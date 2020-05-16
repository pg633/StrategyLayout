package com.pg.sl.dao;

import com.pg.sl.dao.entities.GroovyParam;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 9:30 下午
 */
@Mapper
public interface GroovyDao {


    @Select("select `id`,`name`, `description`, `script`, `status`\n" +
            "from groovy_func_udf\n" +
            "where status = 1 ;")
    @Results(id = "groovyParamMap", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
            @Result(property = "script", column = "script", jdbcType = JdbcType.VARCHAR),
            @Result(property = "status", column = "status", jdbcType = JdbcType.INTEGER),
    })
    List<GroovyParam> getAllScript();


    @Select("select `id`,`name`, `description`, `script`, `status`\n" +
            "from groovy_func_udf\n" +
            "where status = 1 " +
            "and name= #{name} ")
    @ResultMap("groovyParamMap")
    GroovyParam getScriptByName(String name);






}
