/**
 * ActivityController.java
 * com.bi.activity.web
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月4日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bi.activity.dto.BaseResult;
import com.bi.activity.entity.ActivityConf;
import com.bi.activity.entity.ActivityDataPoints;
import com.bi.activity.entity.ActivityIndex;
import com.bi.activity.entity.ActivityTail;
import com.bi.activity.entity.Ranking;
import com.bi.activity.service.ActivityService;
import com.bi.activity.service.impl.ActivityExplain;
import com.bi.activity.util.ZPageUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * ClassName:ActivityController（Describe this Class）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月4日		下午4:53:53
 * @see 	 
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	
    @Autowired
    private ActivityService activityService;
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————验证指定用户是否有参数指定活动的资格",notes = "活动id        activityId（必填）<br>"
            + "用户id        memberId（必填")
    @RequestMapping(value = "/checkActivity", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    @ApiIgnore
    public BaseResult<Boolean> checkActivity(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Boolean bool = false;
        try {
            bool = activityService.checkActivity(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new BaseResult<Boolean>(code,msg,bool);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————查看所有（指定）启用活动信息",notes = "活动id        activityId（选填）")
    @RequestMapping(value = "/findActivitys", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<List<ActivityIndex>> findActivitys(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        List<ActivityIndex> list = null;
        try {
            list = activityService.findActivitys(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<List<ActivityIndex>>(code,msg,list);
        
    }
    
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————查看指定活动下所有（指定）奖励",notes = "活动id        confParentId（必填）<br>"
            + "奖励代码        confId（选填）")
    @RequestMapping(value = "/findActivityConfs", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<List<ActivityConf>> findActivityConfs(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        List<ActivityConf> list = null;
        try {
            list = activityService.findActivityConfs(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<List<ActivityConf>>(code,msg,list);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————用户完成任务触发奖励发放",notes = "用户id        memberId（必填）<br>"
            + "活动代码        indexId（必填）<br>完成任务id        confId（必填）")
    @RequestMapping(value = "/award", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    @ApiIgnore
    public BaseResult<String> award(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        String str = "";
        try {
        	str = activityService.award(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<String>(code,msg,str);
        
    }
    
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————邀请排名奖励",notes = "排名取数		rankingCount（必填）")
    @RequestMapping(value = "/ranking", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<List<Ranking>> ranking(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        List<Ranking> rankings = null;
        try {
        	rankings = activityService.ranking(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<List<Ranking>>(code,msg,rankings);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————个人邀请排名查询",notes = "用户id		memberId（必填）")
    @RequestMapping(value = "/rankingByMemberId", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Ranking> rankingByMemberId(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Ranking ranking = null;
        try {
        	ranking = activityService.rankingByMemberId(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Ranking>(code,msg,ranking);
        
    }  
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————推荐的好友",notes = "推荐人id		parentId（必填）<br>"
    		+ "每页显示条数      pageSize（非必填）<br>" + "查看页码        currentPage（非必填）")
    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<ZPageUtil<ActivityDataPoints>> friends(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        ZPageUtil<ActivityDataPoints> activityDataPoints = null;
        try {
        	activityDataPoints = activityService.friends(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<ZPageUtil<ActivityDataPoints>>(code,msg,activityDataPoints);
        
    }  
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "活动————奖励记录",notes = "推荐人id		memberId（必填）<br>"
    		+ "每页显示条数      pageSize（非必填）<br>" + "查看页码        currentPage（非必填）")
    @RequestMapping(value = "/friendsAward", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<ZPageUtil<ActivityTail>> friendsAward(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        ZPageUtil<ActivityTail> activityDataPoints = null;
        try {
        	activityDataPoints = activityService.friendsAward(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<ZPageUtil<ActivityTail>>(code,msg,activityDataPoints);
        
    }  

}

