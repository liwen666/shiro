package com.itclj.service.impl;

import com.itclj.mapper.SysRolePermissionMapper;
import com.itclj.model.SysRolePermission;
import com.itclj.model.SysRolePermissionExample;
import com.itclj.service.SysRolePermissionService;
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
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    @Transactional
    public SysRolePermission add(SysRolePermission sysRolePermission) {
        sysRolePermissionMapper.insertSelective(sysRolePermission);
        return sysRolePermission;
    }

    @Override
    public List<SysRolePermission> queryByRoleIds(List<Integer> roleids) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdIn(roleids);
        return sysRolePermissionMapper.selectByExample(example);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleids) {
        List<SysRolePermission> sysRolePermissionList = queryByRoleIds(roleids);
        if (CollectionUtils.isEmpty(sysRolePermissionList)) {
            return null;
        }
        List<Integer> permissionIds = new ArrayList<>();
        for (SysRolePermission sysRolePermission : sysRolePermissionList) {
            permissionIds.add(sysRolePermission.getPermissionId());
        }
        return permissionIds;
    }
}
