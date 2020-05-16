package cn;

import cn.pg.sl.integration.plus.entity.Student;
import cn.pg.sl.integration.plus.service.IStudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/16 8:45 上午
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class MybatisConfTest {
    @Autowired
    private IStudentService studentService;



    @Test
    public void funcMybatis(){
//        Student student = new Student()
//                .setStuName("zhangsan")
//                .setStuNumber("54")
//                .setAge(23);
//
//        System.out.println(studentService.insert(student));
        Assert.assertNull(studentService);
    }
}
