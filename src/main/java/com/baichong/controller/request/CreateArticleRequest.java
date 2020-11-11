package com.baichong.controller.request;

import com.baichong.model.enums.ArticleCategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 11:52
 */
@Data
public class CreateArticleRequest implements Serializable {


    private static final long serialVersionUID = 9017907056902054976L;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true)

    private String content;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", required = true)
    private String author;

    /**
     * 文章类型
     *
     * @see ArticleCategoryEnum
     */
    @ApiModelProperty(value = "文章类型", required = true)
    private String category;

    /**
     * 封面图
     */
    @ApiModelProperty(value = "封面图", required = false)
    private String surfacePlot;

    /**
     * 插图
     */
    @ApiModelProperty(value = "插图,多个英文逗号隔开", required = false)
    private String illustratingPicture;

    /**
     * 标签id 逗号隔开
     */
    @ApiModelProperty(value = "标签id 逗号隔开", required = true)
    private String labelIds;


}
