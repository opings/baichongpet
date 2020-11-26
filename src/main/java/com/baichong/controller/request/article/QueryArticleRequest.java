package com.baichong.controller.request.article;

import com.baichong.controller.request.BasePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

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
    @ApiModelProperty(value = "标签id", required = false)
    private Long labelId;
}
