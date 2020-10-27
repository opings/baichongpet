package com.baichong.dao.mapper;

import com.baichong.dao.entity.ArticleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface ArticleMapper {

    int insert(ArticleDO articleDO);

    ArticleDO selectByArticleId(@Param("articleId") String articleId);

    List<ArticleDO> listByCategory(@Param("category") String category,
                                     @Param("startIndex") int startIndex,
                                     @Param("pageSize") int pageSize);


}
