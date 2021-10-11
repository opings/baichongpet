package com.baichong.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CountSplitSubjectDO {

    private Long id;

    private String subjectId;

    private Integer countValue;

    private String parentSubjectId;

    private Date createDt;

    private Date updateDt;


}
