package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.pg.sl.dao.GroovyDao;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.collections.Maps;
import org.testng.reporters.jq.Main;

import java.io.File;
import java.net.URL;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 8:48 上午
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(locations = "classpath:sl-udf-dao.xml")
public class GroovyBeanTest {
    //    @Autowired
//    GroovyBeanManager groovyBeanManager;
//    @Test
//    public void func(){
//        System.out.println("aa");
//        System.out.println(groovyBeanManager==null?"null":"not null");
//    }
    @Autowired
    private GroovyDao groovyDao;
     @Test
    public void testDao(){
//         System.out.println(datasource==null);
        System.out.println(groovyDao.getAllScript());

    }
//    @Autowired
//    @Qualifier("ss")
//    String ss;
//
//    @Test
//    public void testAutoConf() {
//
//        System.out.println(ss);
//    }

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        GroovyClassLoader loader = new GroovyClassLoader();
// 找到文件夹所在路径
        String file = "import com.pg.sl.udf.api.AbstractGroovyAction\n" +
                "import org.slf4j.Logger\n" +
                "import org.slf4j.LoggerFactory\n" +
                "import org.springframework.util.CollectionUtils\n" +
                "\n" +
                "/**\n" +
                " * @author lianzheng04* @date 2020/5/15 1:16 下午\n" +
                " * @version 1.0\n" +
                " */\n" +
                "class FakeUserGroovy extends AbstractGroovyAction {\n" +
                "    private static final Logger logger = LoggerFactory.getLogger(\"FakeUserGroovy\")\n" +
                "    private static final String indiName = \"creditlist_dpid_whitelist_write\";\n" +
                "\n" +
                "    @Override\n" +
                "    Object doAction(Map<String, Object> params) throws Exception {\n" +
                "        if (CollectionUtils.isEmpty(params)) {\n" +
                "            return null\n" +
                "        }\n" +
                "        return params.get(indiName)\n" +
                "    }\n" +
                "\n" +
                "    static void main(String[] args) {\n" +
                "        def map  = ['creditlist_dpid_whitelist_write1':'asd']\n" +
                "        def action = new FakeUserGroovy().doAction(map)\n" +
                "        println action\n" +
                "    }\n" +
                "}\n";
            Class groovyClass = loader.parseClass(file);
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        Map<Object, Object> objectObjectMap = Maps.newHashMap();
        objectObjectMap.put("creditlist_dpid_whitelist_write",123);
        Object doAction = groovyObject.invokeMethod("doAction", objectObjectMap);
        System.out.println(doAction);


    }
}
