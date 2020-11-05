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
     * 正文
     */
    private String content;
    /**
     * 分类
     *
     * @see EncyclopediasCategoryEnum
     */
    private String category;

    private Date createDt;

    private Date updateDt;

    /**************************额外标准信息*******************************/
    /**
     * 中文名
     */
    private String chineseName;
    /**
     * 别名
     */
    private String alias;
    /**
     * 界
     */
    private String kingdom;
    /**
     * 门
     */
    private String phylum;
    /**
     * 亚门
     */
    private String subPhylum;
    /**
     * 纲
     */
    private String biologyClass;
    /**
     * 亚纲
     */
    private String biologySubClass;
    /**
     * 目
     */
    private String orders;
    /**
     * 亚目
     */
    private String subOrder;
    /**
     * 科
     */
    private String family;
    /**
     * 亚科
     */
    private String subFamily;
    /**
     * 族
     */
    private String race;
    /**
     * 亚族
     */
    private String subRace;
    /**
     * 属
     */
    private String genus;
    /**
     * 亚属
     */
    private String subGenus;
    /**
     * 种
     */
    private String species;
    /**
     * 亚种
     */
    private String subSpecies;
    /**************************额外信息*******************************/



}
