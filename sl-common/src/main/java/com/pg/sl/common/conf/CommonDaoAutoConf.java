package com.pg.sl.common.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/15 2:06 下午
 */
@Configuration
@ComponentScan("com.pg.sl.common")
@PropertySource("classpath:mysql.properties")
@MapperScan("com.pg.sl.common.dao")
public class CommonDaoAutoConf {

    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.user}")
    String user;
    @Value("${jdbc.password}")
    String password;

    @Bean
    DruidDataSource datasource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setInitialSize(0);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxWait(60000);

        druidDataSource.setValidationQuery("SELECT 1");

        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);

        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(1800);
        druidDataSource.setLogAbandoned(true);

        try {
            druidDataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;

    }


    @Bean
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.pg.sl.common.dao.entities");
//        sqlSessionFactoryBean.setMapperLocations(null);
        return sqlSessionFactory;
    }


}
