package com.baichong.controller.request.biologycatalogue;

import com.baichong.model.enums.EncyclopediasCategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/11/14 16:19
 */
@Data
public class QueryBiologyCatalogueCategoryLabelRequest {

    /**
     * 分类
     *
     * @see EncyclopediasCategoryEnum
     */
    @ApiModelProperty(value = "分类", required = true)
    private String category;

}
