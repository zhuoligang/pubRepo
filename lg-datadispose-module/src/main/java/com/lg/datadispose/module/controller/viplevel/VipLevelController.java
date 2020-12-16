package com.lg.datadispose.module.controller.viplevel;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.bean.vo.ResultVO;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.dao.userdao.CenterUserMainMapper;
import com.lg.datadispose.module.service.VipLevelService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/viplevel/")
public class VipLevelController {
	
	@Autowired
	private VipLevelService ipLevelService;
	@Autowired
	private CenterUserMainMapper centerUserMainDao;
	
	/*
	 * 查询用户BT持仓量
	 */
	@ApiOperation(value = "获取用户bt持仓量————zlg", notes = "用户uuid    userId（必填）")
	@RequestMapping(value = "/getUserBtCount", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<?> getUserBtCount(@Valid String userId){
		BigDecimal userBtCount = this.ipLevelService.getUserBtCount(userId);
		return new ResultVO<BigDecimal>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, userBtCount);
	}
	
	@ApiOperation(value = "查询用户最近30天成交折算CNT值（以当前币种最新价格折算）————zlg", notes = "用户uuid    userId（必填）")
	@RequestMapping(value = "/getTurnoverDay30", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<?> getTurnoverDay30(@Valid String userId){
		BigDecimal turnoverDay30 = this.ipLevelService.getTurnoverDay30(userId);
		return new ResultVO<BigDecimal>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, turnoverDay30);
	}
	
	@ApiOperation(value = "用户vip等级结算————zlg", notes = "用户uuid    userId（必填）")
	@RequestMapping(value = "/settlementOfVipLevel", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<?> settlementOfVipLevel(@Valid String userId){
		CenterUserMain selectByPrimaryKey = this.centerUserMainDao.selectByPrimaryKey(userId);
		this.ipLevelService.settlementOfVipLevel(selectByPrimaryKey);
		return new ResultVO<String>(DataDisposeConstant.CODE_200, DataDisposeConstant.SUCCESS, "");
	}
	
}
