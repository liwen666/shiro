package com.itclj.service.impl;

import com.itclj.mapper.SysRoleMapper;
import com.itclj.model.SysRole;
import com.itclj.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional
    public SysRole add(SysRole sysRole) {
        sysRoleMapper.insertSelective(sysRole);
        return sysRole;
    }

    @Override
    @Transactional
    public SysRole edit(SysRole sysRole) {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return sysRole;
    }
}
