package cn.pg.sl.core.service;

import cn.pg.sl.core.model.UnitIndicator;

import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 7:54 下午
 */
public interface IndicatorService {

    /**
     * 指标计算
     * @return
     */
    Object calulateIndicator(UnitIndicator indicator, Map<String, Object> contextMap);
}
