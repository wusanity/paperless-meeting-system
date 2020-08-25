package com.szsm.videomeeting.base.context;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  <p> 基类查询参数 </p>
 *
 * @description:
 * @author: wuzhike
 * @date: 2020/7/30 0013 1:57
 */
@ApiModel(description = "基类查询参数")
@Data
public class BaseQuery extends BasePageQuery{
    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
