package cn.pg.sl.core.coreservice;

import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.repository.UnitRepository;
import cn.pg.sl.core.router.UnitRouter;
import cn.pg.sl.core.service.IndicatorService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pg.sl.common.exec.AsyncLeader;
import com.pg.sl.common.exec.Leader;
import com.pg.sl.common.exec.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:34 下午
 */
@Service
@Slf4j
public class UnitManagerService {
    // 存储小组内指标超时时间
    private final int unitStoreTimeout = 1000;

    @Autowired
    private UnitRouter unitRouter;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private IndicatorService indicatorService;


    /**
     * 指标最小等级当前控制为0
     */
    private static final int MINLEVEL = 0;
    // 查询小组内指标超时时间
    private final int getIndicatorTimeout = 200;

    // 分析线程池
    @Autowired
    @Qualifier(value = "calculateExecutor")
    private ThreadPoolTaskExecutor calculateExecutor;

    @Autowired
    @Qualifier(value = "storeRequestExecutor")
    private ThreadPoolTaskExecutor storeRequestExecutor;


    public Map<String, Object> calculate(List<String> indicatorNameList, Map<String, Object> contextMap) {
        //调用方可以传入过期时间
        Object expiredTimeObj = contextMap.get("expiredTime");
        if (expiredTimeObj != null) {
            int expiredTime = getIndicatorTimeout;
            if (expiredTimeObj instanceof String) {
                try {
                    expiredTime = Integer.valueOf((String) expiredTimeObj);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            } else if (expiredTimeObj instanceof Integer) {
                expiredTime = (Integer) expiredTimeObj;
            }
            contextMap.remove("expiredTime");
            return getFinalIndicatorValue(indicatorNameList, contextMap, expiredTime);
        } else {
            return getFinalIndicatorValue(indicatorNameList, contextMap, getIndicatorTimeout);
        }

    }

    public List<Object> batchCalculate(List<String> indicatorNameList, List<Map<String, Object>> contextMapList) {
        if (CollectionUtils.isEmpty(indicatorNameList)) {
            return null;
        }
        List<Object> resultList = new ArrayList<Object>(indicatorNameList.size());
        if (indicatorNameList.size() == contextMapList.size()) {
            for (int i = 0; i < indicatorNameList.size(); i++) {
                Map<String, Object> tempMap = getFinalIndicatorValue(Lists.newArrayList(indicatorNameList.get(i)), contextMapList.get(i), getIndicatorTimeout);
                resultList.add(tempMap.get(indicatorNameList.get(i)));
            }
        } else {
            for (int i = 0; i < contextMapList.size(); i++) {
                Map<String, Object> tempMap = getFinalIndicatorValue(Lists.newArrayList(indicatorNameList.get(0)), contextMapList.get(i), getIndicatorTimeout);
                resultList.add(tempMap.get(indicatorNameList.get(0)));
            }
        }
        return resultList;
    }

    /**
     * 存储指标计算
     */
    public Object store(List<String> unitLists, Map<String, Object> contextMap) {
        AsyncLeader.doExe(new UnitStoreTask(this, unitLists, contextMap), storeRequestExecutor);
        return "success";

    }

    public Object finalstore(List<String> unitLists, Map<String, Object> contextMap) {
        try {
            return getFinalIndicatorValue(unitLists, contextMap, unitStoreTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> getFinalIndicatorValue(List<String> indicatorNameList,
                                                       Map<String, Object> contextMap,
                                                       int timeout) {

        /**
         * 指标分组 -> 并发 -> 拼接
         */
        Map<Integer, Set<UnitIndicator>> pathRoute = unitRouter.routeNeedIncators(indicatorNameList);
        if (CollectionUtils.isEmpty(pathRoute)) {
            log.info("没有找到对指标{}", indicatorNameList);
        }
        int maxLevel = pathRoute.keySet().stream().max((o1, o2) -> o1.compareTo(o2)).get();
        Map<String, Task> callMap = Maps.newHashMap();
        for (int i = MINLEVEL; i <= maxLevel; i++) {
            Set<UnitIndicator> indicators = pathRoute.get(i);
            if (CollectionUtils.isEmpty(indicators)) {
                continue;
            }
            for (UnitIndicator indicator : indicators) {
                int expireTime = indicator.getExpireTime();
                if (expireTime != 0 && expireTime > timeout) {
                    timeout = expireTime;
                }
//                contextMap.put("CLIENT_APP",ContextUtils.getLocalContext("CLIENT_APP"));
                callMap.put(indicator.getName(), new UnitExeTask(indicatorService, indicator, contextMap));
            }


            Map<String, Object> rets = Leader.doExe(callMap, calculateExecutor, timeout);
            contextMap.putAll(rets);
        }
        Map<String, Object> rets = Maps.newHashMap();
        // 返回指标值
        for (String indicatorName : indicatorNameList) {
            if (contextMap.containsKey(indicatorName)) {
                rets.put(indicatorName, contextMap.get(indicatorName));
            }
        }
        return rets;
    }


}
