package com.itclj.controller;

import com.itclj.common.entity.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by lujun.chen on 2018/09/29
 */
@RestController
public class MainController {

    @RequestMapping("/login")
    public ResponseData login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return new ResponseData();
        } catch (AuthenticationException e) {
            return new ResponseData("登录失败");
        }
    }
}
