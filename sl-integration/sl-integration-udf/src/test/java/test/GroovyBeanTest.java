package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:48 上午
 */
@RunWith(SpringRunner.class)
//@EnableAutoConfiguration
@ImportResource(locations = {"classpath*:sl-udf-dao.xml"})
public class GroovyBeanTest {
//    @Autowired
//    GroovyBeanManager groovyBeanManager;
//    @Test
//    public void func(){
//        System.out.println("aa");
//        System.out.println(groovyBeanManager==null?"null":"not null");
//    }
//    @Autowired
//    private GroovyDao groovyDao;
    @Autowired
    private DruidDataSource datasource;
     @Test
    public void testDao(){
         System.out.println(datasource==null);
//        System.out.println(groovyDao.getAllFuncPara());

    }

}
