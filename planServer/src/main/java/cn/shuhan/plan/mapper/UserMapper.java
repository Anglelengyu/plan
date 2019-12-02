package cn.shuhan.plan.mapper;


import cn.shuhan.plan.domain.entity.RoleEntity;
import cn.shuhan.plan.domain.entity.UserEntity;
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
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    List<RoleEntity> getRolesByUserName(String username);

    UserEntity selectRolesByUsername(String username);
}
