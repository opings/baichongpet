package com.baichong.model.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zyz
 * @since 2020-04-06 00:45
 */
public enum LabelTargetTypeEnum {

    /**
     * 文章标签
     */
    ARTICLE_TAG("ARTICLE_TAG", "文章标签"),

    /**
     * 百科标签
     */
    ENCYCLOPEDIAS_TAG("ENCYCLOPEDIAS_TAG", "百科标签"),

    /**
     * 百科分类
     */
    ENCYCLOPEDIAS_CATEGORY("ENCYCLOPEDIAS_CATEGORY", "百科分类"),
    ;

    @Getter
    private String code;

    @Getter
    private String desc;

    LabelTargetTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static LabelTargetTypeEnum getByCode(String code) {
        return Arrays.stream(LabelTargetTypeEnum.values()).filter(item ->
                item.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
