package com.baichong.model.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zyz
 * @since 2020-04-06 00:45
 */
public enum EncyclopediasCategoryEnum {

    /**
     * 动物
     */

    animal("RECOMMEND", "推荐"),


    /**
     * 植物
     */

    botany("RECOMMEND", "推荐"),


    /**
     * 推荐
     */

    microorganism("RECOMMEND", "推荐"),


    /**
     * 推荐
     */

    RECOMMEND("RECOMMEND", "推荐"),

;

    @Getter
    private String code;

    @Getter
    private String desc;

    EncyclopediasCategoryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EncyclopediasCategoryEnum getByCode(String code) {
        return Arrays.stream(EncyclopediasCategoryEnum.values()).filter(item ->
                item.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
