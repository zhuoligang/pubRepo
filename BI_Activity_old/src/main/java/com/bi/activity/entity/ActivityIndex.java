package com.bi.activity.entity;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: ActivityIndex
* @Description: TODO(活动主表)
* @author Administrator
* @date 2018年9月12日上午10:41:03
*
 */
@ApiModel("活动主表")
public class ActivityIndex {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("活动名称")
    private String acName;
    @ApiModelProperty("是否开启：默认 1 开启")
    private Boolean acStatus;
    @ApiModelProperty("奖励币种id")
    private Integer acCoinId;
    @ApiModelProperty("奖池总数")
    private Integer acTotal;
    @ApiModelProperty("奖池剩余数")
    private Integer acResidue;
    @ApiModelProperty("活动开始时间")
    private Date  acStarttime;
    @ApiModelProperty("活动结束时间")
    private Date acEndtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
    }

    public Boolean getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Boolean acStatus) {
        this.acStatus = acStatus;
    }

    public Integer getAcCoinId() {
        return acCoinId;
    }

    public void setAcCoinId(Integer acCoinId) {
        this.acCoinId = acCoinId;
    }

    public Integer getAcTotal() {
        return acTotal;
    }

    public void setAcTotal(Integer acTotal) {
        this.acTotal = acTotal;
    }

    public Integer getAcResidue() {
        return acResidue;
    }

    public void setAcResidue(Integer acResidue) {
        this.acResidue = acResidue;
    }

    public Date getAcEndtime() {
        return acEndtime;
    }

    public void setAcEndtime(Date acEndtime) {
        this.acEndtime = acEndtime;
    }

    public Date getAcStarttime() {
        return acStarttime;
    }

    public void setAcStarttime(Date acStarttime) {
        this.acStarttime = acStarttime;
    }

    @Override
    public String toString() {
        return "ActivityIndex [id=" + id + ", acName=" + acName + ", acStatus=" + acStatus + ", acCoinId=" + acCoinId
                + ", acTotal=" + acTotal + ", acResidue=" + acResidue + ", acStarttime=" + acStarttime + ", acEndtime="
                + acEndtime + "]";
    }
    
}