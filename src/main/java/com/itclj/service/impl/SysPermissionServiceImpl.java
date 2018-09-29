package com.itclj.service.impl;

import com.itclj.mapper.SysPermissionMapper;
import com.itclj.model.SysPermission;
import com.itclj.model.SysPermissionExample;
import com.itclj.service.SysPermissionService;
import com.itclj.service.SysRolePermissionService;
import com.itclj.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    @Transactional
    public SysPermission add(SysPermission sysPermission) {
        sysPermissionMapper.insertSelective(sysPermission);
        return sysPermission;
    }

    @Override
    @Transactional
    public SysPermission edit(SysPermission sysPermission) {
        sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        return sysPermission;
    }

    @Override
    public List<SysPermission> queryByUserId(Integer userid) {

        List<Integer> roleIdList = sysUserRoleService.getRoleIdsByUserId(userid);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return null;
        }

        List<Integer> permissionIds = sysRolePermissionService.getPermissionIdsByRoleIds(roleIdList);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }

        SysPermissionExample permissionExample = new SysPermissionExample();
        permissionExample.createCriteria().andIdIn(permissionIds);
        return sysPermissionMapper.selectByExample(permissionExample);
    }

    @Override
    public List<String> getPermissionCodesByUserId(Integer userid) {
        List<SysPermission> sysPermissionList = queryByUserId(userid);
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            return null;
        }
        List<String> permissionCodes = new ArrayList<>();
        for (SysPermission sysPermission : sysPermissionList) {
            permissionCodes.add(sysPermission.getCode());
        }
        return permissionCodes;
    }
}
