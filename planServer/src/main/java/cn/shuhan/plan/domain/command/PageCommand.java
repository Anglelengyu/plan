package cn.shuhan.plan.domain.command;

import cn.shuhan.plan.enums.ResultEnum;
import cn.shuhan.plan.exception.BaseException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页信息
 */
@Data
public class PageCommand {

    @ApiModelProperty("分页参数")
    private Pagination pagination;

    @ApiModelProperty("排序参数")
    private List<Sorter> sorterList;

    /**
     * 分页查询构造Page
     */
    public static Page builder(PageCommand pageCommand){
        if (pageCommand == null || pageCommand.getPagination() == null ||pageCommand.getPagination().getPageSize() == null || pageCommand.getPagination().getCurrent() == null) {// pageSize为空则不分页
            throw BaseException.build(ResultEnum.PAGINATION_ERROR);
        }
        if (pageCommand.getPagination().getCurrent() <= 0 || pageCommand.getPagination().getPageSize() <= 0) {
            throw BaseException.build(ResultEnum.PAGINATION_ERROR);
        }
        Page userPage = new Page(pageCommand.getPagination().getCurrent(),pageCommand.getPagination().getPageSize());
        return userPage;
    }

    /**
     * 不分页查询构造Page
     */
    public static Page builderAll(){
        Pagination pagination = new Pagination();
        return new Page(pagination.getCurrent(),pagination.getPageSize());
    }

}
