package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.command.PageCommand;
import cn.shuhan.plan.domain.dto.ArticleDTO;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.enums.ResultEnum;
import cn.shuhan.plan.exception.BaseException;
import cn.shuhan.plan.mapper.ArticleMapper;
import cn.shuhan.plan.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;

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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public IPage listArticle(ArticleQueryCommand command) {
        IPage page = PageCommand.builder(command);
        if (CollectionUtils.isEmpty(command.getSorterList())){
            ((Page) page).setDesc("a.create_time");
        }
        return articleMapper.List(page, command);
    }

    @Override
    public IPage listByUserId(ArticleQueryCommand command) {
        IPage page = PageCommand.builder(command);
        if (CollectionUtils.isEmpty(command.getSorterList())){
            ((Page) page).setDesc("a.create_time");
        }
        return articleMapper.ListByUserId(page, command);
    }

    @Override
    public ArticleDTO detail(Long id) {
        ArticleDTO dto = articleMapper.getById(id);
        if (ObjectUtils.isEmpty(dto)) {
            throw BaseException.build(ResultEnum.NO_DATA);
        }
        return dto;
    }

    @Override
    public void updateArt(ArticleCommand command) {
        command.setUpdateTime(new Date());
        articleMapper.updateArt(command);
    }

    @Override
    public void create(ArticleCommand command) {
        Article article = new Article();
        BeanUtils.copyProperties(command, article);
        article.setCreateTime(new Date());
        articleMapper.insert(article);
    }

    @Override
    public void deleteArt(Long id) {
        articleMapper.deleteArt(id);
    }
}
