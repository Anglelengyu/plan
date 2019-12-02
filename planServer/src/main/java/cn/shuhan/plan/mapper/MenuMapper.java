package cn.shuhan.plan.mapper;


import cn.shuhan.plan.domain.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface MenuMapper extends BaseMapper<MenuEntity> {

    List<MenuEntity> getAllMenu();
}
