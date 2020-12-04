package com.xykj.springshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户表
 * @Author: wm
 * @Date: 2020-07-24  10:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private String salt;

    private List<Role> roles;
}
