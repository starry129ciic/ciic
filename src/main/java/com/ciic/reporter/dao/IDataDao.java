package com.ciic.reporter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface IDataDao {
    /**
     * 查询数据
     * @return
     */
     List<Map<String, Object>> getData(String sql);
}
