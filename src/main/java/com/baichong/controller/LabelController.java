package com.baichong.controller;

import com.baichong.controller.request.label.CreateLabelRequest;
import com.baichong.controller.request.label.QueryLabelRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.model.LabelModel;
import com.baichong.service.LabelService;
import com.baichong.util.ConstantUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:40
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/{projectId}/{version}/label")
@Api(value = "标签接口", tags = {"标签接口"})
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ResponseBody
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建标签")
    public SimpleResult<String> createArticle(
            @RequestBody
            @ApiParam(value = "Created label object", required = true)
                    CreateLabelRequest request) {
        labelService.create(request.getLabelName());
        return SimpleResult.success(ConstantUtils.Y);
    }


    @ResponseBody
    @GetMapping(value = "/listLabel")
    @ApiOperation(value = "标签列表")
    public SimpleResult<List<LabelModel>> listArticle(QueryLabelRequest request) {
        List<LabelModel> labelModels = labelService.listLabel(request.getStartIndex(), request.getPageSize());
        return SimpleResult.success(labelModels);
    }

}