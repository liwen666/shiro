package com.itclj.service.impl;

import com.itclj.mapper.SysUserRoleMapper;
import com.itclj.model.SysUserRole;
import com.itclj.model.SysUserRoleExample;
import com.itclj.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SysUserRole> queryByUserId(Integer userid) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userid);
        return sysUserRoleMapper.selectByExample(example);
    }

    @Override
    public List<Integer> getRoleIdsByUserId(Integer userid) {
        List<SysUserRole> sysUserRoleList = queryByUserId(userid);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            return null;
        }
        List<Integer> roleIds = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            roleIds.add(sysUserRole.getRoleId());
        }
        return roleIds;
    }
}
