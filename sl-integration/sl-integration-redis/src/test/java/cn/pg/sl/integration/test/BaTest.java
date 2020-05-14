package cn.pg.sl.integration.test;

import cn.pg.sl.integration.api.redis.conf.RedisClient;
import cn.pg.sl.integration.api.redis.service.City;
import cn.pg.sl.integration.api.redis.service.UserInfo;
import cn.pg.sl.integration.api.redis.service.UserServiceImpl;
import cn.pg.sl.integration.api.redis.util.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.fastinfoset.algorithm.BASE64EncodingAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/6 2:06 下午
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
//        (exclude = RedisAutoConfiguration.class)
public class BaTest {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void func(){
        City city = new City(1, "123");
        userService.putRedis(city);
    }

    @Test
    public void func2(){
      String s =  "[\"cn.pg.sl.integration.api.redis.service.City\",{\"id\":1,\"name\":\"123\"}]";
        System.out.println(s);

    }

    @Autowired
    private RedisClient bean2;

    @Test
    public void doTestBean(){

        System.out.println(bean2.getClients());
        redisUtils.set("a","123");
        System.out.println(redisUtils.get("a"));
    }


}
