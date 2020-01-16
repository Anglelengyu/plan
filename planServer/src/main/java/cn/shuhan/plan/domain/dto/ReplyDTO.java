package cn.shuhan.plan.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author Lengyu
 * @since 2020-01-16
 */
@Data
public class ReplyDTO {

    private Long id;

    private Long replyPid;

    @ApiModelProperty(value = "评论人id")
    private Long userId;

    @ApiModelProperty(value = "评论人名称")
    private String responder;

    @ApiModelProperty(value = "被评论人名称")
    private String reviewers;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "评论时间")
    private Date time;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论内容")
    private List<ReplyDTO> reply;


}
