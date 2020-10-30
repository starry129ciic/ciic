package com.ciic.reporter.common;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


    public static List<Object> dataSourceIds = new ArrayList<Object>();


    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }


    public static String getDataSourceType() {
        return contextHolder.get();
    }


    public static void clearDataSourceType() {
        contextHolder.remove();
    }


    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
