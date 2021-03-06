package cn.shuhan.plan.service;

import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.dto.ArticleDTO;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.domain.entity.LoginInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
public interface ArticleService extends IService<Article> {

    void create(ArticleCommand command);

    IPage listArticle(ArticleQueryCommand command);

    IPage listByUserId(ArticleQueryCommand command);

    ArticleDTO detail(Long id);

    void updateArt(ArticleCommand command);

    void deleteArt(Long id);
}
