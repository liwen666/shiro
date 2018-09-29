package com.itclj.service;

import com.itclj.model.SysRolePermission;

import java.util.List;


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

    /**
     * 通过角色ID获取权限
     *
     * @param roleids 角色ID
     * @return
     */
    List<SysRolePermission> queryByRoleIds(List<Integer> roleids);

    /**
     * 通过角色ID获取权限ID
     *
     * @param roleids 角色ID
     * @return
     */
    List<Integer> getPermissionIdsByRoleIds(List<Integer> roleids);
}
