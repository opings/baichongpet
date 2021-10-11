package com.baichong.model;

import com.baichong.util.AssertUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import lombok.Getter;
import lombok.ToString;

@ToString
public class OutBizKey {

    /**
     * 外部业务类型
     */
    @Getter
    private String outBizType;


    /**
     * 外部业务单号
     */
    @Getter
    private String outBizNo;

    @JsonCreator
    public OutBizKey(@JsonProperty("outBizType") String outBizType, @JsonProperty("outBizNo") String outBizNo) {

        AssertUtil.notBlank(outBizType, () -> "outBizType empty");
        AssertUtil.notBlank(outBizNo, () -> "outBizNo empty");

        this.outBizType = outBizType;
        this.outBizNo = outBizNo;
    }

    public String getKey() {

        return Joiner.on(":").join(outBizType, outBizNo);
    }


}
