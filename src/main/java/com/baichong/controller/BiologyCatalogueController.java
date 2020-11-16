package com.baichong.controller;

import com.baichong.controller.request.BasePageRequest;
import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueCategoryLabelRequest;
import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.controller.request.biologycatalogue.QueryBiologyCatalogueCategoryLabelRequest;
import com.baichong.controller.request.biologycatalogue.QueryBiologyCatalogueRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.EncyclopediasCategoryEnum;
import com.baichong.service.BiologyCatalogueService;
import com.baichong.util.ConstantUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/{projectId}/{version}/biologyCatalogue")
@Api(value = "生物名录接口", tags = {"生物名录接口"})
public class BiologyCatalogueController {

    @Autowired
    private BiologyCatalogueService biologyCatalogueService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建生物名录")
    public SimpleResult<String> createBiologyCatalogue(@RequestBody CreateBiologyCatalogueRequest request) {
        biologyCatalogueService.create(request);
        return SimpleResult.success(ConstantUtils.Y);
    }


    @ResponseBody
    @RequestMapping(value = "/selectBiologyCatalogueInfo", method = RequestMethod.GET)
    @ApiOperation(value = "生物名录详情")
    public SimpleResult<BiologyCatalogueModel> selectBiologyCatalogueInfo(QueryBiologyCatalogueRequest request) {
        BiologyCatalogueModel biologyCatalogueModel =
                biologyCatalogueService.selectByBiologyCatalogueId(request.getBiologyCatalogueId());
        return SimpleResult.success(biologyCatalogueModel);
    }

    @ResponseBody
    @RequestMapping(value = "/listBiologyCatalogueInfo", method = RequestMethod.GET)
    @ApiOperation(value = "生物名录列表")
    public SimpleResult<List<BiologyCatalogueModel>> listBiologyCatalogueInfo(BasePageRequest request) {
        List<BiologyCatalogueModel> biologyCatalogueModels =
                biologyCatalogueService.listBiologyCatalogue(request.getStartIndex(), request.getPageSize());
        return SimpleResult.success(biologyCatalogueModels);
    }


    @ResponseBody
    @RequestMapping(value = "/listCategory", method = RequestMethod.GET)
    @ApiOperation(value = "生物名录分类列表")
    public SimpleResult<List<Map<String, String>>> listCategory() {
        List<Map<String, String>> result = Lists.newArrayList();
        for (EncyclopediasCategoryEnum encyclopediasCategoryEnum : EncyclopediasCategoryEnum.values()) {
            Map<String, String> articleCategoryList = Maps.newHashMap();
            articleCategoryList.put("code", encyclopediasCategoryEnum.getCode());
            articleCategoryList.put("desc", encyclopediasCategoryEnum.getDesc());
            result.add(articleCategoryList);
        }
        return SimpleResult.success(result);
    }

    @ResponseBody
    @RequestMapping(value = "/createCategoryLabel", method = RequestMethod.GET)
    @ApiOperation(value = "创建名录分类标签")
    public SimpleResult<LabelModel> createCategoryLabel(CreateBiologyCatalogueCategoryLabelRequest request) {
        LabelModel labelModel = biologyCatalogueService.createCategoryLabel(request.getCategory(), request.getLabelName());
        return SimpleResult.success(labelModel);
    }


    @ResponseBody
    @RequestMapping(value = "/listCategoryLabel", method = RequestMethod.GET)
    @ApiOperation(value = "名录分类标签列表")
    public SimpleResult<List<LabelModel>> listCategoryLabel(QueryBiologyCatalogueCategoryLabelRequest request) {
        List<LabelModel> result = biologyCatalogueService.listCategoryLabel(request.getCategory());
        return SimpleResult.success(result);
    }

}
