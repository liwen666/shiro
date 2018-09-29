package com.itclj.controller;

import com.itclj.model.SysUser;
import com.itclj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 * Create by lujun.chen on 2018/09/29
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 用户信息
     */
    @PostMapping("/add")
    public SysUser add(SysUser sysUser) {
        return sysUserService.add(sysUser);
    }
}
