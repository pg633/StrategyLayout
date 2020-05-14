package cn.pg.sl.cache;

import cn.pg.sl.cache.service.TestSpringCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/13 4:02 下午
 */
@Component
@Slf4j
public class CacheRunner implements CommandLineRunner {

    @Autowired
    TestSpringCacheService testSpringCacheService;

    @Override
    public void run(String... args) throws Exception {
        log.info("--begin get value");
        log.info("isbn-1234 -->" + testSpringCacheService.queryEmp(1));
        log.info("isbn-4567 -->" + testSpringCacheService.queryEmp(2));
        log.info("isbn-1234 -->" + testSpringCacheService.queryEmp(1));
        log.info("isbn-4567 -->" + testSpringCacheService.queryEmp(2));
    }
}
