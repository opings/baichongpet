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

    ANIMAL("ANIMAL", "动物"),


    /**
     * 植物
     */

    BOTANY("BOTANY", "植物"),

    /**
     * 真菌
     */
    FUNGUS("FUNGUS", "真菌"),

    /**
     * 病毒
     */
    VIRUS("VIRUS", "病毒"),

    /**
     * 原核生物
     */
    PROCARYOTIC_ORGANISM("PROCARYOTIC_ORGANISM", "原核生物"),

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
