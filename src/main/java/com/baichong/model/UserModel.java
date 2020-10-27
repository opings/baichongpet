package com.baichong.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyongzhen
 * @since 2020/10/22 16:36
 */
@Data
public class UserModel implements Serializable{
    private static final long serialVersionUID = 6466491587185623335L;

    private Long userId;

    private String userName;

    private String password;

    private String phone;
}
