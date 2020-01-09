package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.*;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.domain.entity.LoginInfo;
import cn.shuhan.plan.domain.entity.User;
import cn.shuhan.plan.mapper.ArticleMapper;
import cn.shuhan.plan.mapper.LoginInfoMapper;
import cn.shuhan.plan.mapper.UserMapper;
import cn.shuhan.plan.service.ArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    private final ArticleMapper articleMapper;

    @Override
    public IPage listArticle(ArticleQueryCommand command) {
        IPage page = PageCommand.builder(command);
        return articleMapper.List(page,command);
    }

    @Override
    public void create(ArticleCommand command) {
        Article article = new Article();
        BeanUtils.copyProperties(command,article);
        articleMapper.insert(article);
    }

}
