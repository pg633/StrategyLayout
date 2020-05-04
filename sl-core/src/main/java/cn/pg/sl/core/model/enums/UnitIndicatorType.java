package cn.pg.sl.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:54 下午
 */
@Getter
@AllArgsConstructor
public enum UnitIndicatorType {
    INDICATOR("INDICATOR", "指标"),
    ATTRIBUTE("ATTRIBUTE", "属性"),
    MANU("MANU", "手工输入");

    private String name;
    private String desc;
}
