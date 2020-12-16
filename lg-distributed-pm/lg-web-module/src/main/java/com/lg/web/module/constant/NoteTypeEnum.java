package com.lg.web.module.constant;

public enum NoteTypeEnum {
	DEFAULT("DEFAULT","默认"),
	FINDPASSWORD("FINDPASSWORD","找回登录密码"),
	UPDATEPASSWORD("UPDATEPASSWORD","重置登录密码"),
	UPDATEUSERTRADE("UPDATEUSERTRADE","设置/重置资金安全密码"),
	CERTIFICATION("CERTIFICATION","普通实名认证"),
	PUTBANKCARD("PUTBANKCARD","添加银行卡"),
	ADDADDERSS("ADDADDERSS","添加提币地址"),
	/**
	 * 以下为特殊业务
	 */
	LOGIN("LOGIN","登录"),
	REGISTER("REGISTER","注册"),
	SWITCHGOOGLESAFE("SWITCHGOOGLESAFE","启用谷歌验证码");
	
	private String key;
	
	private String type;
	
	private NoteTypeEnum(String key,String type) {
		this.key = key;
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
