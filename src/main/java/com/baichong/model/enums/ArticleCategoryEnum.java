package com.baichong.model.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zyz
 * @since 2020-04-06 00:45
 */
public enum ArticleCategoryEnum {

    /**
     * 推荐
     */

    RECOMMEND("RECOMMEND", "推荐"),

    /**
     * 佰宠热点
     */
    BAI_CHONG_RE_DIAN("BAI_CHONG_RE_DIAN", "佰宠热点"),

    /**
     * 佰宠健康
     */
    BAI_CHONG_JIAN_KANG("BAI_CHONG_JIAN_KANG", "佰宠健康"),

    /**
     * 养宠常识
     */
    YANG_CHONG_CHANG_SHI("YANG_CHONG_CHANG_SHI", "养宠常识"),

    /**
     * 宠物饮食
     */
    CHONG_WU_YIN_SHI("CHONG_WU_YIN_SHI", "宠物饮食"),

    /**
     * 宠物训练
     */
    CHONG_WU_XUN_LIAN("CHONG_WU_XUN_LIAN", "宠物训练"),

    /**
     * 佰宠故事
     */
    BAI_CHONG_GU_SHI("BAI_CHONG_GU_SHI", "佰宠故事"),;

    @Getter
    private String code;

    @Getter
    private String desc;

    ArticleCategoryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ArticleCategoryEnum getByCode(String code) {
        return Arrays.stream(ArticleCategoryEnum.values()).filter(item ->
                item.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
