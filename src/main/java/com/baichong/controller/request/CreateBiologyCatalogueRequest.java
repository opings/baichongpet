package com.baichong.controller.request;

import com.baichong.model.enums.EncyclopediasCategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaoyongzhen
 * @since 2020/10/27 14:56
 */
@Data
public class CreateBiologyCatalogueRequest {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = true)
    private String introduction;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片", required = false)
    private String img;
    /**
     * 正文
     */
    @ApiModelProperty(value = "正文", required = true)
    private String content;
    /**
     * 分类
     *
     * @see EncyclopediasCategoryEnum
     */
    @ApiModelProperty(value = "分类", required = false)
    private String category;

    /**
     * 标签id 逗号隔开
     */
    @ApiModelProperty(value = "标签id 逗号隔开", required = true)
    private String labelIds;

    /**************************额外标准信息*******************************/
    /**
     * 中文名
     */
    @ApiModelProperty(value = "中文名", required = false)
    private String chineseName;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名", required = false)
    private String alias;
    /**
     * 界
     */
    @ApiModelProperty(value = "界", required = false)
    private String kingdom;
    /**
     * 门
     */
    @ApiModelProperty(value = "门", required = false)
    private String phylum;
    /**
     * 亚门
     */
    @ApiModelProperty(value = "亚门", required = false)
    private String subPhylum;

    /**
     * 纲
     */
    @ApiModelProperty(value = "纲", required = false)
    private String biologyClass;
    /**
     * 亚纲
     */
    @ApiModelProperty(value = "亚纲", required = false)
    private String biologySubClass;
    /**
     * 目
     */
    @ApiModelProperty(value = "目", required = false)
    private String order;
    /**
     * 亚目
     */
    @ApiModelProperty(value = "亚目", required = false)
    private String subOrder;
    /**
     * 亚目
     */
    @ApiModelProperty(value = "亚目", required = false)
    private String family;
    /**
     * 亚科
     */
    @ApiModelProperty(value = "亚科", required = false)
    private String subFamily;
    /**
     * 族
     */
    @ApiModelProperty(value = "族", required = false)
    private String race;
    /**
     * 亚族
     */
    @ApiModelProperty(value = "亚族", required = false)
    private String subRace;
    /**
     * 属
     */
    @ApiModelProperty(value = "属", required = false)
    private String genus;
    /**
     * 亚属
     */
    @ApiModelProperty(value = "亚属", required = false)
    private String subGenus;
    /**
     * 种
     */
    @ApiModelProperty(value = "种", required = false)
    private String species;
    /**
     * 亚种
     */
    @ApiModelProperty(value = "亚种", required = false)
    private String subSpecies;
    /**************************额外信息*******************************/


}
