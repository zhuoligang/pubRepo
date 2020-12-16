package com.bi.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: Vcoinrecord
* @Description: TODO(币流水记录表)
* @author Administrator
* @date 2018年9月12日上午10:55:32
*
 */
@ApiModel("币流水记录表")
public class Vcoinrecord {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer memberId;
    @ApiModelProperty("币种id")
    private Integer virtualcoinId;
    @ApiModelProperty("类型")
    private Integer typeId;
    @ApiModelProperty("数量")
    private BigDecimal variable;
    @ApiModelProperty("费率")
    private BigDecimal fee;
    @ApiModelProperty("时间")
    private Date recordtime;
    

    public Vcoinrecord() {
        super();
    }

    public Vcoinrecord(Integer memberId, Integer virtualcoinId, Integer typeId, BigDecimal variable, BigDecimal fee,
            Date recordtime) {
        super();
        this.memberId = memberId;
        this.virtualcoinId = virtualcoinId;
        this.typeId = typeId;
        this.variable = variable;
        this.fee = fee;
        this.recordtime = recordtime;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getVariable() {
        return variable;
    }

    public void setVariable(BigDecimal variable) {
        this.variable = variable;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    @Override
    public String toString() {
        return "Vcoinrecord [id=" + id + ", memberId=" + memberId + ", virtualcoinId=" + virtualcoinId + ", typeId="
                + typeId + ", variable=" + variable + ", fee=" + fee + ", recordtime=" + recordtime + "]";
    }
    
    
}