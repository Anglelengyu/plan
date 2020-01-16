package cn.shuhan.plan.domain.command;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author Lengyu
 * @since 2020-01-16
 */
@Data
public class ReplyCommand{

    private Long id;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    private Long replyPid;

    @ApiModelProperty(value = "评论人id")
    private Long userId;

    @ApiModelProperty(value = "评论人名称")
    private String responder;

    @ApiModelProperty(value = "被评论人名称")
    private String reviewers;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评论时间")
    private String time;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "数据状态 1正常 0删除")
    private Integer dataStatus;

}
