package cn.shuhan.plan.domain.command;

import cn.shuhan.plan.enums.ResultEnum;
import cn.shuhan.plan.exception.BaseException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页信息
 */
@Data
public class Pagination {

    @ApiModelProperty("当前页")
    private Integer current = 1;

    @ApiModelProperty("页面尺寸")
    private Integer pageSize = Integer.MAX_VALUE;

    /**
     * 判断是否需要分页以及分页参数是否正确
     */
    public boolean canPaginantion() {
        if (this.pageSize == null || this.current == null) {// pageSize为空则不分页
            return false;
        }
        if (this.current <= 0 || this.pageSize <= 0) {
            throw BaseException.build(ResultEnum.PAGINATION_ERROR);
        }

        return true;
    }
}
