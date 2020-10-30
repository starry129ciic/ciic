package com.ciic.reporter.service;

import java.util.List;
import java.util.Map;

public interface IDataService {
    /**
     *
     * @return
     */
    public List<Map<String, Object>> getData(String dataSourceId,String sql);
}
