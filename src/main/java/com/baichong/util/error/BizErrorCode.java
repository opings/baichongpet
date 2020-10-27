package com.baichong.util.error;

import lombok.Getter;

/**
 * @author zyz
 * @since 2020-03-11 13:38
 */
public enum BizErrorCode {


    /**
     * 直播活动不存在
     */
    LIVE_ACTIVITY_NO_EXISTS("LIVE_ACTIVITY_NO_EXISTS", "直播活动不存在"),



    ;

    @Getter
    private String code;

    @Getter
    private String msg;


    BizErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
