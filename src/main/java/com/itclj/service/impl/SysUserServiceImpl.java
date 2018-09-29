package com.itclj.service.impl;

import com.itclj.mapper.SysUserMapper;
import com.itclj.model.SysUser;
import com.itclj.model.SysUserExample;
import com.itclj.service.SysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public SysUser add(SysUser sysUser) {
        sysUserMapper.insertSelective(sysUser);
        return sysUser;
    }

    @Override
    @Transactional
    public SysUser edit(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return sysUser;
    }

    @Override
    public SysUser detail(Integer userid) {
        return sysUserMapper.selectByPrimaryKey(userid);
    }

    @Override
    public List<SysUser> query(SysUser sysUser) {
        SysUserExample example = new SysUserExample();
        return sysUserMapper.selectByExample(example);
    }

    public SysUser queryByUsernameAndPassword(String username, String password) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().
                andUsernameEqualTo(username).
                andPasswordEqualTo(password);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysUsers)) {
            return sysUsers.get(0);
        }
        return null;
    }
}
