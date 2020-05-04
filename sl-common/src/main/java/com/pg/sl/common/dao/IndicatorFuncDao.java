package com.pg.sl.common.dao;

import com.pg.sl.common.bean.TbUintIndicatorPara;
import com.pg.sl.common.bean.TbUnitFunc;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:54 上午
 */
@Mapper
public interface IndicatorFuncDao {

    String INDICATOR_FUNC_COLUME = "  id,name,description,return_type,status,source_type ";

    @Select("select " + INDICATOR_FUNC_COLUME + " from unit_indicator_provider where status = 1 limit 1000 ")
    @Results(id = "unitIndiFuncaMap", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
            @Result(property = "returnType", column = "return_type", jdbcType = JdbcType.VARCHAR),
            @Result(property = "sourceType", column = "source_type", jdbcType = JdbcType.VARCHAR),
            @Result(property = "status", column = "status", jdbcType = JdbcType.INTEGER),
    })
    List<TbUnitFunc> getAllIndicatorFunc();


    @Select(
            "  select\n" +
                    INDICATOR_FUNC_COLUME +
                    "  from credit_dtc_indicator_param \n" +
                    "  where status = 1\n" +
                    "  and name = #{name}\n" +
                    "  limit 1")
    @ResultMap("unitIndiFuncaMap")
    TbUnitFunc getIndicatorParaByName(@Param("name") String indiName);



}
