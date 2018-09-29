package com.itclj.service.impl;

import com.itclj.common.utils.BeanUtil;
import com.itclj.model.SysUser;
import com.itclj.service.LoginService;
import com.itclj.service.SysPermissionService;
import com.itclj.service.SysUserService;
import com.itclj.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * Create by lujun.chen on 2018/09/29
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserVO getUser(String username, String password) {
        SysUser sysUser = sysUserService.queryByUsernameAndPassword(username, password);
        UserVO userVO = BeanUtil.copyProperties(sysUser, UserVO.class);
        userVO.setPermissions(sysPermissionService.getPermissionCodesByUserId(sysUser.getId()));
        userVO.setToken(getToken(username));

        return userVO;
    }

    private String getToken(String username) {
        return DigestUtils.md5DigestAsHex((username + System.currentTimeMillis()).getBytes());
    }

}
