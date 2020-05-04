package cn.pg.sl.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:50 下午
 */
@Data
@AllArgsConstructor
public class UnitFuncIndicator {
    private final String className ;//服务接口名称

    private final String methodName ;//服务接口方法名称

    private final List<UnitFuncIndicatorPara> paras;//入参名称

    private int sourceType;//接口来源 0:inline,1:inline_voke
}
