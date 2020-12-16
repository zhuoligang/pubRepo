package com.bi.activity.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("用户资产表--推送承兑平台用")
public class MemberVirtualcoinDto {
	
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer memberId;
    @ApiModelProperty("币种id")
    private Integer virtualcoinId;
    @ApiModelProperty("中文名")
    private String cname;
    @ApiModelProperty("国际码")
    private String eunit;
    @ApiModelProperty("余额")
    private BigDecimal countactive;
    @ApiModelProperty("冻结额")
    private BigDecimal countdisable;
    @ApiModelProperty("钱包地址")
    private String walletaddress;
    
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
	public Integer getVirtualcoinId() {
		return virtualcoinId;
	}
	public void setVirtualcoinId(Integer virtualcoinId) {
		this.virtualcoinId = virtualcoinId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEunit() {
		return eunit;
	}
	public void setEunit(String eunit) {
		this.eunit = eunit;
	}
	public BigDecimal getCountactive() {
		return countactive;
	}
	public void setCountactive(BigDecimal countactive) {
		this.countactive = countactive;
	}
	public BigDecimal getCountdisable() {
		return countdisable;
	}
	public void setCountdisable(BigDecimal countdisable) {
		this.countdisable = countdisable;
	}
	public String getWalletaddress() {
		return walletaddress;
	}
	public void setWalletaddress(String walletaddress) {
		this.walletaddress = walletaddress;
	}
    
    

}
