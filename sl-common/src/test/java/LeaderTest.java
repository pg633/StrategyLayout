import com.google.common.collect.Maps;
import com.pg.sl.common.dao.IndicatorDao;
import com.pg.sl.common.exec.Task;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 12:22 下午
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class LeaderTest {

    @Autowired
    private IndicatorDao indicatorDao;
    @Autowired
    DataSource dataSource;
    @Autowired
    SqlSessionFactoryBean sqlSessionFactory ;
    @Autowired
    MapperScannerConfigurer mapperScannerConfigurer;
    private String ss;
    @Test
    public void testInit(){

        System.out.println(dataSource==null);
        System.out.println(sqlSessionFactory ==null);
        System.out.println(mapperScannerConfigurer ==null);
        System.out.println(indicatorDao.getAllIndicator());
    }
//    @Test
//    public void test(){
//        Map<String, Task> map = Maps.newHashMap();
//        map.put("1", new Task() {
//            @Override
//            public Object doTask() {
//
//                return "a";
//            }
//        });
//
//
//        map.put("2", new Task() {
//            @Override
//            public Object doTask() {
//
//                return "b";
//            }
//        });
//    }
//    @Test
//    public void testAyncLeader(){
//
//        List<Task> tasks = Arrays.asList(new Task() {
//            @Override
//            public Object doTask() {
//                System.out.println("in task");
//                return "a";
//            }
//        });
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(9); // 创建一个大小为9的固定线程池，可以按照CPU的核数初步判定，如果CPU密集性任务则创建N+1个，如果是IO密集型任务则创建2N+1个，其中N即CPU的核数
//        Logger logger = LoggerFactory.getLogger(LeaderTest.class);
//    }

}
