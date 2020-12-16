/**
 * ActivityService.java
 * com.bi.activity.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月4日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.service;

import java.util.List;
import java.util.Map;

import com.bi.activity.dto.StaticticsTask;
import com.bi.activity.dto.StatisticsInvitationRegUser;
import com.bi.activity.dto.StatisticsRegUser;
import com.bi.activity.entity.ActivityConf;
import com.bi.activity.entity.ActivityDataPoints;
import com.bi.activity.entity.ActivityIndex;
import com.bi.activity.entity.ActivityTail;
import com.bi.activity.entity.Ranking;
import com.bi.activity.util.ZPageUtil;

/**
 * ClassName:ActivityService（Describe this Class）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月4日		下午4:59:10
 * @see 	 
 */
public interface ActivityService {
    
    Boolean checkActivity(Map<String, Object> map);
    
    Boolean points(Map<String, Object> map);
    
    List<ActivityIndex> findActivitys(Map<String, Object> map);
    
    List<ActivityConf> findActivityConfs(Map<String, Object> map);
    
    String award(Map<String, Object> map);
    
    List<Ranking> ranking(Map<String, Object> map);
    
    Ranking rankingByMemberId(Map<String, Object> map);
    
    ZPageUtil<ActivityDataPoints> friends(Map<String, Object> map);
    
    ZPageUtil<ActivityTail> friendsAward(Map<String, Object> map);
    
    boolean findNewUserByDay(String dayStart,String dayEnd,String url);
    
    boolean findActiveTimeByHour(String dayStart,String dayEnd,String url);
    
    boolean findInvitationRegUserByDay(String dayStart,String dayEnd,String url);
    
    boolean findInvitationRegUserTask(String dayEnd,String url);

}

