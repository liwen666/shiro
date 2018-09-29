package service;

import com.alibaba.fastjson.JSON;
import com.itclj.model.SysRole;
import com.itclj.service.SysRoleService;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by lujun.chen on 2018/09/29
 */
public class SysRoleServiceTest extends BaseTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setCode("user");
        sysRole.setName("用户管理");
        SysRole sysRoleR = sysRoleService.add(sysRole);
        logger.info(JSON.toJSONString(sysRoleR));
    }
}
