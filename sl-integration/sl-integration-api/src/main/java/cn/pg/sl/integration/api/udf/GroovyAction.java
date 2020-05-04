package cn.pg.sl.integration.api.udf;

import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 10:21 下午
 */
public interface GroovyAction {

    Object doAction(Map<String,Object> params) throws Exception;
}

