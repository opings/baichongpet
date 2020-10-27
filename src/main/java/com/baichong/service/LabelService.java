package com.baichong.service;

import com.baichong.dao.entity.LabelDO;
import com.baichong.dao.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 16:19
 */
@Service
public class LabelService {

    @Autowired
    private LabelMapper labelMapper;

    public void create(String labelName) {
        LabelDO labelDO = new LabelDO();
        labelDO.setLabelName(labelName);
        labelMapper.insert(labelDO);
    }


}
