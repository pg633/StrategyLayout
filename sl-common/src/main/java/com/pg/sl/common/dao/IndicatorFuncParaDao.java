package com.pg.sl.common.dao;

import com.pg.sl.common.bean.TbUnitFunc;
import com.pg.sl.common.bean.TbUnitFuncPara;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 11:02 上午
 */
@Mapper
public interface IndicatorFuncParaDao {
    String INDICATOR_FUNC_PARA_COLUME = "  id,indicator_provider_id,name,description,data_type ";


    @Select(value = "select " + INDICATOR_FUNC_PARA_COLUME + " from unit_indicator_provider_param  limit 1000 ")
    @Results(id = "unitIndiFuncaMap", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "funcId", column = "indicator_provider_id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
            @Result(property = "dateType", column = "data_type", jdbcType = JdbcType.VARCHAR),
    })
    List<TbUnitFuncPara> getAllFuncPara();


//    @Select(
//            "  select\n" +
//                    INDICATOR_FUNC_PARA_COLUME +
//                    "  from credit_dtc_indicator_param \n" +
//                    "  where status = 1\n" +
//                    "  and name = #{name}\n" +
//                    "  limit 1")
//    @ResultMap("unitIndiFuncaMap")
//    TbUnitFunc getIndicatorParaByName(@Param("name") String indiName);

}
