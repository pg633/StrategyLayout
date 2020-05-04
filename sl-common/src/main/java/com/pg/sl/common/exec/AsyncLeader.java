package com.pg.sl.common.exec;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 12:46 下午
 */
@Slf4j
public class AsyncLeader {
    /**
     * 异步执行任务
     */
    public static void doExe(Task tasks, ThreadPoolTaskExecutor executor) {
        try {
            executor.submit(new AsynWorker(tasks));
        } catch (Exception e) {
            log.error("AsyncLeader error {} {}", e.getMessage(), e);
            return;
        }
    }

    public static void doExe(List<Task> tasks, ThreadPoolTaskExecutor executor) {
        tasks.forEach(t -> doExe(t, executor));
    }


    /**
     * 异步执行任务
     */
    public static void doExe(Task tasks, ThreadPoolTaskExecutor executor, Logger logger) {
        try {
            executor.submit(new AsynWorker(tasks));
        } catch (Exception e) {
            logger.error("AsyncLeader error {} {}", e.getMessage(), e);
            return;
        }
    }

    public static void doExe(List<Task> tasks, ThreadPoolTaskExecutor executor, Logger logger) {
        tasks.forEach(t -> doExe(t, executor, logger));
    }
}
