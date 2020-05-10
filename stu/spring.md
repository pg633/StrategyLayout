* spring 启动类
```$java
@SpringBootApplication   spring-boot 启动类
使用了以下三个
@Configuration（@SpringBootConfiguration点开查看发现里面还是应用了@Configuration）
@EnableAutoConfiguration
@ComponentScan

重点看下 EnableAutoConfiguration
EnableAutoConfiguration-> AutoConfigurationImportSelector 借助完成aoc

autoConf  spring-factories
@ConditionalOnClass ： classpath中存在该类时起效
@ConditionalOnMissingClass ： classpath中不存在该类时起效
@ConditionalOnBean ： DI容器中存在该类型Bean时起效
@ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效
@ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效
@ConditionalOnExpression ： SpEL表达式结果为true时
@ConditionalOnProperty ： 参数设置或者值一致时起效
@ConditionalOnResource ： 指定的文件存在时起效
@ConditionalOnJndi ： 指定的JNDI存在时起效
@ConditionalOnJava ： 指定的Java版本存在时起效
@ConditionalOnWebApplication ： Web应用环境下起效
@ConditionalOnNotWebApplication ： 非Web应用环境下起效



```

//规则 job 参数 

/*
 generated-id 唯一id
 trans-time 时间区间 where dt >= 20200101 and dt <= 20200333
 不穿入为 null ,单独传入 where dt >= 20200101 
 feature-set ,hive 表当中的 select  json_map from table_name 
feature-block-columes 特征字段黑名单不要哪些特征列 
store-location 存储到mysql中的表名 
    数据表字段 
    
    id(自增id,无需管) 
    gid(generated-id 用于筛选特定规则问题)
    table_info(训练表名+日期)
    info(text 用于存储训练信息)
    

*/
--generated-id=123 
--trans-time-left=20200101
--trans-time-right=20200333
--feature-set=json_map
--table-name=ba_rc.tmpXXX
--feature-block-columes=columea,columeb,columec
--rule-radios=5
--store-location=mysql_table_name

// 规则 和上面相同 

//模型 
/**
    存储模型文件位置 pmml
    在mysql 表的text中存储相关的s3上的文件路径 
*/









