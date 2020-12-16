/**
 * MemberService.java
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

import java.util.Map;

import com.bi.activity.entity.Member;

/**
 * ClassName:MemberService（Describe this Class）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月4日		下午1:14:05
 * @see 	 
 */
public interface MemberService {
    
    Member findMember(Map<String, Object> map);
    
    Member getCacheMember(Map<String, Object> map);
    
    boolean putCacheMember(Map<String, Object> map);
    
    boolean deleteCacheMember(Map<String, Object> map);
    
}

