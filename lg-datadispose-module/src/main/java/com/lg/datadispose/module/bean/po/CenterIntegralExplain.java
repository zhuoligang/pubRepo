package com.lg.datadispose.module.bean.po;

import java.math.BigDecimal;

public class CenterIntegralExplain {
    private Integer id;

    private String scoreExplain;

    private Boolean isincrease;

    private BigDecimal score;
    
    public String getIdStr(){
    	return String.valueOf(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScoreExplain() {
        return scoreExplain;
    }

    public void setScoreExplain(String scoreExplain) {
        this.scoreExplain = scoreExplain == null ? null : scoreExplain.trim();
    }

    public Boolean getIsincrease() {
        return isincrease;
    }

    public void setIsincrease(Boolean isincrease) {
        this.isincrease = isincrease;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}