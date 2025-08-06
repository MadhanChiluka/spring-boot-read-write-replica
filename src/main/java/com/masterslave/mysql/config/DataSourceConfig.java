package com.masterslave.mysql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.masterslave.mysql.util.DataSourceType;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.master.datasource")
    public HikariDataSource masterDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.slave.datasource")
    public HikariDataSource slaveDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        DynamicDataSource datasource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.getType(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.getType(), slaveDataSource);
        datasource.setTargetDataSources(targetDataSources);
        return datasource;
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

}
