import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pg.sl.common.exec.AsyncLeader;
import com.pg.sl.common.exec.Leader;
import com.pg.sl.common.exec.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class LeaderTest {

    @Test
    public void test(){
        Map<String, Task> map = Maps.newHashMap();
        map.put("1", new Task() {
            @Override
            public Object doTask() {

                return "a";
            }
        });


        map.put("2", new Task() {
            @Override
            public Object doTask() {

                return "b";
            }
        });

//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(9); // 创建一个大小为9的固定线程池，可以按照CPU的核数初步判定，如果CPU密集性任务则创建N+1个，如果是IO密集型任务则创建2N+1个，其中N即CPU的核数
//
//
//        Object o = Leader.doExe(
//                map, executor, 100
//
//        );
//        String s = JSONObject.toJSONString(o);
//        Assert.assertEquals(s,"{\"1\":\"a\",\"2\":\"b\"}");

    }
    @Test
    public void testAyncLeader(){

        List<Task> tasks = Arrays.asList(new Task() {
            @Override
            public Object doTask() {
                System.out.println("in task");
                return "a";
            }
        });
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(9); // 创建一个大小为9的固定线程池，可以按照CPU的核数初步判定，如果CPU密集性任务则创建N+1个，如果是IO密集型任务则创建2N+1个，其中N即CPU的核数
        Logger logger = LoggerFactory.getLogger(LeaderTest.class);
//        AsyncLeader.doExe(tasks,executor,logger);
    }

}
