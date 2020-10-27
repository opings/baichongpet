package com.baichong.service.helper;

import com.baichong.dao.entity.LabelDO;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.model.LabelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 16:19
 */
@Service
public class LabelHelper {


    public LabelModel buildLabelModel(LabelDO labelDO) {
        if (Objects.isNull(labelDO)) {
            return null;
        }
        LabelModel labelModel = new LabelModel();
        labelModel.setId(labelDO.getId());
        labelModel.setLabelName(labelDO.getLabelName());
        return labelModel;
    }

}
