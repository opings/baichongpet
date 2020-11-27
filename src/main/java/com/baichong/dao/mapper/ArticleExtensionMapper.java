package com.baichong.dao.mapper;

import com.baichong.dao.entity.ArticleExtensionDO;
import com.baichong.dao.entity.BiologyCatalogueExtensionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface ArticleExtensionMapper {

    int insert(ArticleExtensionDO articleExtensionDO);

    ArticleExtensionDO selectByArticleId(@Param("articleId") String articleId);


}
