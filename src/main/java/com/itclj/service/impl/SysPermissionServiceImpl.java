package com.itclj.service.impl;

import com.itclj.mapper.SysPermissionMapper;
import com.itclj.model.SysPermission;
import com.itclj.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

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
}
