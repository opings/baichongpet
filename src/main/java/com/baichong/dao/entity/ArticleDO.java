package com.baichong.dao.entity;

import com.baichong.model.enums.ArticleCategoryEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/25 11:54
 */
@Data
public class ArticleDO {

    private Long id;

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
     * 最后修改时间
     */
    private Date lastUpdateDate;

    /**
     * 文章类型
     *
     * @see ArticleCategoryEnum
     */
    private String category;

    private Date createDt;

    private Date updateDt;



}
