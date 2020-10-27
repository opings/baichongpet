package com.baichong.controller;

import com.baichong.dao.entity.UserDO;
import com.baichong.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:40
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }


    @ResponseBody
    @RequestMapping(value = "/selectUsers", method = RequestMethod.GET)
    public List<UserDO> selectUsers(){
        return userMapper.selectUsers();
    }

}
