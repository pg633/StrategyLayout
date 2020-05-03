package com.pg.sl.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 12:20 上午
 */
@Mapper
public interface IndicatorDao {

    @Select("SELECT id FROM t limit 1")
    Integer getAll();

}
