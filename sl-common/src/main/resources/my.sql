CREATE TABLE `unit_indicator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID表示',
  `name` varchar(250) NOT NULL DEFAULT '' COMMENT '指标名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '指标描述',
  `indi_injec_id` int(11) NOT NULL  COMMENT '服务id',
  `level` int(1) DEFAULT NULL COMMENT '等级',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `create_operator` varchar(64)   DEFAULT '' COMMENT '创建人',
  `update_operator` varchar(64)   DEFAULT '' COMMENT '修改人',
  `store_node` varchar(256) NOT NULL DEFAULT '' COMMENT '存储节点，可以有多个，以英文逗号分隔',
  `category_id` int(11) NOT NULL DEFAULT '-1' COMMENT '业务分类',
  `expire_time` int(8) NOT NULL DEFAULT '0' COMMENT '指标超时时间，单位ms',
  `postposition_groovy_id` int(11) DEFAULT NULL COMMENT '后置groovy脚本id',
  PRIMARY KEY  (`id`),
  UNIQUE   (`name`),
  INDEX   (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4 COMMENT='指标单元表';


use ab;

CREATE TABLE `as_recommend_track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gid` varchar(40) NOT NULL COMMENT '唯一限制id',
  `table_info` varchar(4) NOT NULL  COMMENT '元数据信息',
  `info` text NOT NULL   COMMENT '操作信息',

  PRIMARY KEY (`id`),
  KEY `idx_as_recommend_track_gid` (`gid`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 COMMENT='算法流水记录表'