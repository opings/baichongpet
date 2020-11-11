package com.baichong.service;

import com.baichong.dao.entity.ArticleDO;
import com.baichong.dao.entity.LabelDO;
import com.baichong.dao.entity.LabelRelationDO;
import com.baichong.dao.mapper.ArticleMapper;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.ArticleModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.ArticleHelper;
import com.baichong.util.DateUtils;
import com.baichong.util.IDUtils;
import com.baichong.util.SplitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 13:24
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleHelper articleHelper;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private LabelRelationMapper labelRelationMapper;
    @Autowired
    private LabelMapper labelMapper;

    public void create(String title,
                       String content,
                       String author,
                       String category,
                       String surfacePlot,
                       String illustratingPicture,
                       String labelIds) {
        transactionTemplate.execute(status -> {
            ArticleDO articleDO = new ArticleDO();
            articleDO.setArticleId(IDUtils.getId());
            articleDO.setTitle(title);
            articleDO.setContent(content);
            articleDO.setAuthor(author);
            articleDO.setCategory(category);
            articleDO.setSurfacePlot(surfacePlot);
            articleDO.setIllustratingPicture(illustratingPicture);
            articleDO.setPublishDate(DateUtils.nowDate());
            articleDO.setLastUpdateDate(DateUtils.nowDate());
            articleMapper.insert(articleDO);

            List<String> labelIdList = SplitterUtils.toList(labelIds);
            for (String labelIdStr : labelIdList) {
                LabelDO labelDO = labelMapper.selectById(Long.parseLong(labelIdStr));
                if (Objects.isNull(labelDO)) {
                    continue;
                }
                LabelRelationDO labelRelationDO = new LabelRelationDO();
                labelRelationDO.setTargetType(LabelTargetTypeEnum.ARTICLE_TAG.getCode());
                labelRelationDO.setTargetId(articleDO.getArticleId());
                labelRelationDO.setLabelId(Long.parseLong(labelIdStr));
                labelRelationMapper.insert(labelRelationDO);
            }
            return true;
        });
    }


    public List<ArticleModel> listByCategory(String category, Integer startIndex, Integer pageSize) {
        List<ArticleDO> articleDOS = articleMapper.listByCategory(category, startIndex, pageSize);
        return articleDOS.stream()
                .map(item -> articleHelper.buildArticleModel(item))
                .collect(Collectors.toList());
    }


    public ArticleModel selectByArticleId(String articleId) {
        return articleHelper.buildArticleModel(articleMapper.selectByArticleId(articleId));
    }

}
