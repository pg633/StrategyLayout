namespace java.swift com.pg.sl.api.client

include "SlException.thrift"
/**
* 加载资源
**/
service SlManage{
    /**
    *   加载所有指标
    **/
    bool loadAll();

    /**
    *  加载redis 中节点信息，等等
    **/
    bool loadRedis() throws (1:SlException.SlException e);

}