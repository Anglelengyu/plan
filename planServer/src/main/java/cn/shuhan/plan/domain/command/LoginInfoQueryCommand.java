package cn.shuhan.plan.domain.command;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("login_info")
@ApiModel(value="LoginInfo对象", description="")
public class LoginInfoQueryCommand extends PageCommand{

    private Long id;

    private Long userId;

    private String name;

    private String ip;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm;ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm;ss", timezone = "GMT+8")
    private Date loginTime;

}
