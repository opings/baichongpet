package com.baichong.dao.mapper;

import com.baichong.dao.entity.LabelRelationDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface LabelRelationMapper {

    int insert(LabelRelationDO labelRelationDO);


    List<LabelRelationDO> listByArticleId(@Param("targetType") String targetType,
                                          @Param("targetId") String targetId,
                                          @Param("startIndex") int startIndex,
                                          @Param("pageSize") int pageSize);

    List<LabelRelationDO> listByLabelId(@Param("targetType") String targetType,
                                        @Param("labelId") Long labelId,
                                        @Param("startIndex") int startIndex,
                                        @Param("pageSize") int pageSize);

}
