package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.PageCommand;
import cn.shuhan.plan.domain.command.UserQueryCommand;
import cn.shuhan.plan.domain.entity.LoginInfo;
import cn.shuhan.plan.domain.entity.User;
import cn.shuhan.plan.mapper.LoginInfoMapper;
import cn.shuhan.plan.mapper.UserMapper;
import cn.shuhan.plan.service.LoginInfoService;
import cn.shuhan.plan.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Service
public class LoginInfoImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService{

}
