package com.bi.activity.dto;

public class AdminLog {
	private String hour;
	private String num;
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "AdminLog [hour=" + hour + ", num=" + num + "]";
	}
	

}
