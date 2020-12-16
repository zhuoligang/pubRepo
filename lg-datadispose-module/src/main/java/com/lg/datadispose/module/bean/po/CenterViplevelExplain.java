package com.lg.datadispose.module.bean.po;

import java.math.BigDecimal;

public class CenterViplevelExplain {
    private Integer id;

    private String vip;

    private BigDecimal btFloor;

    private BigDecimal tradingFloor;

    private BigDecimal makerRate;

    private BigDecimal takerRate;

    private BigDecimal takerRateBt;

    private BigDecimal makerRateBt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip == null ? null : vip.trim();
    }

    public BigDecimal getBtFloor() {
        return btFloor;
    }

    public void setBtFloor(BigDecimal btFloor) {
        this.btFloor = btFloor;
    }

    public BigDecimal getTradingFloor() {
        return tradingFloor;
    }

    public void setTradingFloor(BigDecimal tradingFloor) {
        this.tradingFloor = tradingFloor;
    }

    public BigDecimal getMakerRate() {
        return makerRate;
    }

    public void setMakerRate(BigDecimal makerRate) {
        this.makerRate = makerRate;
    }

    public BigDecimal getTakerRate() {
        return takerRate;
    }

    public void setTakerRate(BigDecimal takerRate) {
        this.takerRate = takerRate;
    }

    public BigDecimal getTakerRateBt() {
        return takerRateBt;
    }

    public void setTakerRateBt(BigDecimal takerRateBt) {
        this.takerRateBt = takerRateBt;
    }

    public BigDecimal getMakerRateBt() {
        return makerRateBt;
    }

    public void setMakerRateBt(BigDecimal makerRateBt) {
        this.makerRateBt = makerRateBt;
    }
}