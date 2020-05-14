package com.pg.sl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/14 9:30 下午
 */
@Mapper
public interface GroovyDao {


    @Select(value = "select script " + " from groovy_func_udf    ")
//        @Results(id = "unitIndiFuncaMap", value = {
//                @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
//                @Result(property = "funcId", column = "indicator_provider_id", jdbcType = JdbcType.INTEGER),
//                @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
//                @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
//                @Result(property = "dateType", column = "data_type", jdbcType = JdbcType.VARCHAR),
//        })
    String getAllFuncPara();


}
