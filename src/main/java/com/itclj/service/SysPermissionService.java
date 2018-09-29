package com.itclj.service;

import com.itclj.model.SysPermission;

import java.util.List;

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

    /**
     * 通用户ID获取权限
     *
     * @param userid 用户ID
     * @return
     */
    List<SysPermission> queryByUserId(Integer userid);

    /**
     * 通过用户ID获取权限编码
     *
     * @param userid 用户ID
     * @return
     */
    List<String> getPermissionCodesByUserId(Integer userid);
}
