package com.xykj.springshiro.shiro;

import com.xykj.springshiro.entity.Permission;
import com.xykj.springshiro.entity.Role;
import com.xykj.springshiro.entity.User;
import com.xykj.springshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义realm
 *
 * @Author: wm
 * @Date: 2020-07-23  15:46
 * @Version 1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        //获取用户名
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserRoleByName(primaryPrincipal);

        if (!CollectionUtils.isEmpty(user.getRoles())) {
            //获取该用户的所有角色信息
            List<String> roleList = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            log.info("用户："+user.getUsername()+"拥有的角色有----"+roleList);
            //配置权限
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(roleList);
            roleList.forEach(roleName -> {
                List<Permission> userPermission = userService.findUserPermission(roleName);
                if (!CollectionUtils.isEmpty(userPermission)){
                    //获取该角色的所有权限
                    List<String> permissionNames = userPermission.stream().map(Permission::getName).collect(Collectors.toList());
                    authorizationInfo.addStringPermissions(permissionNames);
                }
            });
            return authorizationInfo;
        }
        log.info(user.getUsername()+"还未拥有角色");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户信息
        String principal = (String) token.getPrincipal();
        User user = userService.findUserByName(principal);
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}
