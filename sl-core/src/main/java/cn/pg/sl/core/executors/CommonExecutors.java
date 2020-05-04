package cn.pg.sl.core.executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 7:57 下午
 */
@Configuration
public class CommonExecutors {
    @Bean
    public ThreadPoolTaskExecutor calculateExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(250);
        threadPoolTaskExecutor.setMaxPoolSize(250);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor storeRequestExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(50);
        threadPoolTaskExecutor.setMaxPoolSize(50);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setQueueCapacity(500);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        return threadPoolTaskExecutor;
    }
}
