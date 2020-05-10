package cn.pg.sl.biz;

import cn.pg.sl.integration.api.redis.service.AbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/6 2:43 下午
 */

@SpringBootApplication
@RestController
public class AbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AbApplication.class, args);

    }
    @Autowired
    private AbService abService;

    @RequestMapping("/")
    public String index() {
        return abService.get();
    }
}