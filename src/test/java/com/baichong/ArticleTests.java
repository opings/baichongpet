package com.baichong;

import com.alibaba.excel.EasyExcel;
import com.baichong.exceldemo.ArticleExcelData;
import com.baichong.exceldemo.ArticleDataListener;
import com.baichong.model.ArticleModel;
import com.baichong.model.enums.ArticleCategoryEnum;
import com.baichong.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void createTest() {
        articleService.create(
                "title",
                "content",
                "author",
                ArticleCategoryEnum.BAI_CHONG_RE_DIAN.getCode(),
                "",
                "",
                "1,2,3,4");
    }

    @Test
    public void listByCategoryTest () throws JsonProcessingException {
        IPage<ArticleModel> result = articleService.listByCategory(
                ArticleCategoryEnum.BAI_CHONG_RE_DIAN.getCode(),
                0,
                10);
        ObjectMapper mapper=new ObjectMapper();
        System.out.println( mapper.writeValueAsString(result));
    }

    @Test
    public void excelBatchLoadData() {

        String fileName = "C:\\work\\workspace\\github\\baichongpet\\zixun.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ArticleExcelData.class, new ArticleDataListener(articleService)).sheet().doRead();

    }

}
