package com.lg.datadispose.module.bean.po;

import java.math.BigDecimal;
import java.util.Date;

public class AssetAccount {
    private String id;

    private Integer version;

    private String userId;

    private BigDecimal hotMoney;

    private BigDecimal coldMoney;

    private String memberId;

    private String oldMemberId;

    private Integer status;

    private String publickey;

    private String privatekey;

    private String coinName;

    private String coinCode;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getHotMoney() {
        return hotMoney;
    }

    public void setHotMoney(BigDecimal hotMoney) {
        this.hotMoney = hotMoney;
    }

    public BigDecimal getColdMoney() {
        return coldMoney;
    }

    public void setColdMoney(BigDecimal coldMoney) {
        this.coldMoney = coldMoney;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getOldMemberId() {
        return oldMemberId;
    }

    public void setOldMemberId(String oldMemberId) {
        this.oldMemberId = oldMemberId == null ? null : oldMemberId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey == null ? null : publickey.trim();
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey == null ? null : privatekey.trim();
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName == null ? null : coinName.trim();
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode == null ? null : coinCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}