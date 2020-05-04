package com.pg.sl.common.exec;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 处理工作组件
 */
public class Worker implements Callable<Object> {

    private final CountDownLatch latch;
    private final Task task;

    public Worker(Task task, CountDownLatch latch) {
        this.latch = latch;
        this.task = task;
    }

    @Override
    public Object call() throws Exception {
        try {
            return task.doTask();
        } catch (Exception e) {
            throw e;
        } finally {
            latch.countDown();
        }
    }
}
