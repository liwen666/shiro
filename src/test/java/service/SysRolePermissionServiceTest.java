package service;

import com.alibaba.fastjson.JSON;
import com.itclj.model.SysRolePermission;
import com.itclj.service.SysRolePermissionService;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by lujun.chen on 2018/09/29
 */
public class SysRolePermissionServiceTest extends BaseTest {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Test
    public void add() {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setPermissionId(1);
        sysRolePermission.setRoleId(1);
        SysRolePermission sysRolePermissionR = sysRolePermissionService.add(sysRolePermission);
        logger.info(JSON.toJSONString(sysRolePermissionR));
    }
}
