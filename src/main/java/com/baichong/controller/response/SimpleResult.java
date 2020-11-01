package com.baichong.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyongzhen
 * @since 2020/10/31 11:45
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleResult<T> implements Serializable {


    private static String successCode = "200";

    private String code;

    private String msg;

    private T data;


    public static <T> SimpleResult<T> success(T result) {
        SimpleResult<T> simpleResult = new SimpleResult<>();
        simpleResult.setCode(successCode);
        simpleResult.setData(result);
        return simpleResult;
    }

    public static <T> SimpleResult<T> failure(String errorCode, String errorMsg) {
        SimpleResult<T> simpleResult = new SimpleResult<>();
        simpleResult.setCode(errorCode);
        simpleResult.setMsg(errorMsg);
        return simpleResult;
    }


}

