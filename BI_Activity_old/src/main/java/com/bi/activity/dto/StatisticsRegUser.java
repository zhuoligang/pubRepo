package com.bi.activity.dto;

import java.util.Date;

public class StatisticsRegUser {
	private String memberId;
	private String name;
	private String memberTel;
	private Date regTime;
	private String regIp;
	private String parentId;
	private String idcard;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "StatisticsRegUser [memberId=" + memberId + ", name=" + name + ", memberTel=" + memberTel + ", regTime="
				+ regTime + ", regIp=" + regIp + ", parentId=" + parentId + ", idcard=" + idcard + "]";
	}
	
	
	
	
	
	
}
