package com.xykj.shiro.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Author: wm
 * @Date: 2020-07-22  10:34
 * @Version 1.0
 */
public class TestAuthenticator {
    public static void main(String[] args) {

        //1.创建安全管理器 SecurityManager  DefaultSecurityManager实现了SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.设置Realm
        securityManager.setRealm(new IniRealm("classpath:user.ini"));

        //3.将安装工具类中设置默认安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //4.获取主体对象
        Subject subject = SecurityUtils.getSubject();

        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaohei","213");

        //登录失败会报异常，登录成功不会显示
        //常见异常 UnknownAccountException 用户不存在  IncorrectCredentialsException 错误凭证异常（密码错误）
        try{
            subject.login(token);
            System.out.println("登录成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("登录失败");
        }

    }
}
