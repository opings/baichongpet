package com.baichong.dao.mapper;

import com.baichong.dao.entity.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:48
 */
@Repository
public interface UserMapper {

    int insert(UserDO userDO);

    List<UserDO> selectUsers();
}
