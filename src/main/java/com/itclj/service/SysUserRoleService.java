package com.itclj.service;

import com.itclj.model.SysUserRole;

/**
 * Create by lujun.chen on 2018/09/29
 */
public interface SysUserRoleService {

    /**
     * 用户管理角色
     *
     * @param sysUserRole 用户角色
     * @return 用户角色
     */
    SysUserRole add(SysUserRole sysUserRole);
}
