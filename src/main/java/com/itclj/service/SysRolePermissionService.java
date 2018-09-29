package com.itclj.service;

import com.itclj.model.SysRolePermission;

/**
 * 角色关联权限
 * Create by lujun.chen on 2018/09/29
 */
public interface SysRolePermissionService {

    /**
     * 角色关联权限
     *
     * @param sysRolePermission 角色关联的权限
     * @return 角色关联权限，带ID的
     */
    SysRolePermission add(SysRolePermission sysRolePermission);
}
