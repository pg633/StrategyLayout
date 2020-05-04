package com.pg.sl.common.dao;

import com.pg.sl.common.bean.TbUnitGroovy;
import com.pg.sl.common.bean.TbUnitIndicator;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import javax.xml.ws.WebServiceRef;
import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 12:20 上午
 */
@Mapper
public interface IndicatorDao {
    String INDICATOR_COLUME = "  id,name,description,indi_injec_id,level,status,store_node,expire_time ";

    @Select("select " + INDICATOR_COLUME + " from unit_indicator where status = 1 limit 1000 ")
    @Results(id = "unitIndiMap", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
            @Result(property = "indiInjecId", column = "indi_injec_id", jdbcType = JdbcType.INTEGER),
            @Result(property = "level", column = "level", jdbcType = JdbcType.INTEGER),
            @Result(property = "status", column = "status", jdbcType = JdbcType.INTEGER),
            @Result(property = "storeNode", column = "store_node", jdbcType = JdbcType.VARCHAR),
            @Result(property = "expireTime", column = "expire_time", jdbcType = JdbcType.INTEGER),
    })
    List<TbUnitIndicator> getAllIndicator();


    @Select("select " + INDICATOR_COLUME + " from unit_indicator"
            + " where status = 1 "
            + " and name= #{name} "
            + "limit 1000 ")
    TbUnitIndicator selectUnitByName(@Param("name") String name);

    @Select(
            "select\n" +
                    "            a.name as indiName ,b.class_name as groovyName\n" +
                    "            from unit_indicator a\n" +
                    "            join credit_groovy_template b\n" +
                    "            on a.postposition_groovy_id = b.id\n" +
                    "            where a.status = 1\n" +
                    "            and a.postposition_groovy_id != 0\n" +
                    "            and a.postposition_groovy_id is not null\n" +
                    "            limit 10000"
    )
    @Results(id = "indiGroovyMap", value = {
            @Result(property = "indiName", column = "indiName", jdbcType = JdbcType.VARCHAR),
            @Result(property = "groovyName", column = "groovyName", jdbcType = JdbcType.VARCHAR),
    })
    List<TbUnitGroovy> getIndiScription();


}
