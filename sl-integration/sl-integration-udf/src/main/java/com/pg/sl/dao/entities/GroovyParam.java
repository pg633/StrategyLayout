package com.pg.sl.dao.entities;

import lombok.Builder;
import lombok.Data;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/15 1:26 下午
 */
@Data
@Builder
public class GroovyParam {
        private int id;// id自增
        private String name;// 脚本名字
        private String description;// 脚本功能描述
        private String script;// 脚本
        private int status;// 脚本状态
}
