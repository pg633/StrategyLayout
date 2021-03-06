package com.pg.web;

//import com.pg.sl.common.dao.IndicatorDao;
import com.alibaba.fastjson.JSONObject;
import com.pg.sl.common.bean.TbUintIndicatorPara;
import com.pg.sl.common.bean.TbUnitFunc;
import com.pg.sl.common.bean.TbUnitFuncPara;
import com.pg.sl.common.bean.TbUnitIndicator;
import com.pg.sl.common.dao.IndicatorDao;
import com.pg.sl.common.dao.IndicatorFuncDao;
import com.pg.sl.common.dao.IndicatorFuncParaDao;
import com.pg.sl.common.dao.IndicatorParaDao;
import com.pg.sl.common.enums.ParaEnum;
import com.pg.sl.web.AppApplication;
import com.pg.sl.web.controller.HiController;
import org.apache.ibatis.annotations.Param;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/2 11:56 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppApplication.class)
public class ControllerTests {
    @Autowired
    private HiController hiController;
    @Test
    public void testController() {
        String s = hiController.gethI();
        Assert.assertNotNull(s);
    }

    @Autowired
    private IndicatorDao dao;
    @Test
    public void  testConnectIndicator(){
        List<TbUnitIndicator> all = dao.getAllIndicator();

        String s = JSONObject.toJSONString(all);
        System.out.println(s);
    }
    @Autowired
    private IndicatorParaDao indicatorParaDao;
    @Autowired
    private IndicatorFuncDao indicatorFuncDao;
    @Autowired
    private IndicatorFuncParaDao indicatorFuncParaDao;
    @Test
    public void  testConnectIndicatorpara(){
//        List<TbUintIndicatorPara> all = indicatorParaDao.getAllIndicatorPara()
//                ;
//        String s = JSONObject.toJSONString(all);
//        System.out.println(s);

//        List<TbUnitFunc> all = indicatorFuncDao.getAllIndicatorFunc();
//        List<TbUnitFuncPara> allFuncPara = indicatorFuncParaDao.getAllFuncPara();
//
//
//
//        String s = JSONObject.toJSONString(allFuncPara);
//        System.out.println(s);

        String code = ParaEnum.ARRAY.getCode();
        System.out.println(code);


    }




}
