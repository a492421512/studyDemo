package com.xykj.shiro.login;

import com.xykj.shiro.realm.CustomMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @Author: wm
 * @Date: 2020-07-22  16:12
 * @Version 1.0
 */

//md5认证
public class CustomMd5Authenticator {

    public static void main(String[] args) {

        //创建安全管理对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        CustomMd5Realm customMd5Realm = new CustomMd5Realm();
        //设置md5加密
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列
        credentialsMatcher.setHashIterations(1024);
        //自定义realm添加算法
        customMd5Realm.setCredentialsMatcher(credentialsMatcher);
        //配置自定义Realm
        securityManager.setRealm(customMd5Realm);
        //将安全管理对象注入工具类
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //生成token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        //登录
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
        }

        System.out.println("================");
        //判断是否认证通过
        if (subject.isAuthenticated()){
            //是否是这个角色
            boolean role = subject.hasRole("admin");
            System.out.println("是否是这个角色"+role);
            //是否满足以下角色
            boolean allRoles = subject.hasAllRoles(Arrays.asList("admin", "user", "super"));
            System.out.println("是否满足以下角色"+allRoles);
            //满足哪些角色
            boolean[] roles = subject.hasRoles(Arrays.asList("admin", "user", "super"));
            for (boolean b : roles) {
                System.out.println(b);
            }

            System.out.println("===================");
            //是否允许控制资源
            boolean permitted = subject.isPermitted("user:create:01");
            System.out.println("是否允许控制资源"+permitted);
            //是否是同时允许控制以下资源
            boolean permittedAll = subject.isPermittedAll("user:update", "order:create:001", "product:update");
            System.out.println("是否是同时允许控制以下资源"+permittedAll);
            //可以控制哪些资源
            boolean[] permitteds = subject.isPermitted("user:update", "order:create:001", "product:update");
            for (boolean b : permitteds) {
                System.out.println(b);
            }

        }
    }
}
