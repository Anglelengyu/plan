package cn.shuhan.plan.domain.command;

import cn.shuhan.plan.enums.ResultEnum;
import cn.shuhan.plan.exception.BaseException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // 排序参数
        if (!CollectionUtils.isEmpty(pageCommand.getSorterList())){
            List<Sorter> sorterList = pageCommand.getSorterList();
            Map<String, List<Sorter>> map = sorterList.stream().collect(Collectors.groupingBy(sorter -> sorter.getOrder()));
            for (String s : map.keySet()) {
                if ("asc".equals(s)){
                    List<String> ascList = map.get(s).stream().map(Sorter::getField).collect(Collectors.toList());
                    userPage.setAscs(ascList);
                }
                if ("desc".equals(s)){
                    List<String> descList = map.get(s).stream().map(Sorter::getField).collect(Collectors.toList());
                    userPage.setDescs(descList);
                }
            }
        }
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
