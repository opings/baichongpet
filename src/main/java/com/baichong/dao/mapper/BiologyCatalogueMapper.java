package com.baichong.dao.mapper;

import com.baichong.dao.entity.BiologyCatalogueDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface BiologyCatalogueMapper {

    int insert(BiologyCatalogueDO biologyCatalogueDO);

    BiologyCatalogueDO selectByBiologyCatalogueId(@Param("biologyCatalogueId") String biologyCatalogueId);

    List<BiologyCatalogueDO> listBiologyCatalogue(
            @Param("category") String category,
            @Param("labelList") List<String> labelList,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);

}
