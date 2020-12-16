package com.bi.activity.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.bi.activity.dto.BaseAutowired;
import com.bi.activity.dto.MemberVirtualcoinDto;
import com.bi.activity.entity.Member;
import com.bi.activity.entity.MemberLog;
import com.bi.activity.entity.MemberVirtualcoin;
import com.bi.activity.entity.Vcoinrecord;
import com.bi.activity.entity.Virtualcoin;
import com.bi.activity.service.AcceptCorrelationService;
import com.bi.activity.util.MathHelper;
import com.bi.activity.util.SystemHelper;

/**
 * 
* @ClassName: AcceptCorrelationImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Administrator
* @date 2018年10月9日下午10:05:21
*
 */
@Service
public class AcceptCorrelationImpl  extends BaseAutowired implements AcceptCorrelationService{

	@Override
	public Map<String, Object> gainAcceptById(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int id = Integer.parseInt(map.get("id").toString());
		//用户基本信息
		Member member = memberDao.selectByPrimaryKey(id);
		resultMap.put("member", member);
		//用户账户信息
//		Map<String, Object> map_ = new HashMap<String, Object>();
//		map_.put("memberId", id);
//		if(map.get("virtualcoinIds") != null){
//			map_.put("virtualcoinIds", String.valueOf(map.get("virtualcoinIds")).split(","));
//		}
//		List<MemberVirtualcoinDto> memberVirtualcoins = memberVirtualcoinDao.selectMemberVirtualcoinDtosByMemberId(map_);
//		resultMap.put("memberVirtualcoins", memberVirtualcoins);
		return resultMap;
	}

	@Override
	public List<MemberVirtualcoinDto> gainAcceptByIdPlus(Map<String, Object> map) {
		if(map.get("virtualcoinIds") != null){
			map.put("virtualcoinIds", String.valueOf(map.get("virtualcoinIds")).split(","));
		}
		List<MemberVirtualcoinDto> memberVirtualcoins = memberVirtualcoinDao.selectMemberVirtualcoinDtosByMemberId(map);
		return memberVirtualcoins;
	}

	@Override
	public String transferred(Map<String, Object> map) {
		String str = "";
		if(map.get("memberId") == null || map.get("virtualcoinId") == null ||
				map.get("count") == null || map.get("virtualcoinType") == null){
			return str = "缺少必要参数";
		}
		int memberId = Integer.parseInt(map.get("memberId").toString());
		int virtualcoinId = Integer.parseInt(map.get("virtualcoinId").toString());
		int transferredCount = Integer.parseInt(map.get("count").toString());
		int virtualcoinType = Integer.parseInt(map.get("virtualcoinType").toString());
		if(virtualcoinType != 80 && virtualcoinType != 81){
			return str = "不支持的划转类型";
		}
		if(virtualcoinType == 80 && transferredCount < 0 || virtualcoinType == 81 && transferredCount > 0 ){
			return str = "划转类别或数量有误";
		}
		boolean bool = transferredDo(memberId,virtualcoinId,transferredCount);
		if(!bool){
			return str = "划转过程错误";
		}
		str = transferredDoAfter(memberId,virtualcoinId,transferredCount,virtualcoinType);
		return str;
	}
	
