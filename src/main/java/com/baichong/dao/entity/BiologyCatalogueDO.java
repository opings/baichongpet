package com.baichong.dao.entity;

import com.baichong.model.enums.EncyclopediasCategoryEnum;
import lombok.Data;

import java.util.Date;

/**
 * 生物名录百科
 *
 * @author zhaoyongzhen
 * @since 2020/10/26 17:32
 */
@Data
public class BiologyCatalogueDO {

    private Long id;
    /**
     * id
     */
    private String BiologyCatalogueId;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 图片
     */
    private String img;
    /**
     * 分类
     *
     * @see EncyclopediasCategoryEnum
     */
    private String category;

    private Date createDt;

    private Date updateDt;



}
