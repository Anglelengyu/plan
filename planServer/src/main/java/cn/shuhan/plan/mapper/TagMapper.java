package cn.shuhan.plan.mapper;

import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.command.TagCommand;
import cn.shuhan.plan.domain.command.TagQueryCommand;
import cn.shuhan.plan.domain.dto.ArticleDTO;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.domain.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @author lengyu
 * @since 2019-11-22
 */
public interface TagMapper extends BaseMapper<Tag> {

    void updateByCommand(TagCommand command);

    void ChangStatus(@Param("id") Long id, @Param("status") Integer status, @Param("dataStatus") Integer dataStatus);
}
