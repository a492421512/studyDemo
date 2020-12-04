package com.xykj.springshiro.service;

import cn.hutool.core.util.IdUtil;
import com.xykj.springshiro.entity.Permission;
import com.xykj.springshiro.entity.User;
import com.xykj.springshiro.mapper.UserMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wm
 * @Date: 2020-07-24  10:27
 * @Version 1.0
 */
@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) {
        //生成随机盐
        String salt = IdUtil.simpleUUID();
        user.setSalt(salt);
        //生成加密密码
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public User findUserRoleByName(String username) {
        return userMapper.findUserRoleByName(username);
    }

    @Override
    public List<Permission> findUserPermission(String roleName) {
        return userMapper.findUserPermission(roleName);
    }
}
