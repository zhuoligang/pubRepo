package com.lg.datadispose.module.bean.po;

import java.math.BigDecimal;
import java.util.Date;

public class CenterIntegralDetail {
	private String id;

	private String userId;

	private String memberId;

	private Boolean isincrease;

	private Integer status;

	private BigDecimal score;

	private Date occurTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public Boolean getIsincrease() {
		return isincrease;
	}

	public void setIsincrease(Boolean isincrease) {
		this.isincrease = isincrease;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
}