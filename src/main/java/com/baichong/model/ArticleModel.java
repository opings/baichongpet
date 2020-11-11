package com.baichong.model;

import com.baichong.model.enums.ArticleCategoryEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章
 *
 * @author zhaoyongzhen
 * @since 2020/10/25 11:54
 */
@Data
public class ArticleModel {

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布时间
     */
    private Date publishDate;

    /**
     * 最后修改时间ArticleCategoryEnum
     */
    private String lastUpdateDate;

    /**
     * 文章类型
     *
     * @see ArticleCategoryEnum
     */
    private String category;

    /**
     * 文章标签
     */
    private List<LabelModel> labelList;

    /**
     * 封面图
     */
    private String surfacePlot;

    /**
     * 插图
     */
    private List<String> illustratingPicture;

}
