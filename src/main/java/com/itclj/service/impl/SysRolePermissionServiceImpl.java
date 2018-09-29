package com.itclj.service.impl;

import com.itclj.mapper.SysRolePermissionMapper;
import com.itclj.model.SysRolePermission;
import com.itclj.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    @Transactional
    public SysRolePermission add(SysRolePermission sysRolePermission) {
        sysRolePermissionMapper.insertSelective(sysRolePermission);
        return sysRolePermission;
    }
}
