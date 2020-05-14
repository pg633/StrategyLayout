package com.pg.sl.udf.cache;

import cn.pg.sl.integration.api.udf.GroovyAction;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:33 上午
 */
@Component
public class GroovyCacheManager {
    public Optional<GroovyAction> getBean(String beanName, Class<GroovyAction> groovyActionClass) {
        return Optional.empty();
    }

    public void init() {


    }
}
