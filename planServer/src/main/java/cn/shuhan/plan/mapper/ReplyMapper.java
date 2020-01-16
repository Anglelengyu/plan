package cn.shuhan.plan.mapper;

import cn.shuhan.plan.domain.command.ReplyCommand;
import cn.shuhan.plan.domain.dto.ReplyDTO;
import cn.shuhan.plan.domain.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author Lengyu
 * @since 2020-01-16
 */
public interface ReplyMapper extends BaseMapper<Reply> {

    List<ReplyDTO> listAllReply(@Param("articleId") Long articleId);

    void insertReply(@Param("command") ReplyCommand command);

    void updateStatus(@Param("id") Long id,@Param("dataStatus") Integer dataStatus);

    void updateStatusBatch(@Param("allId")List<Long> allId,@Param("dataStatus") Integer dataStatus);

    List<Reply> ListByReplyId(@Param("id") Long id);
}
