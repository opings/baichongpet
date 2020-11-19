package com.baichong.controller.request.biologycatalogue;

import com.baichong.controller.request.BasePageRequest;
import com.baichong.model.enums.EncyclopediasCategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 13:48
 */
@Data
public class ListQueryBiologyCatalogueRequest extends BasePageRequest {
    /**
     * 分类
     *
     * @see EncyclopediasCategoryEnum
     */
    @ApiModelProperty(value = "分类", required = false)
    private String category;

    @ApiModelProperty(value = "标签id 逗号隔开", required = false)
    private String labelIds;


}
