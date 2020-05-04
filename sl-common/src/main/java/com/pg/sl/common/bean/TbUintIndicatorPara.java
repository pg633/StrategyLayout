package com.pg.sl.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:35 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbUintIndicatorPara {
    private int id;// id标示
    private String name;// 名称
    private int indicatorId;// 所属指标
    private String referKey;// 参数refer的值
    private String sourceType;// 参数来源
    private int status;// 状态
}
