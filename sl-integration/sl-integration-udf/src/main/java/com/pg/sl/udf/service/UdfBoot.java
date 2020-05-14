package com.pg.sl.udf.service;

import com.pg.sl.udf.cache.GroovyCacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:30 上午
 */
@Component
public class UdfBoot implements InitializingBean {
    @Autowired
    private GroovyCacheManager groovyCacheManager;
    @Override
    public void afterPropertiesSet() throws Exception {
        groovyCacheManager.init();
    }
}
