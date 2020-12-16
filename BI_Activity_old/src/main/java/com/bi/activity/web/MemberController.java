/**
 * MemberController.java
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
import com.bi.activity.entity.Member;
import com.bi.activity.service.MemberService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * ClassName:MemberController（Describe this Class）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月4日		下午1:15:19
 * @see 	 
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "用户————根据用户id查询用户信息",notes = "用户id        id（必填）")
    @RequestMapping(value = "/findMember", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Member> findMember(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Member member = null;
        try {
            member = memberService.findMember(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Member>(code, msg, member);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "用户————获取缓存中的用户信息",notes = "用户id        id（必填）")
    @RequestMapping(value = "/getCacheMember", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Member> getCacheMember(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Member member = null;
        try {
            member = memberService.getCacheMember(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Member>(code, msg, member);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "用户————将指定用户信息放入缓存",notes = "用户id        id（必填）<br>"
    		+ "是否需要先检查缓存中是否存在该用户信息		flag（选填）")
    @RequestMapping(value = "/putCacheMember", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Boolean> putCacheMember(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Boolean bool = false;
        try {
        	bool = memberService.putCacheMember(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Boolean>(code, msg, bool);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "用户————将指定用户信息从缓存中清除",notes = "用户id        id（必填）")
    @RequestMapping(value = "/deleteCacheMember", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Boolean> deleteCacheMember(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map, @ApiIgnore HttpSession session) {
        int code = 0;
        String msg = "执行失败";
        Boolean bool = false;
        try {
        	bool = memberService.deleteCacheMember(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Boolean>(code, msg, bool);
        
    }
    
}

