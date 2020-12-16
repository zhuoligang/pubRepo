package com.lg.datadispose.module.bean.po;

import java.util.Date;

public class CenterUserMain {
    private String id;

    private String memberId;

    private String parentId;

    private String mobile;

    private String email;

    private String tradePassword;

    private String name;

    private String idcard;

    private String idcardPicFront;

    private String idcardPicBack;

    private String idcardPicOnhand;

    private Integer idcardPicCheckId;

    private Integer idcardPicCheckType;

    private Date regTime;
    
    private Integer regType;

    private Integer userEnabled;

    private Integer tradePasswordType;

    private Integer changePasswordType;
    
    private Integer feeDeductionType;
    
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword == null ? null : tradePassword.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdcardPicFront() {
        return idcardPicFront;
    }

    public void setIdcardPicFront(String idcardPicFront) {
        this.idcardPicFront = idcardPicFront == null ? null : idcardPicFront.trim();
    }

    public String getIdcardPicBack() {
        return idcardPicBack;
    }

    public void setIdcardPicBack(String idcardPicBack) {
        this.idcardPicBack = idcardPicBack == null ? null : idcardPicBack.trim();
    }

    public String getIdcardPicOnhand() {
        return idcardPicOnhand;
    }

    public void setIdcardPicOnhand(String idcardPicOnhand) {
        this.idcardPicOnhand = idcardPicOnhand == null ? null : idcardPicOnhand.trim();
    }

    public Integer getIdcardPicCheckId() {
        return idcardPicCheckId;
    }

    public void setIdcardPicCheckId(Integer idcardPicCheckId) {
        this.idcardPicCheckId = idcardPicCheckId;
    }

    public Integer getIdcardPicCheckType() {
        return idcardPicCheckType;
    }

    public void setIdcardPicCheckType(Integer idcardPicCheckType) {
        this.idcardPicCheckType = idcardPicCheckType;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Integer userEnabled) {
        this.userEnabled = userEnabled;
    }

    public Integer getTradePasswordType() {
        return tradePasswordType;
    }

    public void setTradePasswordType(Integer tradePasswordType) {
        this.tradePasswordType = tradePasswordType;
    }

    public Integer getChangePasswordType() {
        return changePasswordType;
    }

    public void setChangePasswordType(Integer changePasswordType) {
        this.changePasswordType = changePasswordType;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getFeeDeductionType() {
		return feeDeductionType;
	}

	public void setFeeDeductionType(Integer feeDeductionType) {
		this.feeDeductionType = feeDeductionType;
	}

	public Integer getReqType() {
		return regType;
	}

	public void setRegType(Integer regType) {
		this.regType = regType;
	}

    
}