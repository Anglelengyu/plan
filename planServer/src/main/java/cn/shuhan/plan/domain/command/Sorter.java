package cn.shuhan.plan.domain.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 排序参数
 */
@Data
public class Sorter {

    @ApiModelProperty("排序方式:desc-降序，asc-（默认）升序")
    private String order;

    @ApiModelProperty("需排序的字段")
    private String field;

}