	/**
	 * 
	* @Title: transferredDoAfter
	* @Description: TODO(划转成功后续步骤)
	* @param @param memberId
	* @param @param virtualcoinId
	* @param @param transferredCount
	* @param @param flag
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String transferredDoAfter(int memberId, int virtualcoinId, int transferredCount,int virtualcoinType){
		String result = "ok";
		BigDecimal tfCount = new BigDecimal(transferredCount);
		//币流水记录
		Vcoinrecord vcoinrecord = new Vcoinrecord(memberId, virtualcoinId, virtualcoinType, tfCount, BigDecimal.ZERO, new Date());
		int w = vcoinrecordDao.insertSelective(vcoinrecord);
		if (w < 1) return result = "币流水记录写入错误";
		return result;
	}
	
	/**
	 * 
	* @Title: transferred
	* @Description: TODO(划转资金)
	* @param @param memberId
	* @param @param virtualcoinId
	* @param @param transferredCount
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean transferredDo(int memberId, int virtualcoinId, int transferredCount) {
		Boolean bool = false;
		BigDecimal tfCount = new BigDecimal(transferredCount);
		Map<String, Object> map_ = new HashMap<String, Object>();
		map_.put("memberId", memberId);
		map_.put("virtualcoinId", virtualcoinId);
		List<MemberVirtualcoin> memberVirtualcoins = memberVirtualcoinDao.selectByMemberId(map_);
		MemberVirtualcoin memberVirtualcoin = new MemberVirtualcoin();
		if (memberVirtualcoins != null && memberVirtualcoins.size() > 0) {// 已有划转币种账户
			memberVirtualcoin.setId(memberVirtualcoins.get(0).getId());
			memberVirtualcoin.setCountactive(
					MathHelper.add(memberVirtualcoins.get(0).getCountactive(), tfCount, MathHelper.CAL_PRECISION));
			int i = memberVirtualcoinDao.updateByPrimaryKeySelective(memberVirtualcoin);
			if (i < 1)
				return false;
			bool = true;
		} else {// 没有划转币种账户
			memberVirtualcoin = new MemberVirtualcoin(memberId, virtualcoinId, tfCount, BigDecimal.ZERO, null);
			int i = memberVirtualcoinDao.insertSelective(memberVirtualcoin);
			if (i < 1)
				return false;
			bool = true;
		}
		return bool;
	}

	@Override
	public Virtualcoin getVirtualcoin(Map<String, Object> map) {
		Integer id = Integer.parseInt(map.get("id").toString());
		return this.virtualcoinDao.selectByPrimaryKey(id);
	}

	
	@Override
	public String updateMemberByMemberId(Map<String, Object> map, HttpServletRequest request) {
		String result = "修改失败";
		if(map == null || map.get("memberId") == null){
			return result = "缺少必要参数";
		}
		Member member = this.memberDao.selectByPrimaryKey(Integer.parseInt(map.get("memberId").toString()));
		if(member == null){
			return result = "没有该用户";
		}
		Member mem = new Member();
		mem.setId(member.getId());
		if(map.get("enabled") != null){//是否启用（0：冻结，1：启用）		enabled（非必填）
			mem.setEnabled(Boolean.parseBoolean(map.get("enabled").toString()));
			this.memberDao.updateByPrimaryKeySelective(mem);
			return result = "ok";
		}
		if(map.get("password") != null){//登陆密码		password（非必填）
			mem.setPassword(map.get("password").toString());
			this.memberDao.updateByPrimaryKeySelective(mem);
			recordMemberLog(request, mem.getId(), "修改登录密码");
			return result = "ok";
		}
		if(map.get("tradePassword") != null){//交易密码		tradePassword（非必填）
			mem.setTradepassword(map.get("tradePassword").toString());
			this.memberDao.updateByPrimaryKeySelective(mem);
			recordMemberLog(request, mem.getId(), "修改交易密码");
			return result = "ok";
		}
		if(map.get("name") != null && map.get("idcard") != null){//普通认证
			mem.setName(map.get("name").toString());
			mem.setIdcard(map.get("idcard").toString());
			this.memberDao.updateByPrimaryKeySelective(mem);
			return result = "ok";
		}
		if(map.get("idcardpiccheckId") != null){//高级认证状态
			mem.setIdcardpiccheckId(Integer.parseInt(map.get("idcardpiccheckId").toString()));
			if(map.get("idcardpicchecktype") != null){//高级认证类型
				mem.setIdcardpicchecktype("1".equals(map.get("idcardpicchecktype").toString()) ? true : false);
			}
			if(map.get("idcardpicfront") != null &&
					map.get("idcardpicback") != null &&
					map.get("idcardpiconhand") != null ){//高级认证通过,存图片路径
				mem.setIdcardpicfront(map.get("idcardpicfront").toString());
				mem.setIdcardpicback(map.get("idcardpicback").toString());
				mem.setIdcardpiconhand(map.get("idcardpiconhand").toString());
			}
			this.memberDao.updateByPrimaryKeySelective(mem);
			return result = "ok";
		}
		return result;
	}
	
	
	@Override
	public Boolean frozenMembers(Map<String, Object> map) {
		Boolean bool = false;
		if(map.get("memberIds") != null){
			map.put("memberIds", String.valueOf(map.get("memberIds")).split(","));
		}
		if(map.get("enabled") != null){
			map.put("enabled", "1".equals(map.get("enabled").toString()) ? true : false);
		}
		int i = this.memberDao.frozenMembers(map);
		bool = i > 0 ? true : bool;
		return bool;
	}
	
	
	public Boolean recordMemberLog(HttpServletRequest request,Integer memberId,String operation) {
		Boolean bool = false ;
		try {
			MemberLog memberLog = new MemberLog( memberId, operation, new Date(), SystemHelper.getIpAddr(request));
			this.memberLogDao.insertSelective(memberLog);
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}


}
