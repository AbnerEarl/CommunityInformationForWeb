package com.zhang.shequ.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * MybatisPlus配置
 */
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.zhang.shequ.core.mapper"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    /**
     * 数据源连接池配置
     */
    @Bean
    public DruidDataSource singleDatasource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
    
    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
/*    @Bean
    public PerformanceInterceptor performanceInterceptor() {
      return new PerformanceInterceptor();
    }*/

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}