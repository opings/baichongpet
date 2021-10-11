package com.baichong.dao.mapper;

import com.baichong.dao.entity.CountSplitSubjectDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangfangyuan
 * @since 2020-03-12 13:31
 */
@Repository
public interface CountSplitSubjectMapper {

    void insertSelective(CountSplitSubjectDO splitCountSubjectDO);

    void updateCountValue(@Param("subjectId") String subjectId, @Param("countValue") int countValue);

    void increaseCountValue(@Param("subjectId") String subjectId, @Param("countValue") int countValue);

    CountSplitSubjectDO selectBySubjectId(@Param("subjectId") String subjectId);


}
