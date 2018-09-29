package service;

import com.alibaba.fastjson.JSON;
import com.itclj.model.SysPermission;
import com.itclj.service.SysPermissionService;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by lujun.chen on 2018/09/29
 */
public class SysPermissionServiceTest extends BaseTest {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    public void add() {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setCode("user:add");
        sysPermission.setName("添加用户");
        SysPermission sysPermissionR = sysPermissionService.add(sysPermission);
        logger.info(JSON.toJSONString(sysPermissionR));
    }
}
