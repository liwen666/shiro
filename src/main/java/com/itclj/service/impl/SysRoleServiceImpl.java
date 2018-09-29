package com.itclj.service.impl;

import com.itclj.mapper.SysRoleMapper;
import com.itclj.model.SysRole;
import com.itclj.model.SysRoleExample;
import com.itclj.model.SysUserRole;
import com.itclj.service.SysRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

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

    @Override
    public List<SysRole> queryByUserId(Integer userid) {

        List<SysUserRole> sysUserRoleList = sysUserRoleService.queryByUserId(userid);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            return null;
        }

        List<Integer> roleIdList = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            roleIdList.add(sysUserRole.getRoleId());
        }
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        return sysRoleMapper.selectByExample(roleExample);
    }
}
