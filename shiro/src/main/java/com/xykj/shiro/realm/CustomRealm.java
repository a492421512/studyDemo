package com.xykj.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: wm
 * @Date: 2020-07-22  14:20
 * @Version 1.0
 */
//自定义realm
public class CustomRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取主要的信息
        String principal = (String)token.getPrincipal();
        //进行用户名认证
        if ("xiaohei".equals(principal)){
            System.out.println("用户名正确");
            //返回AuthenticationInfo实现类 参数1：用户名 参数2：数据库的密码 参数3：realm的名称
            return  new SimpleAuthenticationInfo(principal,"123",this.getName());
        }
        return null;
    }
}
