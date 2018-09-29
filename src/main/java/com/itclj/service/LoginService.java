package com.itclj.service;

import com.itclj.model.SysUser;

/**
 * Create by lujun.chen on 2018/09/29
 */
public interface LoginService {

    /**
     * 获取用户信息
     *
     * @param username 用户信息
     * @param password 密码
     * @return
     */
    SysUser getUser(String username, String password);
}
