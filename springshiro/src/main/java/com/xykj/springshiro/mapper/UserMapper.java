package com.xykj.springshiro.mapper;

import com.xykj.springshiro.entity.Permission;
import com.xykj.springshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: wm
 * @Date: 2020-07-24  10:24
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    Integer save(User user);

    User findUserByName(String username);

    User findUserRoleByName(String username);

    List<Permission> findUserPermission(String roleName);
}
