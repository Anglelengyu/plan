package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.ReplyCommand;
import cn.shuhan.plan.domain.dto.ReplyDTO;
import cn.shuhan.plan.domain.entity.Reply;
import cn.shuhan.plan.enums.DataStatusEnum;
import cn.shuhan.plan.mapper.ReplyMapper;
import cn.shuhan.plan.service.ReplyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

    private final ReplyMapper replyMapper;

    @Override
    public List<ReplyDTO> getReply(Long articleId) {
        // 获取这个文章下所有的评论
        List<ReplyDTO> replies = replyMapper.listAllReply(articleId);
        if (CollectionUtils.isEmpty(replies)) {
            return null;
        }
        // 获取评论层级
        List<ReplyDTO> dtos = getReplyTree(replies);
        return dtos;
    }

    @Override
    public void create(ReplyCommand command) {
        replyMapper.insertReply(command);
    }

    @Override
    public void delete(Long id) {
        replyMapper.updateStatus(id, DataStatusEnum.DELETED.code);
        // 同时删除子评论
        List<Long> allId = new ArrayList<>();
        deleteChildrenReply(allId, id);

        if (!CollectionUtils.isEmpty(allId)) {
            replyMapper.updateStatusBatch(allId, DataStatusEnum.DELETED.code);
        }
    }

    private void deleteChildrenReply(List<Long> allId, Long id) {
        List<Reply> replies = replyMapper.ListByReplyId(id);
        if (CollectionUtils.isEmpty(replies)) {
            return;
        }
        List<Long> collect = replies.stream().map(Reply::getId).collect(Collectors.toList());
        for (Long aLong : collect) {
            allId.add(aLong);
            deleteChildrenReply(allId, aLong);
        }

    }

    private List<ReplyDTO> getReplyTree(List<ReplyDTO> replies) {
        // 所有上层
        List<ReplyDTO> collect = replies.stream().filter(e -> null == e.getReplyPid()).collect(Collectors.toList());
        List<ReplyDTO> dtoList = new ArrayList<>();
        for (ReplyDTO reply : collect) {

            List<ReplyDTO> childrenList = new ArrayList<>();
            // 获取子集
            getChildren(childrenList, reply, replies);
            reply.setReply(childrenList);
            dtoList.add(reply);
        }
        return dtoList;
    }

    /**
     * @param childrenList 子集
     * @param pReplyDTO    最上层父级
     * @param replies      所有评论
     */
    private void getChildren(List<ReplyDTO> childrenList, ReplyDTO pReplyDTO, List<ReplyDTO> replies) {
        List<ReplyDTO> children = new ArrayList<>();
        for (ReplyDTO reply : replies) {
            if (!StringUtils.isEmpty(reply.getReplyPid()) && pReplyDTO.getId().longValue() == reply.getReplyPid().longValue()) {
                children.add(reply);

                reply.setReviewers(pReplyDTO.getResponder());
                childrenList.add(reply);
            }
        }
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        for (ReplyDTO child : children) {
            getChildren(childrenList, child, replies);
        }
    }

}
