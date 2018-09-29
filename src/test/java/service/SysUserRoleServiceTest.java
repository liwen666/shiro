package service;

import com.alibaba.fastjson.JSON;
import com.itclj.model.SysUserRole;
import com.itclj.service.SysUserRoleService;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by lujun.chen on 2018/09/29
 */
public class SysUserRoleServiceTest extends BaseTest {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    public void add() {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(1);
        sysUserRole.setUserId(1);
        SysUserRole sysUserRoleR = sysUserRoleService.add(sysUserRole);
        logger.info(JSON.toJSONString(sysUserRoleR));
    }
}
