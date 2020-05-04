package cn.pg.sl.core.coreservice;


import com.pg.sl.common.exec.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:35 下午
 */
@Slf4j
public class UnitStoreTask implements Task {

    private final UnitManagerService unitManagerService;
    private final List<String> unitLists;
    private final Map<String, Object> contextMap;

    public UnitStoreTask(UnitManagerService unitManagerService, List<String> unitLists, Map<String, Object> contextMap) {
        this.unitManagerService = unitManagerService;
        this.unitLists = unitLists;
        this.contextMap = contextMap;
    }

    @Override
    public Object doTask() {
        try {
            return unitManagerService.finalstore(unitLists, contextMap);
        } catch (Exception e) {
            log.error("指标计算异常", e);
            return null;
        }
    }
}
