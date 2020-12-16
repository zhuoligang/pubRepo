package com.lg.datadispose.module.bean.bo;

import java.math.BigDecimal;

/*
 * 30天内成交记录
 */
public class Day30AlongBo {
	//成交币种
	private String coinCode;
	//成交总量
	private BigDecimal sumCount;
	
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	@Override
	public String toString() {
		return "Day30AlongBo [coinCode=" + coinCode + ", sumCount=" + sumCount + "]";
	}
	
}
