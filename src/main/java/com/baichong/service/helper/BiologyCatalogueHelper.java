package com.baichong.service.helper;

import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.entity.BiologyCatalogueExtensionDO;
import com.baichong.dao.mapper.BiologyCatalogueExtensionMapper;
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
    @Autowired
    private BiologyCatalogueExtensionMapper biologyCatalogueExtensionMapper;


    /**
     *
     * @param biologyCatalogueDO
     * @param fillExtension     是否填充扩展信息  true：填充 ； false:不填充
     * @return
     */
    public BiologyCatalogueModel buildBiologyCatalogueModel(BiologyCatalogueDO biologyCatalogueDO, Boolean fillExtension) {
        if (Objects.isNull(biologyCatalogueDO)) {
            return null;
        }
        BiologyCatalogueModel biologyCatalogueModel = new BiologyCatalogueModel();
        biologyCatalogueModel.setBiologyCatalogueId(biologyCatalogueDO.getBiologyCatalogueId());
        biologyCatalogueModel.setTitle(biologyCatalogueDO.getTitle());
        biologyCatalogueModel.setIntroduction(biologyCatalogueDO.getIntroduction());
        biologyCatalogueModel.setImg(biologyCatalogueDO.getImg());
        biologyCatalogueModel.setCategory(biologyCatalogueDO.getCategory());
        biologyCatalogueModel.setUpdateDt(biologyCatalogueDO.getUpdateDt());

        {
            if (fillExtension) {
                BiologyCatalogueExtensionDO biologyCatalogueExtensionDO =
                        biologyCatalogueExtensionMapper.selectByBiologyCatalogueId(biologyCatalogueDO.getBiologyCatalogueId());
                biologyCatalogueModel.setContent(biologyCatalogueExtensionDO.getContent());
                biologyCatalogueModel.setChineseName(biologyCatalogueExtensionDO.getChineseName());
                biologyCatalogueModel.setAlias(biologyCatalogueExtensionDO.getAlias());
                biologyCatalogueModel.setKingdom(biologyCatalogueExtensionDO.getKingdom());
                biologyCatalogueModel.setPhylum(biologyCatalogueExtensionDO.getPhylum());
                biologyCatalogueModel.setSubPhylum(biologyCatalogueExtensionDO.getSubPhylum());
                biologyCatalogueModel.setBiologyClass(biologyCatalogueExtensionDO.getBiologyClass());
                biologyCatalogueModel.setBiologySubClass(biologyCatalogueExtensionDO.getBiologySubClass());
                biologyCatalogueModel.setOrder(biologyCatalogueExtensionDO.getOrders());
                biologyCatalogueModel.setSubOrder(biologyCatalogueExtensionDO.getSubOrder());
                biologyCatalogueModel.setFamily(biologyCatalogueExtensionDO.getFamily());
                biologyCatalogueModel.setSubFamily(biologyCatalogueExtensionDO.getSubFamily());
                biologyCatalogueModel.setRace(biologyCatalogueExtensionDO.getRace());
                biologyCatalogueModel.setSubRace(biologyCatalogueExtensionDO.getSubRace());
                biologyCatalogueModel.setGenus(biologyCatalogueExtensionDO.getGenus());
                biologyCatalogueModel.setSubGenus(biologyCatalogueExtensionDO.getSubGenus());
                biologyCatalogueModel.setSpecies(biologyCatalogueExtensionDO.getSpecies());
                biologyCatalogueModel.setSubSpecies(biologyCatalogueExtensionDO.getSubSpecies());
            }
        }


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
