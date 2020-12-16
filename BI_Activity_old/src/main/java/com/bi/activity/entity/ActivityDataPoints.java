package com.bi.activity.entity;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: ActivityDataPoints
* @Description: TODO(数据埋点表)
* @author Administrator
* @date 2018年9月12日上午10:38:22
*
 */
@ApiModel("数据埋点表")
public class ActivityDataPoints {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("用户id")
    private String memberId;
    @ApiModelProperty("推荐人id")
    private String parentId;
    @ApiModelProperty("用户手机号")
    private String memberTel;
    @ApiModelProperty("注册时间")
    private Date regTime;
    @ApiModelProperty("注册ip")
    private String regIp;
    @ApiModelProperty("参与活动id")
    private Integer indexCode;
    @ApiModelProperty("已到达的普通任务等级")
    private Integer confCode;
    @ApiModelProperty("已到达的特殊（其他）任务等级")
    private Integer specialCode;
    @ApiModelProperty("完成的任务")
    private String confName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public Integer getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(Integer indexCode) {
        this.indexCode = indexCode;
    }

    public Integer getConfCode() {
        return confCode;
    }

    public void setConfCode(Integer confCode) {
        this.confCode = confCode;
    }

    public Integer getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(Integer specialCode) {
        this.specialCode = specialCode;
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	@Override
	public String toString() {
		return "ActivityDataPoints [id=" + id + ", memberId=" + memberId + ", parentId=" + parentId + ", memberTel="
				+ memberTel + ", regTime=" + regTime + ", regIp=" + regIp + ", indexCode=" + indexCode + ", confCode="
				+ confCode + ", specialCode=" + specialCode + ", confName=" + confName + "]";
	}

	
    
    
    
}