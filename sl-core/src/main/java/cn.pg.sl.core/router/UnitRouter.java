package cn.pg.sl.core.router;

import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.repository.UnitRepository;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 10:54 上午
 */
@Component
public class UnitRouter {
    @Autowired
    private UnitRepository unitRepository;

    public Map<Integer, Set<UnitIndicator>> routeNeedIncators(List<String> indicators) {

        if (CollectionUtils.isEmpty(indicators)) {
            return Collections.emptyMap();
        }
        // 需要计算的指标，按照等级分类


        Map<Integer, Set<UnitIndicator>> routePath = Maps.newHashMap();

        indicators.stream().forEach(s -> {
            UnitIndicator indicatorByName = unitRepository.getIndicatorByName(s);
            makeUnitgroup(indicatorByName, routePath);

        });

        return routePath;
    }

    /**
     * 组合层级依赖
     *
     * @param indicatorName
     * @param routePath
     */
    private void makeUnitgroup(UnitIndicator indicatorName, Map<Integer, Set<UnitIndicator>> routePath) {
        if (indicatorName == null) {
            return;
        }
        addgroup(indicatorName, routePath);
        List<UnitIndicator> dependIndicators = indicatorName.getDependIndicators();
        if (!ObjectUtils.isEmpty(dependIndicators)) {
            for (UnitIndicator dependIndicator : dependIndicators) {
                addgroup(dependIndicator, routePath);
            }
        }
    }

    private void addgroup(UnitIndicator indicatorName, Map<Integer, Set<UnitIndicator>> routePath) {
        if(!routePath.containsKey(indicatorName.getLevel())){
            routePath.put(indicatorName.getLevel(), Sets.newHashSet());
        }
        routePath.get(indicatorName.getLevel()).add(indicatorName);
    }

}
