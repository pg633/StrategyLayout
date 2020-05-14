package cn.pg.sl.integration.api.redis.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 7:44 上午
 */

@Component
@Data
@ConfigurationProperties(prefix = "redis",ignoreInvalidFields=true, ignoreUnknownFields=true)
@PropertySource(value = {"classpath:redisclients.properties"})
public class RedisClient {
    private List<Client> clients ;


}
