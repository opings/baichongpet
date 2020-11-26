package com.baichong.dao.mapper;

import com.baichong.dao.entity.ArticleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<ArticleDO> {

    @Update(value = " update article set heat = heat +1 where article_id = #{articleId,jdbcType=VARCHAR};")
    void addHeat(String articleId);


    List<ArticleDO> listArticleByLabelId(@Param("labelList") List<Long> labelList,
                                         @Param("startIndex") int startIndex,
                                         @Param("pageSize") int pageSize
    );

}
