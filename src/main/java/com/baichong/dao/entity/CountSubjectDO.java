package com.baichong.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CountSubjectDO {

    private Long id;

    private String outBizNo;

    private String outBizType;

    private String subjectId;

    private Integer splitSubjectCount;

    private String splitSubjectId;

    private String parentSubjectId;

    private Date createDt;

    private Date updateDt;


}
