package com.itclj.service;

import com.itclj.vo.UserVO;

/**
 * Create by lujun.chen on 2018/09/29
 */
public interface LoginService {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UserVO getUser(String username, String password);
}
