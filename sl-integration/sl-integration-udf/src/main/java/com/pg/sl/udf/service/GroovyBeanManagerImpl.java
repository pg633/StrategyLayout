package com.pg.sl.udf.service;

import cn.pg.sl.integration.api.udf.GroovyAction;
import cn.pg.sl.integration.api.udf.GroovyBeanManager;
import com.pg.sl.udf.cache.GroovyCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:31 上午
 */
@Component("GroovyBeanManager")
public class GroovyBeanManagerImpl implements GroovyBeanManager {
    @Autowired
    private GroovyCacheManager groovyCacheManager;
    @Override
    public Optional<GroovyAction> getGroovyBean(String beanName) {
        return  groovyCacheManager.getBean(beanName,GroovyAction.class);
    }
}
