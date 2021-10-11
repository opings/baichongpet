package com.baichong.dao.mapper;

import com.baichong.dao.entity.CountRecordDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计数流水
 *
 * @since 2020-03-15 23:10
 */
@Repository
public interface CountRecordMapper {

    int insertSelective(CountRecordDO record);

    List<CountRecordDO> selectListBySubjectId(@Param("subjectId") String subjectId);

    CountRecordDO selectByIdempotentNo(@Param("subjectId") String subjectId, @Param("idempotentNo") String idempotentNo);

    CountRecordDO selectByIdempotentNoForUpdate(@Param("subjectId") String subjectId, @Param("idempotentNo") String idempotentNo);

    int updateCountValue(@Param("subjectId") String subjectId, @Param("idempotentNo") String idempotentNo, @Param("countValue") Integer countValue);

}
