package com.baichong.service.helper;

import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyongzhen
 * @since 2020/10/27 16:12
 */
@Component
public class BiologyCatalogueHelper {

    @Autowired
    private LabelService labelService;


    public BiologyCatalogueModel buildBiologyCatalogueModel(BiologyCatalogueDO biologyCatalogueDO) {
        if (Objects.isNull(biologyCatalogueDO)) {
            return null;
        }
        BiologyCatalogueModel biologyCatalogueModel = new BiologyCatalogueModel();
        biologyCatalogueModel.setBiologyCatalogueId(biologyCatalogueDO.getBiologyCatalogueId());
        biologyCatalogueModel.setTitle(biologyCatalogueDO.getTitle());
        biologyCatalogueModel.setIntroduction(biologyCatalogueDO.getIntroduction());
        biologyCatalogueModel.setImg(biologyCatalogueDO.getImg());
        biologyCatalogueModel.setContent(biologyCatalogueDO.getContent());
        biologyCatalogueModel.setCategory(biologyCatalogueDO.getCategory());
        biologyCatalogueModel.setUpdateDt(biologyCatalogueDO.getUpdateDt());

        biologyCatalogueModel.setChineseName(biologyCatalogueDO.getChineseName());
        biologyCatalogueModel.setAlias(biologyCatalogueDO.getAlias());
        biologyCatalogueModel.setKingdom(biologyCatalogueDO.getKingdom());
        biologyCatalogueModel.setPhylum(biologyCatalogueDO.getPhylum());
        biologyCatalogueModel.setSubPhylum(biologyCatalogueDO.getSubPhylum());
        biologyCatalogueModel.setBiologyClass(biologyCatalogueDO.getBiologyClass());
        biologyCatalogueModel.setBiologySubClass(biologyCatalogueDO.getBiologySubClass());
        biologyCatalogueModel.setOrder(biologyCatalogueDO.getOrders());
        biologyCatalogueModel.setSubOrder(biologyCatalogueDO.getSubOrder());
        biologyCatalogueModel.setFamily(biologyCatalogueDO.getFamily());
        biologyCatalogueModel.setSubFamily(biologyCatalogueDO.getSubFamily());
        biologyCatalogueModel.setRace(biologyCatalogueDO.getRace());
        biologyCatalogueModel.setSubRace(biologyCatalogueDO.getSubRace());
        biologyCatalogueModel.setGenus(biologyCatalogueDO.getGenus());
        biologyCatalogueModel.setSubGenus(biologyCatalogueDO.getSubGenus());
        biologyCatalogueModel.setSpecies(biologyCatalogueDO.getSpecies());
        biologyCatalogueModel.setSubSpecies(biologyCatalogueDO.getSubSpecies());

        {
            List<LabelModel> labelList = labelService.listLabelByTargetTypeAndId(
                    LabelTargetTypeEnum.ENCYCLOPEDIAS_TAG,
                    biologyCatalogueDO.getBiologyCatalogueId(),
                    0,
                    Integer.MAX_VALUE);
            biologyCatalogueModel.setLabelList(labelList);
        }

        return biologyCatalogueModel;
    }
}
