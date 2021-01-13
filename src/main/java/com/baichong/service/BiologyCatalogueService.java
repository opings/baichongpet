package com.baichong.service;

import com.alibaba.fastjson.JSON;
import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.entity.BiologyCatalogueExtensionDO;
import com.baichong.dao.entity.LabelRelationDO;
import com.baichong.dao.mapper.BiologyCatalogueExtensionMapper;
import com.baichong.dao.mapper.BiologyCatalogueMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.AnimalViewModel;
import com.baichong.model.ApiViewModel;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.EncyclopediasCategoryEnum;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.BiologyCatalogueHelper;
import com.baichong.util.IDUtils;
import com.baichong.util.SplitterUtils;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;
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

    public Boolean getApiView(String page, String type) {
        Map data = new HashMap();
        data.put("key", "3215896b5afe88cd1858dadda0615651");
        data.put("page", page);
        data.put("num", "15");
        data.put("type", type);
        String resp = HttpRequest.post("http://api.tianapi.com/txapi/pet/index").form(data).body();

        ApiViewModel apiViewModel = JSON.parseObject(resp, ApiViewModel.class);
        for (AnimalViewModel animalViewModel : apiViewModel.getNewslist()) {
            BiologyCatalogueDO biologyCatalogueDO = new BiologyCatalogueDO();
            biologyCatalogueDO.setBiologyCatalogueId(IDUtils.getId());
            biologyCatalogueDO.setTitle(animalViewModel.getName());
            biologyCatalogueDO.setIntroduction(animalViewModel.getDesc().length() >= 200 ? animalViewModel.getDesc().substring(0, 200) : animalViewModel.getDesc());
            biologyCatalogueDO.setImg(animalViewModel.getUrl());
            biologyCatalogueDO.setCategory(EncyclopediasCategoryEnum.ANIMAL.getCode());
            biologyCatalogueMapper.insert(biologyCatalogueDO);
            /**
             * animalViewModel.getFeature()  体态特征
             * animalViewModel.getDesc()     描述
             * animalViewModel.getCharacterFeature() 特点
             * animalViewModel.getCareKnowledge()   照顾须知
             * animalViewModel.getFeedPoints()    喂养注意
             */

            BiologyCatalogueExtensionDO biologyCatalogueExtensionDO = new BiologyCatalogueExtensionDO();
            biologyCatalogueExtensionDO.setBiologyCatalogueId(biologyCatalogueDO.getBiologyCatalogueId());
            biologyCatalogueExtensionDO.setContent(animalViewModel.getFeature());
            biologyCatalogueExtensionDO.setChineseName(animalViewModel.getName());

            biologyCatalogueExtensionDO.setFamily(AnimalEnum.getByCode(type).getDesc());

            biologyCatalogueExtensionMapper.insert(biologyCatalogueExtensionDO);

        }
        return Boolean.TRUE;
    }

    public enum AnimalEnum {
        /**
         * 0 猫 3页
         * 1 狗 10页
         * 2 爬行类 44页
         * 3 小宠物 18页
         * 4 水族类 17页
         */
        CAT("0", "猫科"),
        DOG("1","犬科"),
        REPTILES("2","爬行类"),
        SMALL_PET("3","小宠物"),
        AQUARIUM("4","水族类");
        @Getter
        private String code;

        @Getter
        private String desc;

        AnimalEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static AnimalEnum getByCode(String code) {
            return Arrays.stream(AnimalEnum.values()).filter(item ->
                    item.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
        }
    }
}
