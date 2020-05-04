package com.pg.sl.common.util;

import com.google.common.collect.ImmutableSet;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 1:32 下午
 */
public class ConstantUtil {
    public static final ImmutableSet<String> BASE_TYPE_SET =
            ImmutableSet.of( "int", "integer", "float", "double", "char", "bool", "long", "boolean", "byte", "string");
    public static final String REQUESTID = "requestId";

}
