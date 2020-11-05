package com.baichong.controller;

import com.baichong.controller.request.CreateArticleRequest;
import com.baichong.controller.request.QueryArticleRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.model.ArticleModel;
import com.baichong.model.enums.ArticleCategoryEnum;
import com.baichong.service.ArticleService;
import com.baichong.util.ConstantUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:40
 */
@Controller
@RequestMapping(value = "/{projectId}/{version}/article")
@Api(value = "文章接口", tags = {"文章接口"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建文章")
    public SimpleResult<String> createArticle(
            @RequestBody
            @ApiParam(value = "Created article object", required = true)
                    CreateArticleRequest request) {
        articleService.create(request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getCategory(),
                request.getLabelIds());
        return SimpleResult.success(ConstantUtils.Y);
    }


    @ResponseBody
    @RequestMapping(value = "/listArticle", method = RequestMethod.GET)
    @ApiOperation(value = "文章列表")
    public SimpleResult<List<ArticleModel>> listArticle(QueryArticleRequest request) {
        List<ArticleModel> articleModels = articleService.listByCategory(request.getCategory(),
                request.getStartIndex(),
                request.getPageSize());
        return SimpleResult.success(articleModels);
    }

    @ResponseBody
    @RequestMapping(value = "/selectArticleInfo", method = RequestMethod.GET)
    @ApiOperation(value = "文章详情")
    public SimpleResult<ArticleModel> selectArticleInfo(QueryArticleRequest request) {
        ArticleModel articleModel = articleService.selectByArticleId(request.getArticleId());
        return SimpleResult.success(articleModel);
    }


    @ResponseBody
    @RequestMapping(value = "/ListArticleCategory", method = RequestMethod.GET)
    @ApiOperation(value = "文章分类列表")
    public SimpleResult<List<Map<String, String>>> ListArticleCategory() {
        List<Map<String, String>> result = Lists.newArrayList();
        for (ArticleCategoryEnum articleCategoryEnum : ArticleCategoryEnum.values()) {
            Map<String, String> articleCategoryList = Maps.newHashMap();
            articleCategoryList.put("code", articleCategoryEnum.getCode());
            articleCategoryList.put("desc", articleCategoryEnum.getDesc());
            result.add(articleCategoryList);
        }
        return SimpleResult.success(result);
    }

}
