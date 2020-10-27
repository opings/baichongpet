package com.baichong;

import com.baichong.controller.request.BiologyCatalogueRequest;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.service.BiologyCatalogueService;
import com.baichong.service.LabelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiologyCatalogueTests {

    @Autowired
    private BiologyCatalogueService biologyCatalogueService;

    @Test
    public void createTest() {
        BiologyCatalogueRequest request = new BiologyCatalogueRequest();
        request.setTitle("哈士奇");
        request.setImg("https://i5.hoopchina.com.cn/live_android_1589530560659.jpg");
        request.setContent("1111111111111111111111111");
        request.setCategory("犬");
        request.setChineseName("阿拉斯加");
        request.setAlias(request.getAlias());
        request.setKingdom(request.getKingdom());
        request.setPhylum(request.getPhylum());
        request.setSubPhylum(request.getSubPhylum());
        request.setBiologyClass(request.getBiologyClass());
        request.setBiologySubClass(request.getBiologySubClass());
        request.setOrder(request.getOrder());
        request.setSubOrder(request.getSubOrder());
        request.setFamily(request.getFamily());
        request.setSubFamily(request.getSubFamily());
        request.setRace(request.getRace());
        request.setSubRace(request.getSubRace());
        request.setGenus(request.getGenus());
        request.setSubGenus(request.getSubGenus());
        request.setSpecies(request.getSpecies());
        request.setSubSpecies(request.getSubSpecies());

        biologyCatalogueService.create(request);
    }

    @Test
    public void selectByBiologyCatalogueIdTest() throws JsonProcessingException {
        BiologyCatalogueModel biologyCatalogueModel = biologyCatalogueService.selectByBiologyCatalogueId("770689471472467968");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(biologyCatalogueModel));
    }

}
