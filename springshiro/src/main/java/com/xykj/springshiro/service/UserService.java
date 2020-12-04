package com.xykj.springshiro.service;

import com.xykj.springshiro.entity.Permission;
import com.xykj.springshiro.entity.User;

import java.util.List;

/**
 * @Author: wm
 * @Date: 2020-07-24  10:26
 * @Version 1.0
 */
public interface UserService {

    /**
     * 注册
     * @param user
     */
    void register(User user);

    /**
     * 查询用户
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    User findUserRoleByName(String username);

    /**
     * 根据角色名查询权限
     * @param roleName
     * @return
     */
    List<Permission> findUserPermission(String roleName);
}
