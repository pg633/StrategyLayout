package com.pg.sl.common.exec;

import java.util.concurrent.Callable;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 12:45 下午
 */
public class AsynWorker implements Callable<Object> {
    private final Task task;

    public AsynWorker(Task task) {
        this.task = task;
    }

    @Override
    public Object call() throws Exception {
        try {
            return task.doTask();
        } catch (Exception e) {
            return null;
        }
    }
}
