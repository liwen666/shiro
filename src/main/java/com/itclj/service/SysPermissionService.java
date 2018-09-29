package com.itclj.service;

import com.itclj.model.SysPermission;

/**
 * 权限
 * Create by lujun.chen on 2018/09/29
 */
public interface SysPermissionService {

    /**
     * 添加权限
     *
     * @param sysPermission 权限
     * @return 权限，有ID了
     */
    SysPermission add(SysPermission sysPermission);

    /**
     * 修改角色
     *
     * @param sysPermission 角色
     * @return 修改后的角色信息
     */
    SysPermission edit(SysPermission sysPermission);
}
