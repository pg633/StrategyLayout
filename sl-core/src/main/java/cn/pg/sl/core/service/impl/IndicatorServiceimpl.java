package cn.pg.sl.core.service.impl;

import cn.pg.sl.core.model.UnitFuncIndicator;
import cn.pg.sl.core.model.UnitFuncIndicatorPara;
import cn.pg.sl.core.model.UnitIndicator;
import cn.pg.sl.core.model.UnitIndicatorPara;
import cn.pg.sl.core.model.enums.UnitIndicatorType;
import cn.pg.sl.core.repository.UnitRepository;
import cn.pg.sl.core.service.IndicatorService;
import cn.pg.sl.integration.api.IUnitFuncService;
import cn.pg.sl.integration.api.udf.GroovyBeanManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.pg.sl.common.enums.ParaEnum;
import com.pg.sl.common.util.ConstantUtil;
import com.pg.sl.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/4 7:55 下午
 */
@Service
@Slf4j
public class IndicatorServiceimpl implements IndicatorService {
    @Autowired
    private ApplicationContext apx;
//    @Autowired
//    private GroovyBeanManager groovyBeanManager;

    @Autowired
    private UnitRepository unitRepository;
    private void preCalulate(Map<String, Object> calculateParamMap, Map<String, Object> contextMap) {
//        calculateParamMap.put(ConstantUtil.REQUESTID, contextMap.get(ConstantUtil.REQUESTID));
//        calculateParamMap.put(BizServerStoreUtils.STORE_NODE, indicator.getStoreCatg());
//        calculateParamMap.put("CLIENT_APP", contextMap.get("CLIENT_APP"));
    }

    @Override
    public Object calulateIndicator(UnitIndicator indicator, Map<String, Object> contextMap) {

        // 组装上下文
        Map<String, Object> calculateParamMap = new HashMap<String, Object>();
        preCalulate(calculateParamMap, contextMap);

        //参数准备
        if (!CollectionUtils.isEmpty(indicator.getParas())) {
            for (UnitIndicatorPara indicatorPara : indicator.getParas()) {
                UnitFuncIndicatorPara serverInterPara = null;
                //检查指标依赖的接口入参是否在指标入参中

                for (UnitFuncIndicatorPara tempFuncPara : indicator.getDependServerInter().getParas()) {
                    if (indicatorPara.getName().equals(tempFuncPara.getName())) {
                        serverInterPara = tempFuncPara;
                        /**
                         *  todo
                         * 会出现问题  A->B ->D
                         *             A->C->B->D
                         *             B C 拥有想用的依赖常参数
                         */
                        break;
                    }
                }
                //无依赖入参
                if (serverInterPara == null) {
                    continue;
                }

                if (UnitIndicatorType.MANU.getName().equals(indicatorPara.getSourceType())) {
                    List<UnitFuncIndicatorPara> paras = indicator.getDependServerInter().getParas();
                    if (!CollectionUtils.isEmpty(paras)) {
                        for (UnitFuncIndicatorPara para : paras) {
                            if (para.getName().equals(indicatorPara.getName())) {
                                calculateParamMap.put(indicatorPara.getName(), getMenu(para.getDataType(), indicatorPara.getReferKey()));
                                break;
                            }
                        }
                    }
                } else if (UnitIndicatorType.INDICATOR.getName().equals(indicatorPara.getSourceType())
                        || UnitIndicatorType.ATTRIBUTE.getName().equals(indicatorPara.getSourceType())) {
                    calculateParamMap.put(indicatorPara.getName(),
                            contextMap.get(indicatorPara.getName()));

                }
            }
            log.info("{},接口调用.indicator.name:{},function.name:{}", contextMap.get(ConstantUtil.REQUESTID), indicator.getName(), indicator.getDependServerInter().getClassName());

        }
        // 真正指标调用
        UnitFuncIndicator dependServerInter = indicator.getDependServerInter();
        Object result = null;
        switch (dependServerInter.getSourceType()) {
            case 0: {
                IUnitFuncService bean = (IUnitFuncService) apx.getBean(dependServerInter.getClassName());
                result = bean.execute(calculateParamMap);
                break;
            }
            case 1: {
                //  thrift 泛化调用 活着注入调用

                break;
            }
            case 2: {
                /**
                 * 脚本调用
                 */
//                Optional<GroovyAction> groovyResult = groovyBeanManager.getGroovyBean(dependServerInter.getClassName());
//                if (groovyResult.isPresent()) {
//                    try {
//                        result = groovyResult.get().doAction(calculateParamMap);
//                    } catch (Exception e) {
//                        log.error("groovyaction execution failed,action:{} params:{}", dependServerInter.getClassName(), calculateParamMap);
//                        return null;
//                    }
//                }
                break;

            }
        }
        String groovyName = unitRepository.getIndicatorByIndiName(indicator.getName());
//        if (!StringUtils.isEmpty(groovyName)) {
//            Optional<GroovyAction> groovyResult = groovyBeanManager.getGroovyBean(groovyName);
//            if (groovyResult.isPresent()) {
//                Map<String, Object> postpositionParamMap = concatPostParams(calculateParamMap, indicator, result);
//                try {
//                    return groovyResult.get().doAction(postpositionParamMap);
//                } catch (Exception e) {
//                    log.error("groovyaction execution failed,action:{} params:{}", groovyName, postpositionParamMap);
//                }
//            }
//        }
        return result;

    }
    /**
     * 拼装入参、出参以及一些额外字段
     * @param originParamMap
     */
    private Map<String, Object> concatPostParams(Map<String, Object> originParamMap, UnitIndicator indicator, Object indiResult) {
        Map<String, Object> parammap = new HashMap<>(originParamMap.size() + 3);
        parammap.putAll(originParamMap);
//        Object clientAppObj = originParamMap.get("CLIENT_APP");
//        String clientApp = clientAppObj == null ? null : clientAppObj.toString();
//        parammap.put("appKey", clientApp);
//        parammap.put("requestTime", DateUtil.getNowFormatString(DateUtil.SIMPLE));
        parammap.put(indicator.getName(), indiResult);
        return parammap;
    }


    private Object getMenu(String paraType, String referKey) {
        if (ParaEnum.ARRAY.getCode().equals(paraType)) {
            return GsonUtil.toList(referKey);
        } else if (ParaEnum.BOOL.getCode().equals(paraType)) {
            return Boolean.valueOf(referKey);
        } else if (ParaEnum.DOUBLE.getCode().equals(paraType)) {
            return Double.valueOf(referKey);
        } else if (ParaEnum.LONG.getCode().equals(paraType)) {
            return Long.valueOf(referKey);
        } else if (ParaEnum.MAPP.getCode().equals(paraType)) {
            return GsonUtil.toMap(referKey, new TypeReference<Map<String, String>>() {
            });
        } else {
            return referKey;
        }


    }
}
