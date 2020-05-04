package com.pg.sl.common.bean;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.type.JdbcType;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 9:46 上午
 */
@Data
@Builder
public class TbUnitIndicator {
    private int id;// id标示
    private String name;// 名称
    private String description;// 描述
    private int indiInjecId;// 所用的服务id
    private int level;// 等级（在链路上的深度）
    private int status;// 状态
    private String storeNode;// 状态
    private int expireTime ;//超时时间
}
