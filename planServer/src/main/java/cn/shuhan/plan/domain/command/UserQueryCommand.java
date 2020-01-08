package cn.shuhan.plan.domain.command;

import cn.shuhan.plan.domain.entity.Role;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lengyu
 * @since 2019-11-22
 */
@Data
public class UserQueryCommand extends PageCommand{


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

    @ApiModelProperty(value = "是否禁用 0没禁用 1 禁用  默认0")
    private Boolean isDelete;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色")
    private List<Role> roles;

}
