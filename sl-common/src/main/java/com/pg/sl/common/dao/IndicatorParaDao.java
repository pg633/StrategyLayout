package com.pg.sl.common.dao;

import com.pg.sl.common.bean.TbUintIndicatorPara;
import com.pg.sl.common.bean.TbUnitIndicator;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 10:33 上午
 */
@Mapper
public interface IndicatorParaDao {


    String INDICATOR_PARA_COLUME = "  id,indicator_id,source_type,param_name,param_value,status ";

    @Select("select " + INDICATOR_PARA_COLUME + " from unit_indicator_param where status = 1 limit 1000 ")
    @Results(id = "unitIndiParaMap", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "param_name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "indicatorId", column = "indicator_id", jdbcType = JdbcType.INTEGER),
            @Result(property = "referKey", column = "param_value", jdbcType = JdbcType.VARCHAR),
            @Result(property = "sourceType", column = "source_type", jdbcType = JdbcType.VARCHAR),
            @Result(property = "status", column = "status", jdbcType = JdbcType.INTEGER),
    })
    List<TbUintIndicatorPara> getAllIndicatorPara();


    @Select(
            "  select\n" +
            INDICATOR_PARA_COLUME +
            "  from credit_dtc_indicator_param \n" +
            "  where status = 1\n" +
            "  and indicatorId = #{name}\n" +
            "  limit 1")
    @ResultMap("unitIndiParaMap")
    TbUintIndicatorPara getIndicatorParaByName(@Param("name") String indiName);


}
