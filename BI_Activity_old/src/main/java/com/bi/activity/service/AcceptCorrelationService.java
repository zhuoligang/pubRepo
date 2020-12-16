package com.bi.activity.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bi.activity.dto.MemberVirtualcoinDto;
import com.bi.activity.entity.Virtualcoin;

/**
 * 
* @ClassName: AcceptCorrelation
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhuoligang
* @date 2018年10月9日下午10:04:00
*
 */
public interface AcceptCorrelationService {
	
	Map<String,Object> gainAcceptById(Map<String, Object> map);
	
	List<MemberVirtualcoinDto> gainAcceptByIdPlus(Map<String, Object> map);
	
	String transferred(Map<String, Object> map);
	
	Virtualcoin getVirtualcoin(Map<String, Object> map);

	String updateMemberByMemberId(Map<String, Object> map, HttpServletRequest request);
	
	Boolean frozenMembers(Map<String, Object> map);
	
}
