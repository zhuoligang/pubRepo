package com.bi.activity.dao;

import com.bi.activity.entity.TodayLoginLog;

public interface TodayLoginLogMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(TodayLoginLog record);

    int insertSelective(TodayLoginLog record);
    
    int deleteAll();
}