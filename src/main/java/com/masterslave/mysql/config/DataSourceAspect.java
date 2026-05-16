package com.masterslave.mysql.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.masterslave.mysql.annotations.DataSource;

@Aspect
@Component
public class DataSourceAspect {

    @Before(value = "@annotation(dataSource)")
    public void dataSourcePoint(JoinPoint joinPoint, DataSource dataSource) {
        DynamicDataSourceHandler.putDataSource(dataSource.value());
    }

    @After(value = "@annotation(dataSource)")
    public void clearDataSource(JoinPoint joinPoint, DataSource dataSource) {
        DynamicDataSourceHandler.clearDataSource();
    }
}
