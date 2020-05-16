//package cn.pg.sl.integration.plus;
//
//import cn.pg.sl.integration.plus.entity.Student;
//import cn.pg.sl.integration.plus.entity.User;
//import cn.pg.sl.integration.plus.service.IStudentService;
//import cn.pg.sl.integration.plus.service.UserService;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author lianzheng04
// * @version 1.0
// * @date 2020/5/16 10:33 上午
// */
//@SpringBootApplication
//@RestController
//public class App {
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(App.class, args);
//    }
//    @Autowired
//    private IStudentService studentService;
//    @RequestMapping("/hi")
//    public String ff(){
//
//        Student student = new Student()
//                .setStuName("zhangsan")
//                .setStuNumber("54")
//                .setAge(23);
//
//        System.out.println(studentService.insert(student));
//        return "asd";
//    }
//}
//
