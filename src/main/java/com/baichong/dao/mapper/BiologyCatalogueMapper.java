package com.baichong.dao.mapper;

import com.baichong.dao.entity.BiologyCatalogueDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface BiologyCatalogueMapper {

    int insert(BiologyCatalogueDO biologyCatalogueDO);

    BiologyCatalogueDO selectByBiologyCatalogueId(@Param("biologyCatalogueId") String biologyCatalogueId);


}
