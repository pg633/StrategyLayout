package cn.pg.sl.core.repository.impl;

import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.model.UnitIndicatorPara;
import cn.pg.sl.core.model.enums.UnitIndicatorType;
import cn.pg.sl.core.repository.UnitFuncRepository;
import cn.pg.sl.core.repository.UnitRepository;
import cn.pg.sl.core.util.MapList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pg.sl.common.bean.TbUnitGroovy;
import com.pg.sl.common.bean.TbUnitIndicator;
import com.pg.sl.common.dao.IndicatorDao;
import com.pg.sl.common.dao.IndicatorParaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:59 下午
 */
@Component
@Slf4j
public class UnitRepositoryImpl implements UnitRepository {

    @Autowired
    private UnitFuncRepository funcRes;

    @Autowired
    private IndicatorDao indicatorDao;
    @Autowired
    private IndicatorParaDao indicatorParaDao;


    public volatile Map<String, UnitIndicator> cache = new ConcurrentHashMap<String, UnitIndicator>();
    private volatile Map<String, String> indiScriptMapping = new ConcurrentHashMap<>();
    private final int MIN_LEVEL = 0;


    /**
     * 指标依赖关系网，梳理指标等级后 按照先后顺序一次判断指标参数表中并且加入指标树中
     */
    @Override
    public boolean loadAll() {

        MapList<Integer, TbUnitIndicator> indications = getAllIndication();
        MapList<Integer, UnitIndicatorPara> indicationParas = getAllIndicationPara();
        Map<String, UnitIndicator> temp = new ConcurrentHashMap<String, UnitIndicator>();
        AtomicInteger MAX_LEVEL = new AtomicInteger(MIN_LEVEL);
        indications.entrySet().stream().forEach(
                s -> s.getValue().stream().forEach(
                        k -> MAX_LEVEL.set(Math.max(MAX_LEVEL.get(), k.getLevel())))
        );

        for (int i = MIN_LEVEL; i < MAX_LEVEL.get(); i++) {
            List<TbUnitIndicator> indis = indications.get(i);
            if (!CollectionUtils.isEmpty(indis)) {
                for (TbUnitIndicator indi : indis) {
                    List<UnitIndicatorPara> indiPara = indicationParas.get(indi.getId());
                    if (CollectionUtils.isEmpty(indiPara)) {
                        log.error("#指标依赖参数为空 {}", indi.getName());
                        indiPara = Lists.newArrayList();
                    }
                    if (i > MIN_LEVEL) {
                        temp.put(indi.getName(), new UnitIndicator(
                                indi.getName(),
                                funcRes.getFunctionById(indi.getIndiInjecId()),
                                new CopyOnWriteArrayList<UnitIndicator>(getIndicatorDepons(indi, indicationParas, temp)),
                                indi.getLevel(),
                                indi.getStoreNode(),
                                new CopyOnWriteArrayList<>(indicationParas.get(indi.getId())),
                                indi.getExpireTime()

                        ));
                    } else {
                        temp.put(indi.getName(), new UnitIndicator(
                                indi.getName(),
                                funcRes.getFunctionById(indi.getIndiInjecId()),
                                Lists.newArrayList(),
                                indi.getLevel(),
                                indi.getStoreNode(),
                                new CopyOnWriteArrayList<>(),
                                indi.getExpireTime()

                        ));
                    }


                }
            }
        }
        logDiff(cache, temp);
        cache = temp;
        return true;
    }

    private void logDiff(Map<String, UnitIndicator> oldM, Map<String, UnitIndicator> newM) {
        log.info("# 指标更新数据插值 {}", (newM.size() - oldM.size()));
        newM.keySet().stream().filter(s -> !oldM.containsKey(s)).forEach(s -> log.info("#指标更新 更新指标 add {} ", newM.get(s).getName()));
    }

    private Set<UnitIndicator> getIndicatorDepons(TbUnitIndicator indi
            , MapList<Integer, UnitIndicatorPara> indicationParas
            , Map<String, UnitIndicator> temp) {
        Set<UnitIndicator> indiset = Sets.newHashSet();
        List<UnitIndicatorPara> unitIndicatorParas = indicationParas.get(indi.getId());
        if (!CollectionUtils.isEmpty(unitIndicatorParas)) {
            for (UnitIndicatorPara unitIndicatorPara : unitIndicatorParas) {
                if (UnitIndicatorType.INDICATOR.getName().equals(unitIndicatorPara.getSourceType())) {
//                添加依赖
                    UnitIndicator unitIndicator = temp.get(unitIndicatorPara.getReferKey());
                    if (unitIndicator == null) {
                        log.error("指标：{} 依赖指标有误！", indi.getName());
//                        Cat.logEvent("指标依赖有误",indi.getName());
                        throw new RuntimeException("指标：" + indi.getName() + "依赖指标有误！");
                    }
                    indiset.add(unitIndicator);
                    if (unitIndicator.getLevel() > MIN_LEVEL) {
                        indiset.addAll(unitIndicator.getDependIndicators());
                    }

                }
            }
        }
        return indiset;
    }

    private MapList<Integer, UnitIndicatorPara> getAllIndicationPara() {
        MapList<Integer, UnitIndicatorPara> map = new MapList<Integer, UnitIndicatorPara>();
        indicatorParaDao.getAllIndicatorPara().stream().forEach(k -> {
            map.put(k.getIndicatorId(), new UnitIndicatorPara(
                    k.getSourceType(),
                    k.getName(),
                    k.getReferKey())
            );
        });
        return map;
    }

    private MapList<Integer, TbUnitIndicator> getAllIndication() {
        MapList<Integer, TbUnitIndicator> map = new MapList<>();
        indicatorDao.getAllIndicator().stream().forEach(s -> map.put(s.getLevel(), s));
        return map;
    }

    @Override
    public UnitIndicator getIndicatorByName(String name) {
        if (ObjectUtils.isEmpty(cache)) {
            loadAll();
        }
        return cache.get(name);
    }

    @Override
    public boolean loadGroovyInfo() {
        Map<String, String> tempMap = new ConcurrentHashMap<>();
        List<TbUnitGroovy> mappingList = indicatorDao.getIndiScription();
        if (!CollectionUtils.isEmpty(mappingList)) {
            for (TbUnitGroovy temp : mappingList) {
                tempMap.put(temp.getIndiName(), temp.getGroovyName());
            }
        }
        indiScriptMapping = tempMap;
        return true;
    }

    @Override
    public String getIndicatorByIndiName(String indiName) {
        return indiScriptMapping.get(indiName);
    }
}
