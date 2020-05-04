package cn.pg.sl.core.coreservice;

import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.service.IndicatorService;
import com.pg.sl.common.exec.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:34 下午
 */
@Slf4j
public class UnitExeTask implements Task {
    private final IndicatorService indicatorService;
    private final UnitIndicator indicator;
    private final Map<String, Object> contextMap;

    public UnitExeTask(IndicatorService indicatorService, UnitIndicator indicator, Map<String, Object> contextMap) {
        this.indicatorService = indicatorService;
        this.indicator = indicator;
        this.contextMap = contextMap;
    }

    @Override
    public Object doTask() {
        try {
            Object obj = indicatorService.calulateIndicator(indicator, contextMap);
            return obj;
        } catch (Exception e) {
            log.info("{}执行失败,{}", indicator.getName(), e);
            throw e;
        }
    }
}
