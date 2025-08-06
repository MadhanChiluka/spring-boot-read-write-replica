package com.masterslave.mysql.config;

import com.masterslave.mysql.util.DataSourceType;

public class DynamicDataSourceHandler {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSource(DataSourceType dataSourceType) {
        holder.set(dataSourceType.getType());
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
