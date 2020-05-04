package com.pg.sl.common.bean;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.type.JdbcType;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:28 上午
 */
@Data
@Builder
public class TbUnitGroovy {
    //指标名
    String indiName;
    //脚本名
    String groovyName;

}
