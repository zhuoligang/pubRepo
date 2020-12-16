/**
 * BaseAutowired.java com.ejie.compliance.web
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.0 2018年1月18日 zlg
 * 
 * Copyright (c) 2018, EJie All Rights Reserved.
 */

package com.bi.activity.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.bi.activity.dao.ActivityConfMapper;
import com.bi.activity.dao.ActivityDataPointsMapper;
import com.bi.activity.dao.ActivityIndexMapper;
import com.bi.activity.dao.ActivityTailMapper;
import com.bi.activity.dao.MemberLogMapper;
import com.bi.activity.dao.MemberMapper;
import com.bi.activity.dao.MemberVirtualcoinMapper;
import com.bi.activity.dao.VcoinrecordMapper;
import com.bi.activity.dao.VirtualcoinMapper;



/**
 * ClassName:BaseAutowired 公用dao层注入类
 * 
 * @author zlg
 * @version Ver 1.0
 * @Date 2018年1月18日 上午10:06:42
 * @see
 */
public class BaseAutowired {
    
    @Autowired
    protected MemberMapper memberDao;
    @Autowired
    protected MemberVirtualcoinMapper memberVirtualcoinDao;
    @Autowired
    protected ActivityConfMapper activityConfDao;
    @Autowired
    protected ActivityDataPointsMapper activityDataPointsDao;
    @Autowired
    protected ActivityIndexMapper activityIndexDao;
    @Autowired
    protected ActivityTailMapper activityTailDao;
    @Autowired
    protected VcoinrecordMapper vcoinrecordDao;
    @Autowired
    protected VirtualcoinMapper virtualcoinDao;
    @Autowired
    protected MemberLogMapper memberLogDao;
    
}
