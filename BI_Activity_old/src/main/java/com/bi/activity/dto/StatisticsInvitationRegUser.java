package com.bi.activity.dto;

public class StatisticsInvitationRegUser {
	private Integer isInvitition;
	private Integer notInvitition;
	public Integer getIsInvitition() {
		return isInvitition;
	}
	public void setIsInvitition(Integer isInvitition) {
		this.isInvitition = isInvitition;
	}
	public Integer getNotInvitition() {
		return notInvitition;
	}
	public void setNotInvitition(Integer notInvitition) {
		this.notInvitition = notInvitition;
	}
	@Override
	public String toString() {
		return "StatisticsInvitationRegUser [isInvitition=" + isInvitition + ", notInvitition=" + notInvitition + "]";
	}
	

}
