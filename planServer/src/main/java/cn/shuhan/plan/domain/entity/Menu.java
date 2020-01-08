package cn.shuhan.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
@TableName("menu")
@ApiModel(value="MenuEntity对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    private String url;

    private String path;

    private String component;

    private String name;

    @TableField("iconCls")
    private String iconCls;

    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;

    @TableField("parentId")
    private Integer parentId;

    private Boolean enabled;

    private List<Menu> children;

    private List<Role> roles;

}
