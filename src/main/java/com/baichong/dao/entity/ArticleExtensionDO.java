package com.baichong.dao.entity;

import com.baichong.model.enums.ArticleCategoryEnum;
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
public class ArticleExtensionDO {

    private Long id;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 内容
     */
    private String content;

    private Date createDt;

    private Date updateDt;


}
