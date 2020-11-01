package com.baichong.controller;

import com.baichong.controller.request.BasePageRequest;
import com.baichong.controller.request.CreateBiologyCatalogueRequest;
import com.baichong.controller.request.QueryBiologyCatalogueRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.service.BiologyCatalogueService;
import com.baichong.util.ConstantUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:40
 */
@Controller
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

}
