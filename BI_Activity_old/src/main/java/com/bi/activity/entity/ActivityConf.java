package com.bi.activity.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: ActivityConf
* @Description: TODO(奖励配置表)
* @author Administrator
* @date 2018年9月12日上午10:34:54
*
 */
@ApiModel("奖励配置表")
public class ActivityConf {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("奖励名称")
    private String confName;
    @ApiModelProperty("奖励币种id")
    private Integer confCoinId;
    @ApiModelProperty("奖励币数量")
    private Integer confCoinCount;
    @ApiModelProperty("推荐人id")
    private Integer confParentId;
    @ApiModelProperty("是否开启，默认：1 开启")
    private Boolean confStatus;
    @ApiModelProperty("已发放数量 （暂时没用）")
    private Integer confGrantCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
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

    public Integer getConfParentId() {
        return confParentId;
    }

    public void setConfParentId(Integer confParentId) {
        this.confParentId = confParentId;
    }

    public Boolean getConfStatus() {
        return confStatus;
    }

    public void setConfStatus(Boolean confStatus) {
        this.confStatus = confStatus;
    }

    public Integer getConfGrantCount() {
        return confGrantCount;
    }

    public void setConfGrantCount(Integer confGrantCount) {
        this.confGrantCount = confGrantCount;
    }
}