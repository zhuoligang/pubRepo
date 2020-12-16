package com.lg.web.module.bean.po;

import java.util.Date;

public class SpecialCertificationInfo {
    private String id;

    private String memberId;

    private String mobile;

    private String name;

    private String idcard;

    private String idcardPicFront;

    private String idcardPicBack;

    private String idcardPicOnhand;

    private Date reqTime;

    private Integer status;

    private String content;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}