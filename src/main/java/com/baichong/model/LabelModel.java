package com.baichong.model;

import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/10/25 12:18
 */
@Data
public class LabelModel {

    private Long id;

    /**
     * 标签名称
     */
    private String labelName;
}
