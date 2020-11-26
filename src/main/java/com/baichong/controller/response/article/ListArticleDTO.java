package com.baichong.controller.response.article;

import com.baichong.model.ArticleModel;
import lombok.Data;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/11/26 18:00
 */
@Data
public class ListArticleDTO {

    private List<ArticleModel> ArticleModelList;

    private Integer cursorPage;

}
