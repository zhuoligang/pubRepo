/**
 * ActivityExplain.java
 * com.hongkuncheng.vcoin.servlet
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月13日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bi.activity.dao.ActivityConfMapper;
import com.bi.activity.entity.ActivityConf;
import com.bi.activity.util.SpringContextUtil;

/**
 * ClassName:ActivityExplain（活动解释类）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月13日		上午10:01:37
 * @see 	 
 */
@Service
public class ActivityExplain {
    private static Map<Integer,Object> MAP = new HashMap<Integer,Object>() ;
    
    private ActivityExplain (){
        
    }    
    
    private static class LazyHolder {    
        private static final ActivityExplain INSTANCE = new ActivityExplain();    
    }    
     
    public final static ActivityExplain getInstance() { 
    	if(MAP.isEmpty()){
    		ActivityConfMapper activityConfDao = (ActivityConfMapper) SpringContextUtil.getBean(ActivityConfMapper.class);
            Map<String,Object> map = new HashMap<String,Object>() ;
            map.put("confParentId", 1);
            map.put("confId", 1);
            List<ActivityConf> ac1 = activityConfDao.selectActivityConfs(map);
            MAP.put(1, ac1 == null ? "" : ac1.get(0).getConfName());
            
            map.put("confId", 2);
            List<ActivityConf> ac2 = activityConfDao.selectActivityConfs(map);
            MAP.put(2, ac2 == null ? "" : ac2.get(0).getConfName());

            map.put("confId", 3);
            List<ActivityConf> ac3 = activityConfDao.selectActivityConfs(map);
            MAP.put(3, ac3 == null ? "" : ac3.get(0).getConfName());
    	}
        return LazyHolder.INSTANCE;    
    }
    
    public Map<Integer, Object> getMAP() {
		return MAP;
	}

     
     
}

