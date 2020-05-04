package cn.pg.sl.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:49 下午
 */
@Data
@AllArgsConstructor
public class UnitIndicator {

    /**
     * 指标名称
     */
    private final String name;

    /**
     * 查询该指标的服务接口
     */
    private final UnitFuncIndicator dependServerInter;

    /**
     * 依赖的所有指标(包括依赖指标所依赖的指标)
     */
    private final List<UnitIndicator> dependIndicators;

    /**
     * 在路径中的level
     */
    private final int level ;

    /**
     * 存储分类
     */
    private final String storeCatg ;

    /**
     * 需要的入参名称
     */
    private final List<UnitIndicatorPara> paras;

    /**
     * 过期时间
     */
    private final int expireTime;

}
