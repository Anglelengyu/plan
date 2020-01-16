package cn.shuhan.plan.service;

import cn.shuhan.plan.domain.command.ReplyCommand;
import cn.shuhan.plan.domain.dto.ReplyDTO;
import cn.shuhan.plan.domain.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Lengyu
 * @since 2020-01-16
 */
public interface ReplyService extends IService<Reply> {

    List<ReplyDTO> getReply(Long articleId);

    void create(ReplyCommand command);

    void delete(Long id);
}
