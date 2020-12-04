package com.xykj.springshiro.controller;

import com.xykj.springshiro.entity.User;
import com.xykj.springshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: wm
 * @Date: 2020-07-23  17:57
 * @Version 1.0
 */
@RequestMapping("user")
@Controller
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(User user){
        try {
            userService.register(user);
            log.info("注册成功");
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册失败"+e);
            return "redirect:/register.jsp";
        }
    }

    /**
     * 用户认证
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        //生成token
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            log.error("登录失败,用户名错误");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            log.error("登录失败,密码错误");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
}
