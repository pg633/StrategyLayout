package cn.pg.sl.integration.plus.service;

import cn.pg.sl.integration.plus.entity.Student;
import cn.pg.sl.integration.plus.mapper.StudentMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lqh
 * @since 2018-05-05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {


    @Override
    public List<Student> selectStudentByStuName(String Student) {
//        return this.baseMapper.selectStudentByStuName(Student);
        return null;
    }
}
