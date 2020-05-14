package cn.pg.sl.integration.api.redis.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/6 2:12 下午
 */
@Configuration
@ComponentScan("cn.pg.sl.integration.api.redis")
@ConditionalOnProperty(prefix = "redis.auto", value = "enabled", matchIfMissing = true)
public class RedisAutoConf {

    //读取数据的前缀是什么
    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(){
        return new RedisClient();
    }

}
