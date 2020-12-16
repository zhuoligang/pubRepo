/**
 * DataController.java
 * com.bi.activity.web
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月5日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bi.activity.dto.BaseResult;
import com.bi.activity.entity.ActivityStatistics;
import com.bi.activity.service.ActivityService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * ClassName:DataController（数据控制台）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月5日		上午11:35:36
 * @see 	 
 */
@Controller
@RequestMapping("/acdata")
public class ActivityDataController {
    @Autowired
    private ActivityService activityService;
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "数据————数据埋点",notes = "用户id        memberId（必填）<br>邀请人id        parentId（必填）<br>"
            + "用户手机号        memberTel（必填）<br>注册时间        regTime（必填）<br>注册ip        regIp（必填）<br>"
            + "参加活动id       indexCode（必填）")
    @RequestMapping(value = "/points", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    @ApiIgnore
    public BaseResult<Boolean> points(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Boolean bool = false;
        try {
            bool = activityService.points(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Boolean>(code,msg,bool);
        
    }
  
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "数据————数据统计",notes = "统计选项        status（必填）：1，小时统计项；2，日统计项")
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    @ApiIgnore
    public BaseResult<ActivityStatistics> statistics(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        ActivityStatistics as = null;
        
        return new BaseResult<ActivityStatistics>(code,msg,as);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @RequestMapping(value = "/regPeopleByDay")
    @CrossOrigin
    @ResponseBody
    public BaseResult<Boolean> regPeopleByDay(String day,HttpServletRequest request){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date now = new Date();
    	//获取昨天
    	Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.roll(Calendar.DATE, -1);
        Date date = c.getTime();
        //昨天开始时间
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND,00);
        c.set(Calendar.MILLISECOND, 00);
        Date timeStart =c.getTime();
        String dayStart = sf.format(timeStart); 
        //昨天结束时间
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        Date timeEnd=c.getTime();
        String dayEnd=sf.format(timeEnd);
        String path = this.getClass().getResource("/").getPath();
        String[] split = path.split("/WEB-INF");
        String tmp=split[0];
        String url = tmp.substring(0, tmp.lastIndexOf("/"));
        if (day!=null&&!day.equals("")) {
			dayStart=day+" 00:00:00";
			dayEnd=day+" 23:59:59";
		}

    	boolean first = activityService.findNewUserByDay(dayStart,dayEnd,url);
    	boolean second = activityService.findActiveTimeByHour(dayStart,dayEnd,url);
    	boolean thirdAndFourth = activityService.findInvitationRegUserByDay(dayStart,dayEnd,url);
    	boolean fifth = activityService.findInvitationRegUserTask(dayEnd,url);
    	int code = 0;
        String msg = "";
        if (first&&second&&thirdAndFourth&&fifth) {
        	code=1;
        	msg="统计文件全部生成成功！";
        	return new BaseResult<Boolean>(code,msg,true);
		}
        else if (!first||!second||!thirdAndFourth||!fifth) {
			code=0;
			if (!first) {
				msg+="按天统计注册用户数文件生成失败!\n";
			}
			if (!second) {
				msg+="活跃时间统计文件生成失败!\n";
			}
			if (!thirdAndFourth) {
				msg+="自主/被邀请注册用户数统计文件生成失败!\n";
			}
			if (!fifth) {
				msg+="完成任务情况统计文件生成失败!\n";
			}
			return new BaseResult<Boolean>(code,msg,false);
		}
        else {
        	msg="统计文件全部生成失败";
        	return new BaseResult<Boolean>(0,msg,false);
		}
		
    }

}

