package com.bi.activity.dao;

import java.util.List;

import com.bi.activity.entity.ActivityIndex;

public interface ActivityIndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityIndex record);

    int insertSelective(ActivityIndex record);

    ActivityIndex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityIndex record);

    int updateByPrimaryKey(ActivityIndex record);
    
    List<ActivityIndex> selectActivityIndexs();
}