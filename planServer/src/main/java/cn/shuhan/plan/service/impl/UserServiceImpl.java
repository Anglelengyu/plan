package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.entity.UserEntity;
import cn.shuhan.plan.mapper.UserMapper;
import cn.shuhan.plan.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userMapper.selectRolesByUsername(s);
//        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(UserEntity::getUsername, s);
//        List<UserEntity> list = userMapper.selectList(wrapper);
        if (user == null){
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }
}
