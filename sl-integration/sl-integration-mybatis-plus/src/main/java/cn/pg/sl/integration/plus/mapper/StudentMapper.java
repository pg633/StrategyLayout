package cn.pg.sl.integration.plus.mapper;

import cn.pg.sl.integration.plus.entity.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lqh
 * @since 2018-05-05
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("selectStudentByStuName")
    List<Student> selectStudentByStuName(String stuName);

}