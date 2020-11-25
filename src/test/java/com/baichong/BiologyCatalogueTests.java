package com.baichong;

import com.alibaba.excel.EasyExcel;
import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.exceldemo.BiologyCatalogueDataListener;
import com.baichong.exceldemo.BiologyCatalogueExcelData;
import com.baichong.model.BiologyCatalogueModel;
import com.baichong.service.BiologyCatalogueService;
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
        CreateBiologyCatalogueRequest request = new CreateBiologyCatalogueRequest();
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


    @Test
    public void excelBatchLoadData() {

        String fileName = "C:\\work\\workspace\\github\\baichongpet\\baike.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, BiologyCatalogueExcelData.class, new BiologyCatalogueDataListener(biologyCatalogueService)).sheet().doRead();

    }

}
