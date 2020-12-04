package com.xykj.springshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表
 * @Author: wm
 * @Date: 2020-07-24  15:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    private Integer id;

    private String name;

    private String url;
}
