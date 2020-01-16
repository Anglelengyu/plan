package cn.shuhan.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("reply")
@ApiModel(value="Reply对象", description="评论")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long replyPid;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "评论人id")
    private Long userId;

    @ApiModelProperty(value = "评论人名称")
    private String responder;

    @ApiModelProperty(value = "被评论人名称")
    private String reviewers;

    @ApiModelProperty(value = "评论时间")
    private Date time;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "数据状态 1正常 0删除")
    private Integer dataStatus;


}
