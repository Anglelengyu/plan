package cn.shuhan.plan.service;


import cn.shuhan.plan.domain.entity.Menu;
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
public interface MenuService extends IService<Menu> {

    List<Menu> getAllMenu();
}
