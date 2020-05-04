package cn.pg.sl.core.repository.impl;

import cn.pg.sl.core.model.UnitFuncIndicator;
import cn.pg.sl.core.model.UnitFuncIndicatorPara;
import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.repository.UnitFuncRepository;
import cn.pg.sl.core.util.MapList;
import com.pg.sl.common.dao.IndicatorFuncDao;
import com.pg.sl.common.dao.IndicatorFuncParaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:00 下午
 */
@Component
@Slf4j
public class UnitFuncRepositoryImpl implements UnitFuncRepository {

    @Autowired
    private IndicatorFuncDao indicatorFuncDao;
    @Autowired
    private IndicatorFuncParaDao indicatorFuncParaDao;
    public volatile Map<Integer, UnitFuncIndicator> cache = new ConcurrentHashMap<Integer, UnitFuncIndicator>();


    @Override
    public boolean loadAll() {
        MapList<Integer, UnitFuncIndicatorPara> params = new MapList<Integer, UnitFuncIndicatorPara>();
        indicatorFuncParaDao.getAllFuncPara().stream().forEach(
                s -> params.put(s.getFuncId(), new UnitFuncIndicatorPara(s.getName(), s.getDateType())));
        Map<Integer, UnitFuncIndicator> temp = new ConcurrentHashMap<Integer, UnitFuncIndicator>();
        indicatorFuncDao.getAllIndicatorFunc().stream().forEach(func -> {
            List<UnitFuncIndicatorPara> fpl = params.get(func.getId());
            if (CollectionUtils.isEmpty(fpl)) {
                log.error("function has no para:" + func.getName());
                // func 没有指标参数
            }
            //123
            temp.put(
                    func.getId(),
                    new UnitFuncIndicator(
                            func.getName(),
                            func.getReturnType(),
                            new CopyOnWriteArrayList<>(fpl),
                            func.getSourceType()
                    ));
        });

        doLog(cache, temp);
        cache = temp;
        return true;
    }

    private void doLog(Map<Integer, UnitFuncIndicator> oldMap, Map<Integer, UnitFuncIndicator> newMap) {
        log.info("##FuncIndicator size incr {}", (newMap.size() - oldMap.size()));
        newMap.keySet()
                .stream()
                .filter(k -> !oldMap.containsKey(k))
                .forEach(k->{
                    log.info("funccache add add inter: {}",newMap.get(k).getClassName());
                });
    }

    @Override
    public UnitFuncIndicator getFunctionById(Integer id) {
        if (cache == null || cache.size() == 0) {
            loadAll();
        }
        return cache.get(id);
    }
}
