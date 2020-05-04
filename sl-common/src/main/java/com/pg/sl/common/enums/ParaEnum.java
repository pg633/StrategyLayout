package com.pg.sl.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:16 上午
 */
@Getter
@AllArgsConstructor
public enum ParaEnum {
    STR("字符串", "STR"),
    BOOL("布尔", "BOOLEAN"),
    LONG("整数", "LONG"),
    DOUBLE("浮点数", "DOUBLE"),
    ARRAY("数组", "ARY"),
    MAPP("映射表", "MAP");

    private String name;
    private String code;

}
