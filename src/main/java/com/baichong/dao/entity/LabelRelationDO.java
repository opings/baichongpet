package com.baichong.dao.entity;

import com.baichong.model.enums.LabelTargetTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 11:12
 */
@Data
public class LabelRelationDO {

    private Long id;

    /**
     * 标签类型
     * @see LabelTargetTypeEnum
     */
    private String targetType;

    private String targetId;

    private Long labelId;

    private Date createDt;

    private Date updateDt;

}
