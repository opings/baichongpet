package com.baichong.dao.mapper;

import com.baichong.dao.entity.CountSubjectDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangfangyuan
 * @since 2020-03-12 13:31
 */
@Repository
public interface CountSubjectMapper {

    void insertSelective(CountSubjectDO countSubjectDO);


    CountSubjectDO selectByOutBizKey(@Param("outBizType") String outBizType, @Param("outBizNo") String outBizNo);

    CountSubjectDO selectBySubjectId(@Param("subjectId") String subjectId);

    CountSubjectDO selectBySubjectIdForUpdate(@Param("subjectId") String subjectId);

    void updateSplitSubjectId(@Param("subjectId") String subjectId, @Param("splitSubjectId") String splitSubjectId);


}
