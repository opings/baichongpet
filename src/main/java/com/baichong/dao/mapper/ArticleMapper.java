package com.baichong.dao.mapper;

import com.baichong.dao.entity.ArticleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDO> {

    @Update(value = " update article set heat = heat +1 where id = #{id,jdbcType=BIGINT};")
    void addHeat(Long id);
}
