package com.pg.sl.common.exec;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:24 上午
 */
@Slf4j
public class Leader {
    public static Map<String, Object> doExe(Map<String, Task> tasks,
                                            ThreadPoolTaskExecutor executor,
                                            int timeout
    ) {
        final CountDownLatch latch = new CountDownLatch(tasks.size());

        Map<String, Future<Object>> fus = tasks.entrySet()
                .stream()
                .map(s -> {
                    return new Pair<String, Future<Object>>(s.getKey(),
                            executor.submit(new Worker(s.getValue(), latch))
                    );
                })
                .collect(
                        Collectors.toMap(
                                Pair::getKey,
                                Pair::getValue,
                                (existing, replacement) -> replacement)
                );
        try {
            latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // cancel null
        }
        return fus.entrySet()
                .stream()
                .map(k -> {
                    try {
                        return new Pair<String, Object>(k.getKey(), k.getValue().get(5, TimeUnit.MILLISECONDS));
                    } catch (TimeoutException e) {
                        //监控
                        log.error("unit {} timeout ", k.getKey());
                    } catch (InterruptedException e) {
                        //中断系统
                        log.error("unit:{}interrupted,e:{}", k.getKey(), e);
                    } catch (ExecutionException e) {
                        // 特定异常
                        Throwable cause = e.getCause();
                        if (cause instanceof RuntimeException) {
                            log.error("tensorflow error", e.getCause());
                            RuntimeException re = (RuntimeException) cause;
//                            if(SPECIAL_EXCEPTINO_CODE.contains(re.getMessage())){
//                                Cat.logEvent("tensorflow error",e.getMessage());
//                                throw new RuntimeException(re.getMessage());
//                            }
                        }

                    }
                    return null;
                })
                .filter(k -> k != null)
                .collect(Collectors.toMap(
                        Pair::getKey,
                        Pair::getValue,
                        (k1, k2) -> k2
                ));
    }


//    public static Map<String, Object> doExe(Map<String, Task> callMap,
//                                            ThreadPoolTaskExecutor executor, int timeout) {
//        final CountDownLatch latch = new CountDownLatch(callMap.size());
//        Map<String, Future<?>> ruleFutures = new HashMap<String, Future<?>>();
//        for (String key : callMap.keySet()) {
//            try {
//                ruleFutures.put(key, executor.submit(new Worker(callMap
//                        .get(key), latch)));
//            } catch (Exception e) {
//                // 这里一般为队列满了，走默认系统异常流程
//                // 计数器需要减少
//                latch.countDown();
//            }
//        }
//        try {
//            latch.await(timeout, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            // 无需处理
//        }
//        // 组装结果
//        Map<String, Object> result = new HashMap<String, Object>();
//        for (String key : ruleFutures.keySet()) {
//            Future<?> fu = ruleFutures.get(key);
//            try {
//                Object value = fu.get(5,TimeUnit.MILLISECONDS);
//                result.put(key, value);
//            }catch (TimeoutException e) {
//                log.error("indicator:{}执行超时",key);
//            } catch (InterruptedException e) {
//                log.error("indicator:{}interrupted,e:{}",key,e);
//            } catch (ExecutionException e) {
//            }
//        }
//        return result;
//    }


}
