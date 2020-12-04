package com.xykj.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: wm
 * @Date: 2020-07-22  15:44
 * @Version 1.0
 */

//md5自定义的realm
public class CustomMd5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===============进行授权============");
        //获得登录信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("primaryPrincipal:"+primaryPrincipal);
        //创建返回对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置角色
        simpleAuthorizationInfo.addRole("admin");
        //设置资源管理
        simpleAuthorizationInfo.addStringPermission("user:*");
        simpleAuthorizationInfo.addStringPermission("order:create:*");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token数据
        String principal = (String)token.getPrincipal();

        if ("zhangsan".equals(principal)){
            System.out.println("用户名正确");
            String password = "3cd88bcf2cde33e14fd7651731b2e0ff";
            String salt = "000";
            //参数1：用户名  参数2：数据库密码    参数3：盐   参数4：自定义Realm
            return new SimpleAuthenticationInfo(principal,password, ByteSource.Util.bytes(salt),this.getName());
        }
        return null;
    }
}
