package com.pg.sl.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/2 11:36 下午
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
