package com.baichong.service;

import com.baichong.controller.request.BiologyCatalogueRequest;
import com.baichong.dao.entity.BiologyCatalogueDO;
import com.baichong.dao.mapper.BiologyCatalogueMapper;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.service.helper.BiologyCatalogueHelper;
import com.baichong.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(BiologyCatalogueRequest request) {
        BiologyCatalogueDO biologyCatalogueDO = new BiologyCatalogueDO();
        biologyCatalogueDO.setBiologyCatalogueId(IDUtils.getId());
        biologyCatalogueDO.setTitle(request.getTitle());
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
    }


    public BiologyCatalogueModel selectByBiologyCatalogueId(String biologyCatalogueId) {
        return biologyCatalogueHelper.buildBiologyCatalogueModel(
                biologyCatalogueMapper.selectByBiologyCatalogueId(biologyCatalogueId)
        );
    }


}
