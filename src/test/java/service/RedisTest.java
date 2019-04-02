package service;

import com.alibaba.fastjson.JSON;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Create by lujun.chen on 2018/09/30
 */
public class RedisTest extends BaseTest {

  @Autowired
  private JedisCluster jedisCluster;

  @Test
  public void set() {
    String res = jedisCluster.setex("itclj:test:20180930001", 30, "aaaaa");
    logger.info(JSON.toJSONString(res));
    logger.debug(JSON.toJSONString(res));
  }

}
