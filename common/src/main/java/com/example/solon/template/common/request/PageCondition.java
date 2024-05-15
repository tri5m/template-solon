package com.example.solon.template.common.request;

import com.example.solon.template.common.enumeration.OrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.noear.solon.validation.annotation.Max;
import org.noear.solon.validation.annotation.Min;

/**
 * @title:
 * @author: trifolium
 * @date: 2019/3/12 15:05
 * @modified :
 */
@Getter
@Setter
@ApiModel(value = "PageCondition", description = "分页组件请求对象")
public class PageCondition {

    @ApiModelProperty(value = "页号", example = "1", dataType = "int")
    @Min(value = 1, message = "页号最小为1")
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "页容量", example = "10", dataType = "int")
    @Min(value = 1, message = "也容量最小为1")
    @Max(value = 1000, message = "最大页容量为1000")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序字段", example = "id,name", dataType = "string")
    private String sort = "";

    @ApiModelProperty(value = "排序方式", example = "asc,desc", dataType = "string")
    private OrderType order;

}
