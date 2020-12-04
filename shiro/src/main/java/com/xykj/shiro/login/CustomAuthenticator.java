package com.xykj.shiro.login;

import com.xykj.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Author: wm
 * @Date: 2020-07-22  14:24
 * @Version 1.0
 */
public class CustomAuthenticator {

    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //添加自定义Realm
        securityManager.setRealm(new CustomRealm());

        //放入安全工具类
        SecurityUtils.setSecurityManager(securityManager);

        //获取主体
        Subject subject = SecurityUtils.getSubject();

        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken("xiaohei","123");

        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (Exception e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
    }
}
