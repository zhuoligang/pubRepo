package com.bi.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
 * ClassName:Member（用户信息实体类）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018	2018年9月4日		下午1:27:36
 * @see
 */
@ApiModel("用户信息")
public class Member {
    /**
     * 用户id主键
     */
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("邮箱校验token")
    private String token;
    @ApiModelProperty("邮箱状态")
    private Integer status;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("登录密码")
    private String password;
    @ApiModelProperty("交易密码")
    private String tradepassword;

    private String face;

    private String openId;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("身份证号")
    private String idcard;
    @ApiModelProperty("身份证正面")
    private String idcardpicfront;
    @ApiModelProperty("身份证反面")
    private String idcardpicback;
    @ApiModelProperty("手持身份证（最清晰一帧）图像")
    private String idcardpiconhand;
    @ApiModelProperty("高级认证状态")
    private Integer idcardpiccheckId;

    private String idcardpiccheckdtcause;
    @ApiModelProperty("高级认证方式（0：老，1：新）")
    private Boolean idcardpicchecktype;
    @ApiModelProperty("注册时间")
    private Date regtime;

    private BigDecimal balanceactive;

    private BigDecimal balancedisable;
    @ApiModelProperty("是否启用")
    private Boolean enabled;

    private String sessionId;
    @ApiModelProperty("推荐人id")
    private Integer parentId;
    @ApiModelProperty("登录状态的记录token")
    private String optCode;
    @ApiModelProperty("登录token记录")
    private String tokenLogin;
    @ApiModelProperty("登录token有效时间")
    private Long tokenTimelimit;
    @ApiModelProperty("web端登录token记录")
    private String webTokenLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTradepassword() {
        return tradepassword;
    }

    public void setTradepassword(String tradepassword) {
        this.tradepassword = tradepassword == null ? null : tradepassword.trim();
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdcardpicfront() {
        return idcardpicfront;
    }

    public void setIdcardpicfront(String idcardpicfront) {
        this.idcardpicfront = idcardpicfront == null ? null : idcardpicfront.trim();
    }

    public String getIdcardpicback() {
        return idcardpicback;
    }

    public void setIdcardpicback(String idcardpicback) {
        this.idcardpicback = idcardpicback == null ? null : idcardpicback.trim();
    }

    public String getIdcardpiconhand() {
        return idcardpiconhand;
    }

    public void setIdcardpiconhand(String idcardpiconhand) {
        this.idcardpiconhand = idcardpiconhand == null ? null : idcardpiconhand.trim();
    }

    public Integer getIdcardpiccheckId() {
        return idcardpiccheckId;
    }

    public void setIdcardpiccheckId(Integer idcardpiccheckId) {
        this.idcardpiccheckId = idcardpiccheckId;
    }

    public String getIdcardpiccheckdtcause() {
        return idcardpiccheckdtcause;
    }

    public void setIdcardpiccheckdtcause(String idcardpiccheckdtcause) {
        this.idcardpiccheckdtcause = idcardpiccheckdtcause == null ? null : idcardpiccheckdtcause.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public BigDecimal getBalanceactive() {
        return balanceactive;
    }

    public void setBalanceactive(BigDecimal balanceactive) {
        this.balanceactive = balanceactive;
    }

    public BigDecimal getBalancedisable() {
        return balancedisable;
    }

    public void setBalancedisable(BigDecimal balancedisable) {
        this.balancedisable = balancedisable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode == null ? null : optCode.trim();
    }

    public String getTokenLogin() {
        return tokenLogin;
    }

    public void setTokenLogin(String tokenLogin) {
        this.tokenLogin = tokenLogin == null ? null : tokenLogin.trim();
    }

    public Long getTokenTimelimit() {
        return tokenTimelimit;
    }

    public void setTokenTimelimit(Long tokenTimelimit) {
        this.tokenTimelimit = tokenTimelimit;
    }

    public String getWebTokenLogin() {
        return webTokenLogin;
    }

    public void setWebTokenLogin(String webTokenLogin) {
        this.webTokenLogin = webTokenLogin == null ? null : webTokenLogin.trim();
    }

	public Boolean getIdcardpicchecktype() {
		return idcardpicchecktype;
	}

	public void setIdcardpicchecktype(Boolean idcardpicchecktype) {
		this.idcardpicchecktype = idcardpicchecktype;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", mobile=" + mobile + ", token=" + token + ", status=" + status + ", email="
				+ email + ", password=" + password + ", tradepassword=" + tradepassword + ", face=" + face + ", openId="
				+ openId + ", name=" + name + ", idcard=" + idcard + ", idcardpicfront=" + idcardpicfront
				+ ", idcardpicback=" + idcardpicback + ", idcardpiconhand=" + idcardpiconhand + ", idcardpiccheckId="
				+ idcardpiccheckId + ", idcardpiccheckdtcause=" + idcardpiccheckdtcause + ", idcardpicchecktype="
				+ idcardpicchecktype + ", regtime=" + regtime + ", balanceactive=" + balanceactive + ", balancedisable="
				+ balancedisable + ", enabled=" + enabled + ", sessionId=" + sessionId + ", parentId=" + parentId
				+ ", optCode=" + optCode + ", tokenLogin=" + tokenLogin + ", tokenTimelimit=" + tokenTimelimit
				+ ", webTokenLogin=" + webTokenLogin + "]";
	}

    
    
}