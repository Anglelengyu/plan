package cn.shuhan.plan.service;

import cn.shuhan.plan.domain.command.LoginInfoQueryCommand;
import cn.shuhan.plan.domain.command.UserCommand;
import cn.shuhan.plan.domain.command.UserQueryCommand;
import cn.shuhan.plan.domain.entity.LoginInfo;
import cn.shuhan.plan.domain.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
public interface UserService extends IService<User> {

    IPage listUser(UserQueryCommand command);

    User detail(Long id);

    List<LoginInfo> loginInfo(Long id);

    void update(UserCommand command);

    IPage loginInfoList(LoginInfoQueryCommand command);
}
