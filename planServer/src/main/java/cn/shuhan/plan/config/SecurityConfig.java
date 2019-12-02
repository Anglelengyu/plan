package cn.shuhan.plan.config;

import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.domain.entity.UserEntity;
import cn.shuhan.plan.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 逻辑顺序
 * 1.登录的时候会调用loadUserByUsername方法验证用户，然后调用实现了UserDetails的Hr中的getAuthorities，进行用户权限设置，然后验证密码  BCryptPasswordEncoder加密方式
 * 2.访问其他接口的时候：
 * (1)metadataSource中的getAttributes 去获取数据库中当前接口需要的权限角色
 * (2)UrlAccessDecisionManager中的decide 去查看当前用户的权限中是否有接口需要的权限
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomMetadataSource metadataSource;

    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    CustommAuthentionFilter customAuthenticationFilter() throws Exception {
        CustommAuthentionFilter custommAuthentionFilter = new CustommAuthentionFilter();
        custommAuthentionFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication auth) throws IOException {
                resp.setContentType("application/json;charset=utf-8");
                UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                ApiResp respBean = ApiResp.success("登录成功!", user);
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = resp.getWriter();
                out.write(om.writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        custommAuthentionFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                AuthenticationException e) throws IOException {
                resp.setContentType("application/json;charset=utf-8");
                ApiResp respBean = null;
                if (e instanceof BadCredentialsException ||
                        e instanceof UsernameNotFoundException) {
                    respBean = ApiResp.fail(300,"账户名或者密码输入错误!");
                } else if (e instanceof LockedException) {
                    respBean = ApiResp.fail(300,"账户被锁定，请联系管理员!");
                } else if (e instanceof CredentialsExpiredException) {
                    respBean = ApiResp.fail(300,"密码过期，请联系管理员!");
                } else if (e instanceof AccountExpiredException) {
                    respBean = ApiResp.fail(300,"账户过期，请联系管理员!");
                } else if (e instanceof DisabledException) {
                    respBean = ApiResp.fail(300,"账户被禁用，请联系管理员!");
                } else {
                    respBean = ApiResp.fail(300,"登录失败!");
                }
                resp.setStatus(401);
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = resp.getWriter();
                out.write(om.writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        custommAuthentionFilter.setFilterProcessesUrl("/login");

        custommAuthentionFilter.setAuthenticationManager(authenticationManager());
        return custommAuthentionFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder()); //会调用loadUserByUsername方法，然后验证密码  BCryptPasswordEncoder加密方式
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_err", "/favicon.ico", "/register");   //放行
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                }).and()
                .formLogin().loginPage("/login_err")
                .loginProcessingUrl("login").permitAll()
                .and()
                .logout().logoutUrl("logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        ApiResp respBean = ApiResp.success("注销成功!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                }).permitAll()
                .and()
                .csrf().disable()
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}
