package com.pg.sl.common.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.sun.javafx.applet.Splash;
import javafx.util.Pair;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 1:01 下午
 */
public class CommonUtil {

    /**
     * 链接两个obj
     */
    public static String conObj(Object o1, Object o2) {
        StringBuffer sb = new StringBuffer();
        if (!ObjectUtils.isEmpty(o1)) {
            sb.append(o1.toString());
        }
        if (!ObjectUtils.isEmpty(o2)) {
            sb.append(o2.toString());
        }
        return sb.toString();
    }

    /**
     * map<str,obj> -> map <str,str>
     */
    public static Map<String, String> transMap(Map<String, Object> map) {
        Map<String, String> collect = map.entrySet()
                .stream()
                .map(k ->
                        new Pair<String, String>(k.getKey(),
                                isBaseType(k.getValue()) ? k.getValue().toString() : GsonUtil.toJson(k.getValue())))
                .collect(
                        Collectors.toMap(
                                Pair::getKey,
                                Pair::getValue,
                                (existing, replacement) -> replacement)
                );
        return collect;
    }

    /**
     * 是不是基本类型
     *
     * @param value
     * @return
     */
    private static boolean isBaseType(Object value) {
        return ConstantUtil.BASE_TYPE_SET.contains(
                value.getClass().getSimpleName().toLowerCase().intern()
        );
    }

    /**
     * getkey 获取相关数据
     */
    public static String getkey(String ... strs){
        return Joiner.on('#').skipNulls().join(strs);
    }
    public static Map convertMapByStr(String str){
        return Splitter.on('&').withKeyValueSeparator('=').split(str);
    }

    public static void main(String[] args) {
        String a = "asd";
        String simpleName = a.getClass().getSimpleName();
        System.out.println(simpleName);

        String getkey = getkey("1", "2", "3");
        System.out.println(getkey);

        Map map = convertMapByStr("id=123&name=green");
        System.out.println(map.toString());
    }
}
