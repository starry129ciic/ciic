package com.ciic.reporter.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    public Map<Object,Object> myMap = null;
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
