package cn.shuhan.plan.service;


import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.command.TagCommand;
import cn.shuhan.plan.domain.command.TagQueryCommand;
import cn.shuhan.plan.domain.dto.PulldownDTO;
import cn.shuhan.plan.domain.dto.TagDTO;
import cn.shuhan.plan.domain.entity.Tag;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface TagService extends IService<Tag> {
    void create(TagCommand command);

    TagDTO detail(Long id);

    IPage<Tag> listTag(TagQueryCommand command);

    void updateTag(TagCommand command);

    void changeStatus(Long id, Integer status, Integer dataStatus);

    List<PulldownDTO> pulldownTags();
}
