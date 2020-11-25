package com.baichong.dao.entity;

import com.baichong.model.enums.ArticleCategoryEnum;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhaoyongzhen
 * @since 2020/10/25 11:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "article")
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

    /**
     * 封面图
     */
    private String surfacePlot;

    /**
     * 插图
     */
    private String illustratingPicture;

    private Integer heat;

    private Date createDt;

    private Date updateDt;


}
