package com.pg.core;

import cn.pg.sl.core.coreservice.UnitExeTask;
import cn.pg.sl.core.coreservice.UnitManagerService;
import cn.pg.sl.core.model.UnitFuncIndicator;
import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.model.UnitIndicatorPara;
import cn.pg.sl.core.repository.UnitFuncRepository;
import cn.pg.sl.core.repository.impl.UnitFuncRepositoryImpl;
import cn.pg.sl.core.repository.impl.UnitRepositoryImpl;
import cn.pg.sl.core.service.IndicatorService;
import cn.pg.sl.core.util.MapList;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pg.sl.common.dao.IndicatorParaDao;
import com.pg.sl.common.exec.Leader;
import com.pg.sl.common.exec.Task;
import com.pg.sl.web.AppApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:46 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)

public class FuncMapTests {
    @Autowired
    private UnitFuncRepositoryImpl unitFuncRepository;

    @Autowired
    private UnitRepositoryImpl unitRepository;

    @Autowired
    private IndicatorParaDao indicatorParaDao;
    @Autowired
    @Qualifier(value = "calculateExecutor")
    private ThreadPoolTaskExecutor calculateExecutor;

    @Autowired
    @Qualifier(value = "storeRequestExecutor")
    private ThreadPoolTaskExecutor storeRequestExecutor;
    @Test
    public void testFuncMap() {
        boolean b = unitFuncRepository.loadAll();
        System.out.println(b);

        String s = JSONObject.toJSONString(unitFuncRepository.cache);
        System.out.println(s);


        MapList<Integer, UnitIndicatorPara> map = new MapList<Integer, UnitIndicatorPara>();
//        List<TbUintIndicatorPara> allIndicatorPara = indicatorParaDao.getAllIndicatorPara();
//
//        allIndicatorPara.stream().forEach(k -> {
//            map.put(k.getIndicatorId(), new UnitIndicatorPara(
//                    k.getSourceType(),
//                    k.getName(),
//                    k.getReferKey())
//            );
//        });


        boolean b1 = unitRepository.loadAll();
        System.out.println(b1);
        s = JSONObject.toJSONString(unitRepository.cache);
        System.out.println(s);

    }

    @Autowired
    private UnitManagerService unitManagerService;

    @Before
    public void before(){
        unitFuncRepository.loadAll();
        unitRepository.loadAll();

        System.out.println(JSONObject.toJSONString(unitFuncRepository.cache));
        System.out.println(JSONObject.toJSONString(unitRepository.cache));

    }
    @Autowired
    private IndicatorService indicatorService;
    @Test
    public void testIndicator() {
        List<String> unitLists = new ArrayList<>();

        Map<String, Object> contextMap = new HashMap<>();

        unitLists.add("audit_eventuuid_value_store");

        contextMap.put("eventUuid","eventUuid");
        contextMap.put("eventContextJson","eventContextJson");


//        MANU	inner_tableName	credit_dtc_event
//        ATTRIBUTE	key1	eventUuid
//        ATTRIBUTE	value	eventContextJson


        Object store = unitManagerService.store(unitLists, contextMap);
        System.out.println(store);


        UnitIndicator indicator = unitRepository.cache.get("audit_eventuuid_value_store");
        Map<String, Task> callMap = Maps.newHashMap();
        callMap.put(indicator.getName(), new UnitExeTask(indicatorService, indicator, contextMap));
        Map<String, Object> rets = Leader.doExe(callMap, calculateExecutor, 100);
        System.out.println(JSONObject.toJSONString(rets));

    }


    @Test
    public void func (){
//        UnitFuncIndicator functionById = unitFuncRepository.getFunctionById(61);
//        System.out.println(functionById);
        UnitIndicator audit_eventuuid_value_store = unitRepository.getIndicatorByName("audit_eventuuid_value_store");
        System.out.println(JSONObject.toJSONString(audit_eventuuid_value_store));
    }
}
