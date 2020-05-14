package com.pg.sl.udf.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:43 上午
 */
@Configuration
//@ImportResource(locations = {"classpath*:sl-udf-dao.xml"})
@ComponentScan(value = "com.pg.sl.udf")
public class UdfAutoConf {
}
