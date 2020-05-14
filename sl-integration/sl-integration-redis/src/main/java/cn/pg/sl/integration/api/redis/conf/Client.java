package cn.pg.sl.integration.api.redis.conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 7:47 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Client{
    int id;
    String name;
    String host;
    int port;
    int timeout;
    String pasword;
    int database;


};