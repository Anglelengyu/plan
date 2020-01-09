package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.LoginInfoQueryCommand;
import cn.shuhan.plan.domain.command.PageCommand;
import cn.shuhan.plan.domain.command.UserCommand;
import cn.shuhan.plan.domain.command.UserQueryCommand;
import cn.shuhan.plan.domain.dto.UserLoginDTO;
import cn.shuhan.plan.domain.entity.LoginInfo;
import cn.shuhan.plan.domain.entity.User;
import cn.shuhan.plan.mapper.LoginInfoMapper;
import cn.shuhan.plan.mapper.UserMapper;
import cn.shuhan.plan.service.UserService;
import cn.shuhan.plan.utils.RequestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final LoginInfoMapper loginMapper;
    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectRolesByUsername(s);
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(User::getUsername, s);
//        List<User> list = userMapper.selectList(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        // TODO 登录记录
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(null);
        loginInfo.setName(user.getName());
        loginInfo.setUserId(user.getId());
        loginInfo.setIp(RequestUtil.getIpAddress(request));
        loginInfo.setLoginTime(new Date());
        loginMapper.insert(loginInfo);

        return user;
    }

    @Override
    public IPage listUser(UserQueryCommand command) {

        Page page = PageCommand.builder(command);
        IPage<User> dto = userMapper.selectPage(page, new QueryWrapper<>());
        return dto;
    }

    @Override
    public User detail(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public
    List<LoginInfo> loginInfo(Long id) {
        LambdaQueryWrapper<LoginInfo> wrapper = new QueryWrapper<LoginInfo>().lambda()
                .eq(LoginInfo::getUserId, id)
                .orderByDesc(LoginInfo::getLoginTime);
        return loginMapper.selectList(wrapper);
    }

    @Override
    public void update(UserCommand command) {
        userMapper.updateByUserId(command);
    }

    @Override
    public IPage loginInfoList(LoginInfoQueryCommand command) {
        Page page = PageCommand.builder(command);
        IPage<LoginInfo> dto = loginMapper.selectPage(page, new QueryWrapper<>());
        return dto;
    }
}
