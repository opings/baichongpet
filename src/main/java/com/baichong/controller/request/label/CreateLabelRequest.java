package com.baichong.controller.request.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 11:52
 */
@Data
public class CreateLabelRequest implements Serializable {


    private static final long serialVersionUID = 9017907056902054976L;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", required = true)
    private String labelName;


}
