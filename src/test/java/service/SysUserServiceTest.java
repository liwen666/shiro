package service;

import com.alibaba.fastjson.JSON;
import com.itclj.model.SysUser;
import com.itclj.service.SysUserService;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by lujun.chen on 2018/09/29
 */
public class SysUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void add() {
        SysUser sysUser = new SysUser();
        sysUser.setPassword("123456");
        sysUser.setUsername("admin");
        SysUser sysUserR = sysUserService.add(sysUser);
        logger.info(JSON.toJSONString(sysUserR));
    }
}
