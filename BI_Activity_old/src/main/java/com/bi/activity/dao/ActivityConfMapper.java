package com.bi.activity.dao;

import java.util.List;
import java.util.Map;

import com.bi.activity.entity.ActivityConf;

public interface ActivityConfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityConf record);

    int insertSelective(ActivityConf record);

    ActivityConf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityConf record);

    int updateByPrimaryKey(ActivityConf record);
    
    List<ActivityConf> selectActivityConfs(Map<String, Object> map);
}