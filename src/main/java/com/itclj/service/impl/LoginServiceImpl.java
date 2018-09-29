package com.itclj.service.impl;

import com.itclj.model.SysUser;
import com.itclj.service.LoginService;
import com.itclj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    public SysUser getUser(String username, String password) {
        return sysUserService.queryByUsernameAndPassword(username, password);
    }
}
