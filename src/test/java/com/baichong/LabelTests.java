package com.baichong;

import com.baichong.service.LabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelTests {

    @Autowired
    private LabelService labelService;

    @Test
    public void createTest() {
        labelService.create("test1");
        labelService.create("test2");
        labelService.create("test3");

    }

}
