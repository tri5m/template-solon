package com.example.solon.template.model.ro;

import com.example.solon.template.model.common.request.PageCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title: SearchRo
 * @author: trifolium
 * @date: 2023/1/10
 * @modified :
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "分页+关键字搜索")
public class SearchRo extends PageCondition {

    @ApiModelProperty(value = "关键词")
    private String keyword;
}
