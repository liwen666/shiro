package com.itclj.service;

import com.itclj.model.SysUser;

import java.util.List;

/**
 * Create by lujun.chen on 2018/09/29
 */
public interface SysUserService {

    /**
     * 新增用户
     *
     * @param sysUser 用户信息
     * @return 用户信息，带ID
     */
    SysUser add(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser 用户信息
     * @return 修改后的用户信息
     */
    SysUser edit(SysUser sysUser);

    /**
     * 根据用户ID获取用户详细信息
     *
     * @param userid 用户ID
     * @return 用户详细信息
     */
    SysUser detail(Integer userid);

    /**
     * 查询用户列表
     *
     * @param sysUser 查询条件
     * @return 用户列表
     */
    List<SysUser> query(SysUser sysUser);

    /**
     * 通过用户名密码获取用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser queryByUsernameAndPassword(String username, String password);
}
