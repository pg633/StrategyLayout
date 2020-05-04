package cn.pg.sl.core.repository;

import cn.pg.sl.core.model.UnitFuncIndicator;
import cn.pg.sl.core.model.UnitIndicator;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:48 下午
 */
public interface UnitFuncRepository {
    boolean loadAll();

    UnitFuncIndicator getFunctionById(Integer id);
}
