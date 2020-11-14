package com.baichong.controller.request.article;

import com.baichong.controller.request.BasePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 12:01
 */
@Data
public class QueryArticleRequest extends BasePageRequest {
    @ApiModelProperty(value = "文章ID", required = false)
    private String articleId;
    @ApiModelProperty(value = "文章类型", required = false)
    private String category;
}
