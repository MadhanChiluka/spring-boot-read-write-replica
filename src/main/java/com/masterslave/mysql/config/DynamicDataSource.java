package com.masterslave.mysql.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.masterslave.mysql.util.DataSourceType;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected String determineCurrentLookupKey() {
        if (DynamicDataSourceHandler.getDataSource() != null) {
            return DynamicDataSourceHandler.getDataSource();
        }
        return DataSourceType.MASTER.getType();
    }
}
