package cn.pg.sl.integration.api;

import java.util.Map;
import java.util.Optional;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 10:29 下午
 */

public interface IUnitFuncService {
    /**
     * rpc ?
     */
    Object execute(Map<String, Object> calculateParamMap);
}