package com.baichong.controller;

import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.controller.request.biologycatalogue.ListQueryBiologyCatalogueRequest;
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
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建生物名录")
    public SimpleResult<String> createBiologyCatalogue(@RequestBody CreateBiologyCatalogueRequest request) {
        biologyCatalogueService.create(request);
        return SimpleResult.success(ConstantUtils.Y);
    }


    @ResponseBody
    @GetMapping(value = "/selectBiologyCatalogueInfo/{biologyCatalogueId}")
    @ApiOperation(value = "生物名录详情")
    public SimpleResult<BiologyCatalogueModel> selectBiologyCatalogueInfo(@PathVariable String biologyCatalogueId) {
        BiologyCatalogueModel biologyCatalogueModel =
                biologyCatalogueService.selectByBiologyCatalogueId(biologyCatalogueId);
        return SimpleResult.success(biologyCatalogueModel);
    }

    @ResponseBody
    @PostMapping(value = "/listBiologyCatalogueInfo")
    @ApiOperation(value = "生物名录列表")
    public SimpleResult<List<BiologyCatalogueModel>> listBiologyCatalogueInfo(ListQueryBiologyCatalogueRequest request) {
        List<BiologyCatalogueModel> biologyCatalogueModels = biologyCatalogueService.listBiologyCatalogue(
                request.getCategory(),
                request.getLabelIds(),
                request.getStartIndex(),
                request.getPageSize());
        return SimpleResult.success(biologyCatalogueModels);
    }


    @ResponseBody
    @GetMapping(value = "/listCategory")
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
    @GetMapping(value = "/createCategoryLabel/{category}/{labelName}")
    @ApiOperation(value = "创建名录分类标签")
    public SimpleResult<LabelModel> createCategoryLabel(@PathVariable String category, @PathVariable String labelName) {
        LabelModel labelModel = biologyCatalogueService.createCategoryLabel(category, labelName);
        return SimpleResult.success(labelModel);
    }


    @ResponseBody
    @GetMapping(value = "/listCategoryLabel/{category}")
    @ApiOperation(value = "名录分类标签列表")
    public SimpleResult<List<LabelModel>> listCategoryLabel(@PathVariable String category) {
        List<LabelModel> result = biologyCatalogueService.listCategoryLabel(category);
        return SimpleResult.success(result);
    }

}
