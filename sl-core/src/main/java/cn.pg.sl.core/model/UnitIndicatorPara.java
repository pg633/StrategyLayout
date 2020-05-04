package cn.pg.sl.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:51 下午
 */
@Data
@AllArgsConstructor
@Getter
@Setter
public class UnitIndicatorPara {
    /**
     * 来源  对应IndicatorParaType
     */
    private final String sourceType;

    /**
     * 参数名称
     */
    private final String name;

    /**
     * 参数名称 映射的名称  比如：sourceType 为指标 时，为指标名称，sourceType 为 属性 时，为属性名称
     */
    private final String referKey;
}
