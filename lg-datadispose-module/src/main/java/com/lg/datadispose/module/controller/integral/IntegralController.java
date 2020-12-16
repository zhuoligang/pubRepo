package com.lg.datadispose.module.controller.integral;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.bean.vo.ResultVO;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.dao.userdao.CenterUserMainMapper;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.service.IntegralService;
import com.lg.datadispose.module.task.SettlementTask;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/integral/")
public class IntegralController {

	@Autowired
	private IntegralService integralService;
	
	@Autowired
	private CenterUserMainMapper centerUserMainDao;
	
	@Autowired
	private SettlementTask settlementTask;

	@ApiOperation(value = "结算指定老用户积分————zlg", notes = "用户id    userId（必填）")
	@RequestMapping(value = "/settlementOfIntegral", method = RequestMethod.POST)
	@ApiIgnore
	@ResponseBody
	public ResultVO<String> settlementOfIntegral(
			@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, String> map) {
		if (map == null || map.get("userId") == null) {
			throw new BusinessException(DataDisposeConstant.CODE_500, DataDisposeConstant.FILL_IN_REQUIRED);
		}
		CenterUserMain selectByPrimaryKey = this.centerUserMainDao.selectByPrimaryKey(map.get("userId"));
		this.integralService.settlementOfIntegral(selectByPrimaryKey);
		return new ResultVO<String>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, "");
	}

	@ApiOperation(value = "结算所有老用户积分、vip等级————zlg", notes = "无")
	@RequestMapping(value = "/settlementAllOfUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<String> settlementAllOfUser() {
		settlementTask.executeAsync();
		return new ResultVO<String>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, "");
	}

}
