package cn.shuhan.plan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 登录过滤器，将默认的key/value格式使用json
 */
public class CustommAuthentionFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
        if (req.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) ||
                req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken token = null;
            try {
                InputStream is = req.getInputStream();
                Map<String, String> map = mapper.readValue(is, Map.class);
                token = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
                token = new UsernamePasswordAuthenticationToken("", "");
            } finally {
                setDetails(req, token);
                return this.getAuthenticationManager().authenticate(token);
            }
        } else {
            return super.attemptAuthentication(req, resp);
        }
    }
}
