package com.bi.activity.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bi.activity.dto.BaseResult;
import com.bi.activity.dto.MemberVirtualcoinDto;
import com.bi.activity.entity.Virtualcoin;
import com.bi.activity.service.AcceptCorrelationService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
* @ClassName: AcceptCorrelationController
* @Description: TODO(承兑平台对接控制台)
* @author zhuoligang
* @date 2018年10月9日下午10:02:12
*
 */
@Controller
@RequestMapping("/accept")
public class AcceptCorrelationController {
	
    @Autowired
    private AcceptCorrelationService acceptCorrelationService;
	
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "承兑相关————获取指定用户数据到承兑平台",notes = "用户id        id（必填）")
    @RequestMapping(value = "/gainAcceptById", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Map<String, Object>> gainAcceptById(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map) {
        int code = 0;
        String msg = "执行失败";
        Map<String, Object> map_ = new HashMap<String, Object>();
        try {
        	map_ = acceptCorrelationService.gainAcceptById(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Map<String, Object>>(code, msg, map_);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "承兑相关————获取指定用户指定币种数据到承兑平台",notes = "用户id        memberId（必填）<br>"
    		+ "币种id集合[]		virtualcoinIds（选填）")
    @RequestMapping(value = "/gainAcceptByIdPlus", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<List<MemberVirtualcoinDto>> gainAcceptByIdPlus(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map) {
        int code = 0;
        String msg = "执行失败";
        List<MemberVirtualcoinDto> memberVirtualcoins = null;
        try {
        	memberVirtualcoins = acceptCorrelationService.gainAcceptByIdPlus(map);
            code = 1;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<List<MemberVirtualcoinDto>>(code, msg, memberVirtualcoins);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "承兑相关————划转",notes = "用户id        memberId（必填）<br>"
    		+ "币种id		virtualcoinId（必填）<br> 数量			count（必填）<br> 划转类型virtualcoinType			（必填，80：转入，81：转出）")
    @RequestMapping(value = "/transferred", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<String> transferred(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map) {
        int code = 0;
        String msg = "执行失败";
        String str = "";
        try {
        	str = acceptCorrelationService.transferred(map);
        	if(str.equals("ok")){
                code = 1;
        	}
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<String>(code, msg, str);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "承兑相关————获取指定平台币基本信息",notes = "币种id        id（必填）")
    @RequestMapping(value = "/getVirtualcoin", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Virtualcoin> getVirtualcoin(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map) {
        int code = 0;
        String msg = "执行失败";
        Virtualcoin virtualcoin = null;
		if(map == null || map.get("id") == null){
			return new BaseResult<Virtualcoin>(code, "缺少必要参数", virtualcoin);
		}
        try {
        	virtualcoin = acceptCorrelationService.getVirtualcoin(map);
        	code = 1 ;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Virtualcoin>(code, msg, virtualcoin);
        
    }
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "修改指定用户基本信息————zlg",
			notes = "用户id		memberId（必填）<br>"
					+ "是否启用（0：冻结，1：启用）		enabled（非必填）<br>"
					+ "登陆密码								password（非必填）<br>"
					+ "交易密码								tradePassword（非必填）<br>"
					+ "姓名									name（非必填）<br>"
					+ "身份证号								idcard（非必填）<br>"
					+ "身份证正面								idcardpicfront（非必填）<br>"
					+ "身份证反面								idcardpicback（非必填）<br>"
					+ "手持身份证（最清晰一帧）图像				idcardpiconhand（非必填）<br>"
					+ "高级认证状态							idcardpiccheckId（非必填）<br>"
					+ "高级认证方式（0：老，1：新）				idcardpicchecktype（非必填）<br>"
					)
	@RequestMapping(value = "/updateMemberByMemberId", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<String> updateMemberByMemberId(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map,HttpServletRequest request) {
        int code = 0;
        String msg = "执行失败";
        String str = null;
		try {
			str = acceptCorrelationService.updateMemberByMemberId(map,request);
        	if(str.equals("ok")){
                code = 1;
        	}
            msg = "执行成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResult<String>(code, msg, str);
	}
    
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "Authorization", value = "token",
            required = true, dataType = "String")})
    @ApiOperation(value = "承兑相关————批量冻结、解冻用户",notes = "用户id集合        memberIds（必填）<br> 是否启用（0：冻结，1：启用）		enabled（必填）")
    @RequestMapping(value = "/frozenMembers", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public BaseResult<Boolean> frozenMembers(@RequestBody @ApiParam(name = "条件map",
            value = "jeson格式map", required = true) Map<String, Object> map) {
        int code = 0;
        String msg = "执行失败";
        Boolean bool = false;
		if(map == null || map.get("memberIds") == null || map.get("enabled") == null){
			return new BaseResult<Boolean>(code, "缺少必要参数", bool);
		}
        try {
        	bool = acceptCorrelationService.frozenMembers(map);
        	code = 1 ;
            msg = "执行成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult<Boolean>(code, msg, bool);
        
    }

}
