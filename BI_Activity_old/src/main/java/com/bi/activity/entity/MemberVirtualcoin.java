package com.bi.activity.entity;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: MemberVirtualcoin
* @Description: TODO(用户资产表)
* @author Administrator
* @date 2018年9月12日上午10:54:29
*
 */
@ApiModel("用户资产表")
public class MemberVirtualcoin {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer memberId;
    @ApiModelProperty("币种id")
    private Integer virtualcoinId;
    @ApiModelProperty("余额")
    private BigDecimal countactive;
    @ApiModelProperty("冻结额")
    private BigDecimal countdisable;
    @ApiModelProperty("钱包地址")
    private String walletaddress;
    
    

    public MemberVirtualcoin() {
        super();
    }

    public MemberVirtualcoin(Integer memberId, Integer virtualcoinId, BigDecimal countactive, BigDecimal countdisable,
            String walletaddress) {
        super();
        this.memberId = memberId;
        this.virtualcoinId = virtualcoinId;
        this.countactive = countactive;
        this.countdisable = countdisable;
        this.walletaddress = walletaddress;
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

    public Integer getVirtualcoinId() {
        return virtualcoinId;
    }

    public void setVirtualcoinId(Integer virtualcoinId) {
        this.virtualcoinId = virtualcoinId;
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
        this.walletaddress = walletaddress == null ? null : walletaddress.trim();
    }
}