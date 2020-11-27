package com.baichong.service.helper;

import com.baichong.dao.entity.ArticleDO;
import com.baichong.dao.entity.ArticleExtensionDO;
import com.baichong.dao.mapper.ArticleExtensionMapper;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.ArticleModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.LabelService;
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
    private LabelService labelService;
    @Autowired
    private ArticleExtensionMapper articleExtensionMapper;

    public ArticleModel buildArticleModel(ArticleDO articleDO, Boolean fillExtension) {
        if (Objects.isNull(articleDO)) {
            return null;
        }
        ArticleModel articleModel = new ArticleModel();
        articleModel.setArticleId(articleDO.getArticleId());
        articleModel.setTitle(articleDO.getTitle());

        articleModel.setAuthor(articleDO.getAuthor());
        articleModel.setCategory(articleDO.getCategory());
        articleModel.setSurfacePlot(articleDO.getSurfacePlot());
        articleModel.setIllustratingPicture(SplitterUtils.toList(articleDO.getIllustratingPicture()));
        articleModel.setPublishDate(articleDO.getPublishDate());
        articleModel.setLastUpdateDate(articleDO.getArticleId());
        articleModel.setHeat(articleDO.getHeat());

        {
            if (fillExtension) {
                ArticleExtensionDO articleExtensionDO = articleExtensionMapper.selectByArticleId(articleDO.getArticleId());
                articleModel.setContent(articleExtensionDO.getContent());
            }
        }

        {
            List<LabelModel> labelList = labelService.listLabelByTargetTypeAndId(
                    LabelTargetTypeEnum.ARTICLE_TAG,
                    articleDO.getArticleId(),
                    0,
                    Integer.MAX_VALUE);
            articleModel.setLabelList(labelList);
        }
        return articleModel;

    }
}
