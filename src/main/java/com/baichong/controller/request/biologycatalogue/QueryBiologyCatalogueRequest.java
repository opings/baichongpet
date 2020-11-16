package com.baichong.controller.request.biologycatalogue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 13:48
 */
@Data
public class QueryBiologyCatalogueRequest {
    @ApiModelProperty(value = "生物名录id", required = true)
    private String biologyCatalogueId;
}
