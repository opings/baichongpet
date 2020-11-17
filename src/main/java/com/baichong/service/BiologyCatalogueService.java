package com.baichong.service;

import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.entity.LabelDO;
import com.baichong.dao.entity.LabelRelationDO;
import com.baichong.dao.mapper.BiologyCatalogueMapper;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.BiologyCatalogueHelper;
import com.baichong.util.IDUtils;
import com.baichong.util.SplitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyongzhen
 * @since 2020/10/27 14:42
 */
@Service
public class BiologyCatalogueService {

    @Autowired
    private BiologyCatalogueMapper biologyCatalogueMapper;
    @Autowired
    private BiologyCatalogueHelper biologyCatalogueHelper;
    @Autowired
    private LabelRelationMapper labelRelationMapper;
    @Autowired
    private LabelService labelService;

    public void create(CreateBiologyCatalogueRequest request) {
        BiologyCatalogueDO biologyCatalogueDO = new BiologyCatalogueDO();
        biologyCatalogueDO.setBiologyCatalogueId(IDUtils.getId());
        biologyCatalogueDO.setTitle(request.getTitle());
        biologyCatalogueDO.setIntroduction(request.getIntroduction());
        biologyCatalogueDO.setImg(request.getImg());
        biologyCatalogueDO.setContent(request.getContent());
        biologyCatalogueDO.setCategory(request.getCategory());
        biologyCatalogueDO.setChineseName(request.getChineseName());
        biologyCatalogueDO.setAlias(request.getAlias());
        biologyCatalogueDO.setKingdom(request.getKingdom());
        biologyCatalogueDO.setPhylum(request.getPhylum());
        biologyCatalogueDO.setSubPhylum(request.getSubPhylum());
        biologyCatalogueDO.setBiologyClass(request.getBiologyClass());
        biologyCatalogueDO.setBiologySubClass(request.getBiologySubClass());
        biologyCatalogueDO.setOrders(request.getOrder());
        biologyCatalogueDO.setSubOrder(request.getSubOrder());
        biologyCatalogueDO.setFamily(request.getFamily());
        biologyCatalogueDO.setSubFamily(request.getSubFamily());
        biologyCatalogueDO.setRace(request.getRace());
        biologyCatalogueDO.setSubRace(request.getSubRace());
        biologyCatalogueDO.setGenus(request.getGenus());
        biologyCatalogueDO.setSubGenus(request.getSubGenus());
        biologyCatalogueDO.setSpecies(request.getSpecies());
        biologyCatalogueDO.setSubSpecies(request.getSubSpecies());
        biologyCatalogueMapper.insert(biologyCatalogueDO);

        List<String> labelIdList = SplitterUtils.toList(request.getLabelIds());
        for (String labelIdStr : labelIdList) {
            LabelModel labelModel = labelService.selectById(Long.parseLong(labelIdStr));
            if (Objects.isNull(labelModel)) {
                continue;
            }
            LabelRelationDO labelRelationDO = new LabelRelationDO();
            labelRelationDO.setTargetType(LabelTargetTypeEnum.ENCYCLOPEDIAS_TAG.getCode());
            labelRelationDO.setTargetId(biologyCatalogueDO.getBiologyCatalogueId());
            labelRelationDO.setLabelId(Long.parseLong(labelIdStr));
            labelRelationMapper.insert(labelRelationDO);
        }

    }


    public BiologyCatalogueModel selectByBiologyCatalogueId(String biologyCatalogueId) {
        return biologyCatalogueHelper.buildBiologyCatalogueModel(
                biologyCatalogueMapper.selectByBiologyCatalogueId(biologyCatalogueId)
        );
    }

    public List<BiologyCatalogueModel> listBiologyCatalogue(String category, String labelIds, int startIndex, int pageSize) {
        List<String> labelIdList = SplitterUtils.toList(labelIds);
        return biologyCatalogueMapper.listBiologyCatalogue(category, labelIdList, startIndex, pageSize)
                .stream()
                .map(item -> biologyCatalogueHelper.buildBiologyCatalogueModel(item))
                .collect(Collectors.toList());
    }


    public LabelModel createCategoryLabel(String category, String labelName) {
        LabelModel labelModel = labelService.create(labelName);

        LabelRelationDO labelRelationDO = new LabelRelationDO();
        labelRelationDO.setTargetType(LabelTargetTypeEnum.ENCYCLOPEDIAS_CATEGORY.getCode());
        labelRelationDO.setTargetId(category);
        labelRelationDO.setLabelId(labelModel.getId());
        labelRelationMapper.insert(labelRelationDO);

        return labelModel;
    }


    public List<LabelModel> listCategoryLabel(String category) {
        return labelRelationMapper.listByTargetTypeAndId(
                LabelTargetTypeEnum.ENCYCLOPEDIAS_CATEGORY.getCode(),
                category,
                0,
                Integer.MAX_VALUE)
                .stream()
                .map(item -> labelService.selectById(item.getLabelId()))
                .collect(Collectors.toList());
    }

}
