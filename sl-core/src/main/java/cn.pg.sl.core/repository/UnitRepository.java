package cn.pg.sl.core.repository;

import cn.pg.sl.core.model.UnitIndicator;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:47 下午
 */
public interface UnitRepository {
    boolean loadAll();
    UnitIndicator getIndicatorByName(String name);

    boolean loadGroovyInfo();
    String getIndicatorByIndiName(String indiName);

}
