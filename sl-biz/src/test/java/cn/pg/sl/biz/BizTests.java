package cn.pg.sl.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/6 2:25 下午
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes= RedisAutoConf.class)
@EnableAutoConfiguration
public class BizTests {
//    @Autowired
//    private AbService abService;
    @Test
    public void testBizCoonf(){
//        String s = abService.get();
//        System.out.println(s);
    }
}
