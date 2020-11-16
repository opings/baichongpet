package com.baichong.dao.mapper;

import com.baichong.dao.entity.LabelDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 保证标签领域有统一收口
 * 其他领域service曾统一调用com.baichong.service.LabelService
 *
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface LabelMapper {

    Long insert(LabelDO labelDO);

    LabelDO selectById(@Param("id") Long id);

    List<LabelDO> listLabel(@Param("startIndex") int startIndex,
                            @Param("pageSize") int pageSize);


}
