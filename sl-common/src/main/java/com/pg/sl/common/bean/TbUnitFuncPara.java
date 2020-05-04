package com.pg.sl.common.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:04 上午
 */
@Data
@Builder
public class TbUnitFuncPara {
    int id;
    int funcId;
    String name;
    String description;
    String dateType;


//            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID标识',
//            `indicator_provider_id` int(11) NOT NULL COMMENT '服务方法ID',
//            `name` varchar(64) NOT NULL DEFAULT '' COMMENT '参数名称',
//            `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
//            `data_type` varchar(64) NOT NULL DEFAULT '' COMMENT '数据类型',





}
