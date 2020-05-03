package com.pg.web;

//import com.pg.sl.common.dao.IndicatorDao;
import com.pg.sl.common.dao.IndicatorDao;
import com.pg.sl.web.AppApplication;
import com.pg.sl.web.controller.HiController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void  testConnect(){
        Integer all = dao.getAll();
        System.out.println(all);
    }
}
