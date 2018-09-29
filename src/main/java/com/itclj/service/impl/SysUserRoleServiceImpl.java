package com.itclj.service.impl;

import com.itclj.mapper.SysUserRoleMapper;
import com.itclj.model.SysUserRole;
import com.itclj.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole add(SysUserRole sysUserRole) {
        sysUserRoleMapper.insertSelective(sysUserRole);
        return sysUserRole;
    }
}
