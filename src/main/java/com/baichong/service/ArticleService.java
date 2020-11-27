package com.baichong.service;

import com.baichong.dao.entity.ArticleDO;
import com.baichong.dao.entity.ArticleExtensionDO;
import com.baichong.dao.entity.LabelRelationDO;
import com.baichong.dao.mapper.ArticleExtensionMapper;
import com.baichong.dao.mapper.ArticleMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.ArticleModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.ArticleCategoryEnum;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.ArticleHelper;
import com.baichong.util.DateUtils;
import com.baichong.util.IDUtils;
import com.baichong.util.SplitterUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    private LabelService labelService;
    @Autowired
    private ArticleExtensionMapper articleExtensionMapper;

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
            articleDO.setAuthor(author);
            articleDO.setCategory(category);
            articleDO.setSurfacePlot(surfacePlot);
            articleDO.setIllustratingPicture(illustratingPicture);
            articleDO.setPublishDate(DateUtils.nowDate());
            articleDO.setLastUpdateDate(DateUtils.nowDate());
            articleMapper.insert(articleDO);

            ArticleExtensionDO articleExtensionDO = new ArticleExtensionDO();
            articleExtensionDO.setArticleId(articleDO.getArticleId());
            articleExtensionDO.setContent(content);
            articleExtensionMapper.insert(articleExtensionDO);

            List<String> labelIdList = SplitterUtils.toList(labelIds);
            for (String labelIdStr : labelIdList) {
                LabelModel labelModel = labelService.selectById(Long.parseLong(labelIdStr));
                if (Objects.isNull(labelModel)) {
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


    public IPage<ArticleModel> listByCategory(String category, Integer pageNo, Integer pageSize) {
        QueryWrapper<ArticleDO> query = Wrappers.query(ArticleDO.builder().category(category).build());
        Page<ArticleDO> page = new Page<>(pageNo, pageSize);
        IPage<ArticleDO> articlePage = articleMapper.selectPage(page, query);
        IPage<ArticleModel> modelIPage = new Page<>(pageNo, pageSize);
        List<ArticleModel> articleModelList = articlePage.getRecords()
                .stream()
                .map(articleDO -> articleHelper.buildArticleModel(articleDO, false))
                .collect(Collectors.toList());
        setModelPage(articlePage, modelIPage, articleModelList);
        return modelIPage;
    }

    private void setModelPage(IPage<ArticleDO> articlePage, IPage<ArticleModel> modelIPage, List<ArticleModel> articleModelList) {
        modelIPage.setRecords(articleModelList);
        modelIPage.setPages(articlePage.getPages());
        modelIPage.setTotal(articlePage.getTotal());
        modelIPage.setCurrent(articlePage.getCurrent());
    }


    public ArticleModel selectByArticleId(String articleId) {
        QueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_Id", articleId);
        return articleHelper.buildArticleModel(articleMapper.selectOne(queryWrapper), true);
    }

    public void addHeat(String articleId) {
        articleMapper.addHeat(articleId);
    }

    public List<ArticleModel> heatTop10() {
        QueryWrapper<ArticleDO> query = new QueryWrapper<>();
        query.orderByDesc("heat");
        Page<ArticleDO> page = new Page<>(1, 10);
        IPage<ArticleDO> articlePage = articleMapper.selectPage(page, query);
        return articlePage.getRecords()
                .stream()
                .map(articleDO -> articleHelper.buildArticleModel(articleDO, false))
                .collect(Collectors.toList());
    }

    public List<ArticleModel> categoryHeatTop1() {
        QueryWrapper<ArticleDO> query = new QueryWrapper<>();
        query.in("category", ArticleCategoryEnum.BAI_CHONG_JIAN_KANG, ArticleCategoryEnum.BAI_CHONG_RE_DIAN
                , ArticleCategoryEnum.CHONG_WU_YIN_SHI, ArticleCategoryEnum.YANG_CHONG_CHANG_SHI);
        query.orderByDesc("heat", "create_dt");
        query.groupBy("category");
        List<ArticleDO> articleDOS = articleMapper.selectList(query);
        return articleDOS
                .stream()
                .map(articleDO -> articleHelper.buildArticleModel(articleDO, false))
                .collect(Collectors.toList());
    }


    public List<ArticleModel> listArticleByLabelList(Long labelId, Integer startIndex, Integer pageSize) {
        return labelRelationMapper.listByLabelId(LabelTargetTypeEnum.ARTICLE_TAG.getCode(),
                labelId,
                startIndex,
                pageSize)
                .stream()
                .map(item -> {
                    QueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("article_Id", item.getTargetId());
                    return articleHelper.buildArticleModel(articleMapper.selectOne(queryWrapper), false);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
