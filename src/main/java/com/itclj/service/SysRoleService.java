package com.itclj.service;

import com.itclj.model.SysRole;

/**
 * 角色
 * Create by lujun.chen on 2018/09/29
 */
public interface SysRoleService {

    /**
     * 添加角色
     *
     * @param sysRole 角色
     * @return 角色信息有ID了哦
     */
    SysRole add(SysRole sysRole);

    /**
     * 修改角色
     *
     * @param sysRole 角色
     * @return 修改后的角色信息
     */
    SysRole edit(SysRole sysRole);

}
