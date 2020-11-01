package com.baichong.controller.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.TimeZone;

/**
 * @author zyz
 * @since 2020-05-21 16:07
 */
@Data
public class BaseRequest {

    /**
     * 设备编号
     */
    private Long clientId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 平台 android ios h5 小程序
     */
    private String platform;

    /**
     * 版本号
     */
    private String version;

    /**
     * 来源
     */
    private String source;

    /**
     * 时区
     */
    private String time_zone;


    private String client;


    public TimeZone getTimeZone() {
        if (StringUtils.isBlank(time_zone)) {
            return TimeZone.getDefault();
        }
        return TimeZone.getTimeZone(time_zone);
    }
}
