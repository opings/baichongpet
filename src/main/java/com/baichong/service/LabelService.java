package com.baichong.service;

import com.baichong.dao.entity.LabelDO;
import com.baichong.dao.mapper.LabelMapper;
import com.baichong.dao.mapper.LabelRelationMapper;
import com.baichong.model.LabelModel;
import com.baichong.model.enums.LabelTargetTypeEnum;
import com.baichong.service.helper.LabelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 16:19
 */
@Service
public class LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelHelper labelHelper;

    @Autowired
    private LabelRelationMapper labelRelationMapper;

    public LabelModel create(String labelName) {
        LabelDO labelDO = new LabelDO();
        labelDO.setLabelName(labelName);
        labelMapper.insert(labelDO);

        return labelHelper.buildLabelModel(labelDO);
    }

    public List<LabelModel> listLabel(Integer startIndex, Integer pageSize) {
        List<LabelDO> labelDOS = labelMapper.listLabel(startIndex, pageSize);
        return labelDOS.stream()
                .map(item -> labelHelper.buildLabelModel(item))
                .collect(Collectors.toList());
    }


    public LabelModel selectById(Long labelId) {
        return labelHelper.buildLabelModel(labelMapper.selectById(labelId));
    }


    public List<LabelModel> listLabelByTargetTypeAndId(LabelTargetTypeEnum labelTargetTypeEnum,
                                                       String targetId,
                                                       Integer startIndex,
                                                       Integer pageSize) {
        return labelRelationMapper.listByTargetTypeAndId(
                labelTargetTypeEnum.getCode(),
                targetId,
                startIndex,
                pageSize)
                .stream()
                .map(item -> labelMapper.selectById(item.getLabelId()))
                .filter(Objects::nonNull)
                .map(item -> labelHelper.buildLabelModel(item))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}
