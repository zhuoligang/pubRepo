/**
 * ActivityStatistics.java
 * com.bi.activity.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月5日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * ClassName:ActivityStatistics（活动数据统计）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月5日		上午11:22:54
 * @see 	 
 */
@ApiModel("活动数据统计---")
public class ActivityStatistics {
    
    private int id;
    
    @ApiModelProperty("所有用户登录数")
    private int allLoginTimes;
    
    @ApiModelProperty("非邀请注册数")
    private int notInviteNum;
    
    @ApiModelProperty("邀请注册数")
    private int inviteNum;
    
    @ApiModelProperty("高级认证数")
    private int seniorCertificationNum;
    
    @ApiModelProperty("充值数")
    private int rechargeNum;
    
    @ApiModelProperty("交易数")
    private int dealNum;

    public int getNotInviteNum() {
        return notInviteNum;
    }

    public void setNotInviteNum(int notInviteNum) {
        this.notInviteNum = notInviteNum;
    }

    public int getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(int inviteNum) {
        this.inviteNum = inviteNum;
    }

    public int getSeniorCertificationNum() {
        return seniorCertificationNum;
    }

    public void setSeniorCertificationNum(int seniorCertificationNum) {
        this.seniorCertificationNum = seniorCertificationNum;
    }

    public int getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(int rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public int getDealNum() {
        return dealNum;
    }

    public void setDealNum(int dealNum) {
        this.dealNum = dealNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllLoginTimes() {
        return allLoginTimes;
    }

    public void setAllLoginTimes(int allLoginTimes) {
        this.allLoginTimes = allLoginTimes;
    }

    @Override
    public String toString() {
        return "ActivityStatistics [id=" + id + ", allLoginTimes=" + allLoginTimes + ", notInviteNum=" + notInviteNum
                + ", inviteNum=" + inviteNum + ", seniorCertificationNum=" + seniorCertificationNum + ", rechargeNum="
                + rechargeNum + ", dealNum=" + dealNum + "]";
    }

    

    

}

