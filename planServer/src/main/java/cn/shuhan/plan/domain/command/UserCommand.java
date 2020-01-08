package cn.shuhan.plan.domain.command;

import cn.shuhan.plan.domain.entity.Role;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lengyu
 * @since 2019-11-22
 */
@Data
public class UserCommand{

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实密码")
    private String pwd;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "注册时间")
    private Date registerDate;

    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    @ApiModelProperty(value = "性别 1-男 2-女")
    private Integer gender;


    @ApiModelProperty(value = "是否禁用 0没禁用 1 禁用  默认0")
    private Boolean isDelete;

}
