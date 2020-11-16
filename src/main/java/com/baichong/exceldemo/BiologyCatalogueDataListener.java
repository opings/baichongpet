package com.baichong.exceldemo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baichong.controller.request.biologycatalogue.CreateBiologyCatalogueRequest;
import com.baichong.service.ArticleService;
import com.baichong.service.BiologyCatalogueService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


// 有个很重要的点 ArticleDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class BiologyCatalogueDataListener extends AnalysisEventListener<BiologyCatalogueExcelData> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<BiologyCatalogueService> list = new ArrayList<>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private BiologyCatalogueService service;
//    public ArticleDataListener() {
//        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//        demoDAO = new DemoDAO();
//    }
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public BiologyCatalogueDataListener(BiologyCatalogueService service) {
        this.service = service;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(BiologyCatalogueExcelData data, AnalysisContext context) {
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));

        CreateBiologyCatalogueRequest request = new CreateBiologyCatalogueRequest();
        request.setTitle(data.getString1());
        request.setIntroduction(data.getString2());
        request.setImg(data.getString3());
        request.setContent(data.getString4());
        request.setCategory(data.getString5());

        request.setChineseName(data.getString6());
        request.setAlias(data.getString7());
        request.setKingdom(data.getString8());
        request.setPhylum(data.getString9());
        request.setSubPhylum(data.getString10());
        request.setBiologyClass(data.getString11());
        request.setBiologySubClass(data.getString12());
        request.setOrder(data.getString13());
        request.setSubOrder(data.getString14());
        request.setFamily(data.getString15());
        request.setSubFamily(data.getString16());
        request.setRace(data.getString17());
        request.setSubRace(data.getString18());
        request.setGenus(data.getString19());
        request.setSubGenus(data.getString20());
        request.setSpecies(data.getString21());
        request.setSubSpecies(data.getString22());
        request.setLabelIds(data.getString23());

        service.create(request);




//        list.add(data);
//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            list.clear();
//        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
//        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("存储");
//        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        demoDAO.save(list);
//        LOGGER.info("存储数据库成功！");
    }
}