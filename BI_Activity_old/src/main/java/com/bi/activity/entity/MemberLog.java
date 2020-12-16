package com.bi.activity.entity;

import java.util.Date;

public class MemberLog {
    private Integer id;

    private Integer memberId;

    private String operation;

    private Date operationtime;

    private String ipaddress;
    
    

    public MemberLog() {
		super();
	}

	public MemberLog(Integer memberId, String operation, Date operationtime, String ipaddress) {
		super();
		this.memberId = memberId;
		this.operation = operation;
		this.operationtime = operationtime;
		this.ipaddress = ipaddress;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }
}