package com.itclj.controller;

import com.itclj.common.Constants;
import com.itclj.common.entity.ResponseData;
import com.itclj.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

/**
 * Create by lujun.chen on 2018/09/29
 */
@RestController
public class MainController {

    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping("/login")
    public ResponseData login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            Session session = SecurityUtils.getSubject().getSession();
            UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_INFO);
            jedisCluster.setex(Constants.REDIS_KEY_PREFIX_SHIRO_TOKEN + userVO.getToken(),
                    Constants.REDIS_SHIRO_TOKEN_EXPIRES,
                    session.getId().toString());
            return new ResponseData(userVO);
        } catch (AuthenticationException e) {
            return new ResponseData("登录失败");
        }
    }
}
