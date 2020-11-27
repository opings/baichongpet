package com.baichong;

import com.alibaba.excel.EasyExcel;
import com.baichong.controller.ArticleController;
import com.baichong.controller.request.article.QueryArticleRequest;
import com.baichong.controller.response.SimpleResult;
import com.baichong.controller.response.article.ListArticleDTO;
import com.baichong.dao.entity.ArticleDO;
import com.baichong.dao.entity.ArticleExtensionDO;
import com.baichong.dao.mapper.ArticleExtensionMapper;
import com.baichong.dao.mapper.ArticleMapper;
import com.baichong.exceldemo.ArticleDataListener;
import com.baichong.exceldemo.ArticleExcelData;
import com.baichong.model.ArticleModel;
import com.baichong.model.enums.ArticleCategoryEnum;
import com.baichong.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Autowired
    private ArticleController articleController;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleExtensionMapper articleExtensionMapper;

    @Test
    public void createTest() {
        articleService.create(
                "title",
                "introduction",
                "content",
                "author",
                ArticleCategoryEnum.BAI_CHONG_RE_DIAN.getCode(),
                "",
                "",
                "1,2,3,4");
    }

    @Test
    public void listByCategoryTest() throws JsonProcessingException {
        IPage<ArticleModel> result = articleService.listByCategory(
                ArticleCategoryEnum.BAI_CHONG_RE_DIAN.getCode(),
                0,
                10);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }

    @Test
    public void listArticleByLabelListTest() throws JsonProcessingException {

        QueryArticleRequest queryArticleRequest = new QueryArticleRequest();
        queryArticleRequest.setLabelId(1L);
        queryArticleRequest.setPageNo(1);
        queryArticleRequest.setPageSize(10);
        SimpleResult<ListArticleDTO> result = articleController.listArticleByLabelList(queryArticleRequest);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }

    @Test
    public void repairArticleTest() throws JsonProcessingException {
        QueryWrapper<ArticleDO> query = new QueryWrapper<>();
        List<ArticleDO> articleDOS = articleMapper.selectList(query);
        for (ArticleDO articleDO : articleDOS) {
            ArticleExtensionDO articleExtensionDO = articleExtensionMapper.selectByArticleId(articleDO.getArticleId());
            ArticleDO articleDO1 = new ArticleDO();
            articleDO1.setId(articleDO.getId());
            articleDO1.setIntroduction(a(articleExtensionDO.getContent()));
            articleMapper.updateById(articleDO1);
        }


        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(articleDOS));

    }

    private String a(String aa) {
        int strStartIndex = aa.indexOf(">");
        int strEndIndex = aa.indexOf("<", aa.indexOf("<") + 1);

        if (strStartIndex == -1 || strEndIndex == -1) {
            return aa.substring(0, 50);
        }
        String substring = aa.substring(strStartIndex + 1, strEndIndex);
        if (substring.length() > 50) {
            substring = substring.substring(0, 50);
        }
        return substring;
    }


    @Test
    public void excelBatchLoadData() {

        String fileName = "C:\\work\\workspace\\github\\baichongpet\\zixun.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ArticleExcelData.class, new ArticleDataListener(articleService)).sheet().doRead();

    }

}
