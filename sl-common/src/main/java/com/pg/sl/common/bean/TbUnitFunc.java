package com.pg.sl.common.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:54 上午
 */
@Data
@Builder
public class TbUnitFunc {
    private int id;// ID标示
    private String name;// 名称
    private String description;// 描述
    private String returnType;// 返回类型
    private int status;// 状态
    private int sourceType;//接口来源 0:dtc,1:dtcInvoke
}
