package com.lg.datadispose.module.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lg.datadispose.module.bean.po.CenterIntegralExplain;
import com.lg.datadispose.module.bean.vo.ResultVO;
import com.lg.datadispose.module.conf.RedisCache;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.service.IntegralService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/test/")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private IntegralService integralService;

	@ApiOperation(value = "test————zlg", notes = "无")
	@RequestMapping(value = "test", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<?> test() {
		System.out.println("进入test方法.......");
		return new ResultVO<Object>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, "");
	}

	@ApiOperation(value = "查询积分规则————zlg", notes = "无")
	@RequestMapping(value = "/queryExplanIntegral", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<PageInfo<?>> queryExplanIntegral(@Valid Map<String, String> map) {
		PageInfo<CenterIntegralExplain> pageinfo = this.integralService.queryExplanIntegral(map);
		return new ResultVO<PageInfo<?>>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, pageinfo);
	}
	
	@Autowired
	private RedisCache redisCache;
	
	@ApiOperation(value = "test2————zlg", notes = "无")
	@RequestMapping(value = "test2", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<?> test2(@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, String> map) {
		System.out.println("进入test方法.......");
		String vipKey = DataDisposeConstant.VIPLEVEL_USER_LEVEL + map.get("userId");
		String string = this.redisCache.get(vipKey);
		return new ResultVO<String>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, string);
	}

	
}
