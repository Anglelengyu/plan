package cn.shuhan.plan.mapper;


import cn.shuhan.plan.domain.command.UserCommand;
import cn.shuhan.plan.domain.entity.Role;
import cn.shuhan.plan.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRolesByUserName(String username);

    User selectRolesByUsername(String username);

    /**
     * 修改
     * @param command
     */
    void updateByUserId(UserCommand command);
}
