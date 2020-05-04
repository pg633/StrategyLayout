package com.pg.sl.web.controller;

import com.pg.sl.common.dao.IndicatorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/2 11:42 下午
 */
@RestController
public class HiController {
    @RequestMapping("/hi")
    @ResponseBody
    public String gethI() {
        return port;
    }

    @Value("${server.port}")
    private String port;


    @Autowired
    private IndicatorDao aa;

    @RequestMapping("/testSql")
    @ResponseBody
    public String getsql() {
//        System.out.println(aa.getAllIndicator());
//        System.out.println(aa == null);
//        return aa == null ? "ad" : "dd";
        return "asd";
    }


}
