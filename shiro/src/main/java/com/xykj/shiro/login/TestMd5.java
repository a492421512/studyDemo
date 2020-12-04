package com.xykj.shiro.login;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Author: wm
 * @Date: 2020-07-22  16:21
 * @Version 1.0
 */
public class TestMd5 {
    public static void main(String[] args) {
        //使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        //获得加密后的16进制
        System.out.println(md5Hash.toHex()); //202cb962ac59075b964b07152d234b70

        //使用md5+salt
        Md5Hash md5Hash1 = new Md5Hash("123","000"); //3c6fcccf800b9652d2ac85de6c108c86
        System.out.println(md5Hash1.toHex());

        //使用md5+salt+hash散列
        Md5Hash md5Hash2 = new Md5Hash("123","000",1024); //3cd88bcf2cde33e14fd7651731b2e0ff
        System.out.println(md5Hash2.toHex());
    }
}
