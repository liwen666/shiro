package com.itclj.controller;

import com.itclj.common.entity.ResponseData;
import com.itclj.model.SysUser;
import com.itclj.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequiresPermissions("user:add")
    public ResponseData add(@RequestBody SysUser sysUser) {
        return new ResponseData(sysUserService.add(sysUser));
    }

    /**
     * 获取用户详细
     *
     * @param userid 用户ID
     * @return
     */
    @GetMapping("/{userid}")
    @RequiresPermissions("user:detail")
    public ResponseData detail(@PathVariable("userid") Integer userid) {
        return new ResponseData(sysUserService.detail(userid));
    }
}
