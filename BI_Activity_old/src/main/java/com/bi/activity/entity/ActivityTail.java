package com.bi.activity.entity;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: ActivityTail
* @Description: TODO(奖励详情表)
* @author Administrator
* @date 2018年9月12日上午10:43:38
*
 */
@ApiModel("奖励详情表")
public class ActivityTail {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("获奖用户id")
    private Integer memberId;
    @ApiModelProperty("奖励触发人id")
    private Integer parentId;
    @ApiModelProperty("参与活动id")
    private Integer indexCode;
    @ApiModelProperty("获得奖励id")
    private Integer confCode;
    @ApiModelProperty("奖励币种id")
    private Integer confCoinId;
    @ApiModelProperty("奖励币数量")
    private Integer confCoinCount;
    @ApiModelProperty("获奖（发奖）时间")
    private Date tailTime;
    
    @ApiModelProperty("完成的任务")
    private String confName;

    public ActivityTail() {
        super();
    }



    public ActivityTail(Integer memberId, Integer parentId, Integer indexCode, Integer confCode, Integer confCoinId,
            Integer confCoinCount, Date tailTime) {
        super();
        this.memberId = memberId;
        this.parentId = parentId;
        this.indexCode = indexCode;
        this.confCode = confCode;
        this.confCoinId = confCoinId;
        this.confCoinCount = confCoinCount;
        this.tailTime = tailTime;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getConfCode() {
        return confCode;
    }

    public void setConfCode(Integer confCode) {
        this.confCode = confCode;
    }

    public Integer getConfCoinId() {
        return confCoinId;
    }

    public void setConfCoinId(Integer confCoinId) {
        this.confCoinId = confCoinId;
    }

    public Integer getConfCoinCount() {
        return confCoinCount;
    }

    public void setConfCoinCount(Integer confCoinCount) {
        this.confCoinCount = confCoinCount;
    }

    public Date getTailTime() {
        return tailTime;
    }

    public void setTailTime(Date tailTime) {
        this.tailTime = tailTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(Integer indexCode) {
        this.indexCode = indexCode;
    }

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	@Override
	public String toString() {
		return "ActivityTail [id=" + id + ", memberId=" + memberId + ", parentId=" + parentId + ", indexCode="
				+ indexCode + ", confCode=" + confCode + ", confCoinId=" + confCoinId + ", confCoinCount="
				+ confCoinCount + ", tailTime=" + tailTime + ", confName=" + confName + "]";
	}


    
}