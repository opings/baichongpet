package com.baichong.controller;

import com.baichong.controller.request.article.CreateArticleRequest;
import com.baichong.controller.request.article.QueryArticleRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.controller.response.article.ListArticleDTO;
import com.baichong.model.ArticleModel;
import com.baichong.model.enums.ArticleCategoryEnum;
import com.baichong.service.ArticleService;
import com.baichong.util.ConstantUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:40
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/{projectId}/{version}/article")
@Api(value = "文章接口", tags = {"文章接口"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建文章")
    public SimpleResult<String> createArticle(
            @RequestBody
            @ApiParam(value = "Created article object", required = true)
                    CreateArticleRequest request) {
        articleService.create(request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getCategory(),
                request.getSurfacePlot(),
                request.getIllustratingPicture(),
                request.getLabelIds());
        return SimpleResult.success(ConstantUtils.Y);
    }


    @ResponseBody
    @PostMapping(value = "/listArticle")
    @ApiOperation(value = "根据类型查文章列表")
    public SimpleResult<IPage<ArticleModel>> listArticle(@RequestBody QueryArticleRequest request) {
        IPage<ArticleModel> articleModels = articleService.listByCategory(request.getCategory(),
                request.getPageNo(),
                request.getPageSize());
        return SimpleResult.success(articleModels);
    }

    @ResponseBody
    @PostMapping(value = "/listArticleByLabelList")
    @ApiOperation(value = "根据标签查文章列表")
    public SimpleResult<ListArticleDTO> listArticleByLabelList(@RequestBody QueryArticleRequest request) {
        List<ArticleModel> articleModels = articleService.listArticleByLabelList(request.getLabelId(),
                request.getStartIndex(),
                request.getPageSize());

        ListArticleDTO listArticleDTO = new ListArticleDTO();
        listArticleDTO.setArticleModelList(articleModels);
        listArticleDTO.setCursorPage(request.getPageNo() + 1);

        return SimpleResult.success(listArticleDTO);
    }

    @ResponseBody
    @GetMapping(value = "/selectArticleInfo/{articleId}")
    @ApiOperation(value = "文章详情")
    public SimpleResult<ArticleModel> selectArticleInfo(@PathVariable String articleId) {
        ArticleModel articleModel = articleService.selectByArticleId(articleId);
        return SimpleResult.success(articleModel);
    }


    @ResponseBody
    @GetMapping(value = "/listArticleCategory")
    @ApiOperation(value = "文章分类列表")
    public SimpleResult<List<Map<String, String>>> listArticleCategory() {
        List<Map<String, String>> result = Lists.newArrayList();
        for (ArticleCategoryEnum articleCategoryEnum : ArticleCategoryEnum.values()) {
            Map<String, String> articleCategoryList = Maps.newHashMap();
            articleCategoryList.put("code", articleCategoryEnum.getCode());
            articleCategoryList.put("desc", articleCategoryEnum.getDesc());
            result.add(articleCategoryList);
        }
        return SimpleResult.success(result);
    }

    @ResponseBody
    @GetMapping(value = "/addHeat/{articleId}")
    @ApiOperation(value = "文章增加热度")
    public SimpleResult<String> addHeat(@PathVariable String articleId) {
        articleService.addHeat(articleId);
        return SimpleResult.success(ConstantUtils.Y);
    }

    @ResponseBody
    @GetMapping(value = "/heatTop10")
    @ApiOperation(value = "文章热度Top10")
    public SimpleResult<List<ArticleModel>> heatTop10() {
        List<ArticleModel> articleModelList = articleService.heatTop10();
        return SimpleResult.success(articleModelList);
    }

    @ResponseBody
    @GetMapping(value = "/categoryHeatTop1")
    @ApiOperation(value = "文章分类热度Top1")
    public SimpleResult<List<ArticleModel>> categoryHeatTop1() {
        List<ArticleModel> articleModelList = articleService.categoryHeatTop1();
        return SimpleResult.success(articleModelList);
    }


}
