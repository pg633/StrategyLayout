package com.pg.sl.udf.service;

import com.google.common.cache.*;
import com.pg.sl.dao.GroovyDao;
import com.pg.sl.dao.entities.GroovyParam;
import groovy.inspect.swingui.ObjectBrowser;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.ant.Groovy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/15 1:29 下午
 */
@Service
@Slf4j
public class ScriptCacheService {
    @Autowired(required = false)
    private GroovyDao groovyDao;


    public Cache<String, GroovyObject> scriptCC =
            CacheBuilder.newBuilder()
                    //最大数量
                    .maximumSize(1000)
                    //过期时间
                    .expireAfterWrite(2, TimeUnit.HOURS)
                    // 弱饮用
//                    .weakValues()
//                    移除listener
                    .removalListener(
                            new RemovalListener<String, GroovyObject>() {
                                @Override
                                public void onRemoval(RemovalNotification<String, GroovyObject> notification) {
//                                    System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
                                }

                            })
//                    记录状态
                    .recordStats()
                    .build(new CacheLoader<String, GroovyObject>() {
                        @Override
                        public GroovyObject load(String name) {
                            try {
                                GroovyParam groovyParam = groovyDao.getScriptByName(name);
                                GroovyClassLoader loader = new GroovyClassLoader();
                                if (!ObjectUtils.isEmpty(loader)) {
                                    Class groovyClass = loader.parseClass(groovyParam.getScript());
                                    return (GroovyObject) groovyClass.newInstance();
                                }
                            } catch (Exception e) {
                                log.error("## 缓存更新脚本失败");

                            }
                            return (GroovyObject) new Object();
                        }
                    });


}
