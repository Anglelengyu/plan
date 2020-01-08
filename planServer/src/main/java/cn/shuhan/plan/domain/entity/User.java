package cn.shuhan.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="UserEntity对象", description="")
public class User implements Serializable, UserDetails {


    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "注册时间")
    private Date registerDate;

    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    @ApiModelProperty(value = "性别 1-男 2-女")
    private Integer gender;

    @ApiModelProperty(value = "是否禁用 0没禁用 1 禁用  默认0")
    private Boolean isDelete;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色")
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        List<Role> roles = userMapper.getRolesByUserName(username);
        if (CollectionUtils.isEmpty(roles)){
            authorities.add(new SimpleGrantedAuthority("ROLE_NORMAL"));
            return authorities;
        }
        roles.forEach(e->{
            authorities.add(new SimpleGrantedAuthority(e.getName()));
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  //用户过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;   //用户锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  //密码过期
    }

    @Override
    public boolean isEnabled() {
        return true;  //用户禁用
    }
}
