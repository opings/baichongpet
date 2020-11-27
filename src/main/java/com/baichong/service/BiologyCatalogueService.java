package com.baichong.service;

import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.entity.BiologyCatalogueExtensionDO;
import com.baichong.dao.entity.LabelRelationDO;
import com.baichong.dao.mapper.BiologyCatalogueExtensionMapper;
import com.baichong.dao.mapper.BiologyCatalogueMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.BiologyCatalogueHelper;
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
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private BiologyCatalogueExtensionMapper biologyCatalogueExtensionMapper;

    public void create(CreateBiologyCatalogueRequest request) {

        transactionTemplate.execute(status -> {
                    BiologyCatalogueDO biologyCatalogueDO = new BiologyCatalogueDO();
                    biologyCatalogueDO.setBiologyCatalogueId(IDUtils.getId());
                    biologyCatalogueDO.setTitle(request.getTitle());
                    biologyCatalogueDO.setIntroduction(request.getIntroduction());
                    biologyCatalogueDO.setImg(request.getImg());
                    biologyCatalogueDO.setCategory(request.getCategory());
                    biologyCatalogueMapper.insert(biologyCatalogueDO);

                    BiologyCatalogueExtensionDO biologyCatalogueExtensionDO = new BiologyCatalogueExtensionDO();
                    biologyCatalogueExtensionDO.setBiologyCatalogueId(biologyCatalogueDO.getBiologyCatalogueId());
                    biologyCatalogueExtensionDO.setContent(request.getContent());
                    biologyCatalogueExtensionDO.setChineseName(request.getChineseName());
                    biologyCatalogueExtensionDO.setAlias(request.getAlias());
                    biologyCatalogueExtensionDO.setKingdom(request.getKingdom());
                    biologyCatalogueExtensionDO.setPhylum(request.getPhylum());
                    biologyCatalogueExtensionDO.setSubPhylum(request.getSubPhylum());
                    biologyCatalogueExtensionDO.setBiologyClass(request.getBiologyClass());
                    biologyCatalogueExtensionDO.setBiologySubClass(request.getBiologySubClass());
                    biologyCatalogueExtensionDO.setOrders(request.getOrder());
                    biologyCatalogueExtensionDO.setSubOrder(request.getSubOrder());
                    biologyCatalogueExtensionDO.setFamily(request.getFamily());
                    biologyCatalogueExtensionDO.setSubFamily(request.getSubFamily());
                    biologyCatalogueExtensionDO.setRace(request.getRace());
                    biologyCatalogueExtensionDO.setSubRace(request.getSubRace());
                    biologyCatalogueExtensionDO.setGenus(request.getGenus());
                    biologyCatalogueExtensionDO.setSubGenus(request.getSubGenus());
                    biologyCatalogueExtensionDO.setSpecies(request.getSpecies());
                    biologyCatalogueExtensionDO.setSubSpecies(request.getSubSpecies());
                    biologyCatalogueExtensionMapper.insert(biologyCatalogueExtensionDO);

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

                    return true;
                }
        );
    }


    public BiologyCatalogueModel selectByBiologyCatalogueId(String biologyCatalogueId) {
        return biologyCatalogueHelper.buildBiologyCatalogueModel(
                biologyCatalogueMapper.selectByBiologyCatalogueId(biologyCatalogueId), true
        );
    }

    public List<BiologyCatalogueModel> listBiologyCatalogue(String category, String labelIds, int startIndex, int pageSize) {
        List<String> labelIdList = SplitterUtils.toList(labelIds);
        return biologyCatalogueMapper.listBiologyCatalogue(category, labelIdList, startIndex, pageSize)
                .stream()
                .map(item -> biologyCatalogueHelper.buildBiologyCatalogueModel(item, false))
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
