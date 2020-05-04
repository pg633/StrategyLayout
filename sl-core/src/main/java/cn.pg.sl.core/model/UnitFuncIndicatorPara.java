package cn.pg.sl.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:52 下午
 */
@Data
@AllArgsConstructor
public class UnitFuncIndicatorPara {
    private final String name;//接口中参数名称

    private final String dataType;
}
