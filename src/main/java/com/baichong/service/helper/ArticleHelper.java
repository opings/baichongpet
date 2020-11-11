package com.baichong.service.helper;

import com.baichong.dao.entity.ArticleDO;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.ArticleModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.util.SplitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 13:46
 */
@Component
public class ArticleHelper {
    @Autowired
    private LabelRelationMapper labelRelationMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private LabelHelper labelHelper;

    public ArticleModel buildArticleModel(ArticleDO articleDO) {
        if (Objects.isNull(articleDO)) {
            return null;
        }
        ArticleModel articleModel = new ArticleModel();
        articleModel.setArticleId(articleDO.getArticleId());
        articleModel.setTitle(articleDO.getTitle());
        articleModel.setContent(articleDO.getContent());
        articleModel.setAuthor(articleDO.getAuthor());
        articleModel.setCategory(articleDO.getCategory());
        articleModel.setSurfacePlot(articleDO.getSurfacePlot());
        articleModel.setIllustratingPicture(SplitterUtils.toList(articleDO.getIllustratingPicture()));
        articleModel.setPublishDate(articleDO.getPublishDate());
        articleModel.setLastUpdateDate(articleDO.getArticleId());
        {
            List<LabelModel> labelList = labelRelationMapper.listByArticleId(
                    LabelTargetTypeEnum.ARTICLE_TAG.getCode(),
                    articleDO.getArticleId(),
                    0,
                    Integer.MAX_VALUE)
                    .stream()
                    .map(item -> labelMapper.selectById(item.getLabelId()))
                    .filter(Objects::nonNull)
                    .map(item -> labelHelper.buildLabelModel(item))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            articleModel.setLabelList(labelList);
        }
        return articleModel;

    }
}
