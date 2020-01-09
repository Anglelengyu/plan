package cn.shuhan.plan.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Data
public class ArticleDTO{

    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "文章分类")
    private Long tagId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "发布状态 默认1 1-发布 0-草稿箱")
    private Integer issueStatus;

    @ApiModelProperty(value = "自动发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm;ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm;ss", timezone = "GMT+8")
    private Date issueDate;

}
