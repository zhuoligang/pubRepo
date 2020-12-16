package com.bi.activity.dao;

import java.util.List;
import java.util.Map;

import com.bi.activity.dto.AdminLog;
import com.bi.activity.dto.StaticticsTask;
import com.bi.activity.dto.StatisticsInvitationRegUser;
import com.bi.activity.dto.StatisticsRegUser;
import com.bi.activity.entity.ActivityDataPoints;
import com.bi.activity.entity.Ranking;

public interface ActivityDataPointsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityDataPoints record);

    int insertSelective(ActivityDataPoints record);

    ActivityDataPoints selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityDataPoints record);

    int updateByPrimaryKey(ActivityDataPoints record);
    
    ActivityDataPoints selectByMemberId(Integer memberId);
    
    List<Ranking> ranking(Map<String, Object> map);
    
    Ranking selectRankingByMemberId(Map<String, Object> map);
    
    List<ActivityDataPoints> friends(Map<String, Object> map);
    
    int friendsCount(Map<String, Object> map);
    
    List<StatisticsRegUser> selectNewUserByDay(String dayStart,String dayEnd);
    
    List<AdminLog> selectActiveTimeByHour(String dayStart,String dayEnd);
    
    List<StatisticsInvitationRegUser> selectIsInvitationRegUserByDay(String dayStart,String dayEnd);
    
    List<StatisticsInvitationRegUser> selectNotInvitationRegUserByDay(String dayStart,String dayEnd);
    
    List<StaticticsTask> selectInvitationRegUserTask(String dayEnd);
}