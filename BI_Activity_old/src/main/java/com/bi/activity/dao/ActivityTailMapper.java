package com.bi.activity.dao;

import java.util.List;
import java.util.Map;

import com.bi.activity.entity.ActivityTail;

public interface ActivityTailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTail record);

    int insertSelective(ActivityTail record);

    ActivityTail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTail record);

    int updateByPrimaryKey(ActivityTail record);
    
    int selectRankingByMemberId(Map<String, Object> map);
    
    List<ActivityTail> friendsAward(Map<String, Object> map);
    
    int friendsAwardCount(Map<String, Object> map);
}