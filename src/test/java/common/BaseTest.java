package common;

import com.itclj.ItcljApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create by lujun.chen on 2018/09/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItcljApplication.class)
public class BaseTest {
    public Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void test(){
        logger.info("{}","log");
    }
}
