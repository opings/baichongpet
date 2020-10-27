package com.baichong.dao.mapper;

import com.baichong.dao.entity.LabelDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface LabelMapper {

    int insert(LabelDO labelDO);

    LabelDO selectById(@Param("id") Long id);

    List<LabelDO> listLabel(@Param("startIndex") int startIndex,
                            @Param("pageSize") int pageSize);


}
