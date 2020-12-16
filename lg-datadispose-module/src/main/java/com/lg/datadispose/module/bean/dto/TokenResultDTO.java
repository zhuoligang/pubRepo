package com.lg.datadispose.module.bean.dto;

/**
 * 
* @ClassName: TokenResultDTO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年9月23日下午3:31:56
*
 */
public class TokenResultDTO {

	private String access_token;

	private String token_type;

	private String expires_in;

	private String scope;

	private String jti;

	private String refresh_token;

	private String userId;

	public TokenResultDTO() {
		super();
	}

	public TokenResultDTO(String accessToken, String refreshToken, String userId) {
		this.access_token = accessToken;
		this.refresh_token = refreshToken;
		this.userId = userId;
	}
	
	public TokenResultDTO(String accessToken, String refreshToken, String userId, String expiresIn) {
		this.access_token = accessToken;
		this.refresh_token = refreshToken;
		this.userId = userId;
		this.expires_in = expiresIn;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
