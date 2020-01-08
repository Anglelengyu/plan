package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.entity.Menu;
import cn.shuhan.plan.mapper.MenuMapper;
import cn.shuhan.plan.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }
}


