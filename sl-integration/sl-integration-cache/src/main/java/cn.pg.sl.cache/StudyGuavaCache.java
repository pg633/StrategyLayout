package cn.pg.sl.cache;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/13 4:49 下午
 */
public class StudyGuavaCache {
//    LoadingCache<Key, Value> cache = CacheBuilder.newBuilder()
//            .build(
//                    new CacheLoader<Key, Value>() {
//                        public Value load(Key key) throws AnyException {
//                            return createValue(key);
//                        }
//                    });
//...
//        try {
//        return cache.get(key);
//    } catch (ExecutionException e) {
//        throw new OtherException(e.getCause());
//    }

    private static Cache<String, String> cc =
            CacheBuilder.newBuilder()
                    //最大数量
                    .maximumSize(2)
                    //过期时间
                    .expireAfterWrite(3, TimeUnit.SECONDS)
                    // 弱饮用
//                    .weakValues()
//                    移除listener
                    .removalListener(
                            new RemovalListener<String, String>() {
                                @Override
                                public void onRemoval(RemovalNotification<String, String> notification) {

                                    System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");

                                }

                            })
//                    记录状态
                    .recordStats()
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load(String s) throws Exception {

                            return "加载中" + s;
                        }
                    });

    void func1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1");
                try {
                    String value = cc.get("key", new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println("load1"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable1";
                        }
                    });
                    System.out.println("thread1 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2");
                try {
                    String value = cc.get("key", new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println("load2"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable2";
                        }
                    });
                    System.out.println("thread2 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cc.stats());

    }

    public static void main(String[] args) throws ExecutionException {
//        Cache<String, String> cache = CacheBuilder.newBuilder().build();
//        cache.put("word", "Hello Guava Cache");
//        System.out.println(cache.getIfPresent("word"));


        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(loader);//在构建时指定自动加载器

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");


    }
}
