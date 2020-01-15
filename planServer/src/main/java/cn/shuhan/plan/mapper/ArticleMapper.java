package cn.shuhan.plan.mapper;

import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.dto.ArticleDTO;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.domain.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * @author lengyu
 * @since 2019-11-22
 */
//@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleDTO> List(@Param("page") IPage page, @Param("command") ArticleQueryCommand command);

    ArticleDTO getById(@Param("id") Long id);

    void updateArt(ArticleCommand command);

    IPage<ArticleDTO> ListByUserId(@Param("page") IPage page, @Param("command") ArticleQueryCommand command);

    void deleteArt(Long id);
}
