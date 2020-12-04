package com.xykj.springshiro.config;

import com.xykj.springshiro.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置
 * @Author: wm
 * @Date: 2020-07-23  15:41
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    //1.创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源和公共资源
        Map<String,String> map = new HashMap<>();
        map.put("/user/**","anon");
        map.put("/register.jsp","anon");
        map.put("/**","authc");
        //设置默认页面
        //shiroFilterFactoryBean.setLoginUrl("login.jsp");
        //配置认证和授权规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建web安全管理器
    @Bean
    public DefaultWebSecurityManager getSecurityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入realm
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    //3.创建自定义realm
    @Bean
    public CustomRealm getRealm(){
        CustomRealm customRealm = new CustomRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列
        credentialsMatcher.setHashIterations(1024);
        customRealm.setCredentialsMatcher(credentialsMatcher);

        //设置EhCacheManage
        customRealm.setCachingEnabled(true); //开启缓存
        customRealm.setCacheManager(new EhCacheManager()); //设置缓存管理器
        customRealm.setAuthenticationCachingEnabled(true);//开启认证缓存
        customRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        return customRealm;
    }
}
