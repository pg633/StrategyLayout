package cn.pg.sl.integration.api.redis.conf;

import cn.pg.sl.integration.api.redis.service.AbService;
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
@ConditionalOnProperty(prefix = "asdasd", value = "enabled", matchIfMissing = true)
public class RedisAutoConf {

}
