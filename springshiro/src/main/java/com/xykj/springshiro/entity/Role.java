package com.xykj.springshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色表
 * @Author: wm
 * @Date: 2020-07-24  15:40
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer id;

    private String name;

    /**
     * 权限
     */
    List<Permission> permissions;
}
