package com.baichong.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhaoyongzhen
 * @since 2020/10/26 11:12
 */
@Data
public class LabelDO {

    private Long id;

    /**
     * 标签名称
     */
    private String labelName;

    private Date createDt;

    private Date updateDt;

}
