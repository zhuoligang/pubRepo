package com.bi.activity.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: Ranking
* @Description: TODO(排名奖励)
* @author Administrator
* @date 2018年9月11日上午10:43:51
*
 */
@ApiModel("排名奖励")
public class Ranking {
    @ApiModelProperty("排名")
	private int id;
    @ApiModelProperty("用户id")
	private String memberId;
    @ApiModelProperty("邀请好友数")
	private int inviteCount;
    @ApiModelProperty("获得奖励总数")
	private int totalAward;
    
    
    
	public Ranking() {
		super();
	}
	public Ranking(int id, String memberId, int inviteCount, int totalAward) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.inviteCount = inviteCount;
		this.totalAward = totalAward;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getInviteCount() {
		return inviteCount;
	}
	public void setInviteCount(int inviteCount) {
		this.inviteCount = inviteCount;
	}
	public int getTotalAward() {
		return totalAward;
	}
	public void setTotalAward(int totalAward) {
		this.totalAward = totalAward;
	}
	@Override
	public String toString() {
		return "Ranking [id=" + id + ", memberId=" + memberId + ", inviteCount=" + inviteCount + ", totalAward="
				+ totalAward + "]";
	}
    
    

}
