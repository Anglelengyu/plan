package cn.shuhan.plan.mapper;


import cn.shuhan.plan.domain.entity.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenu();
}
