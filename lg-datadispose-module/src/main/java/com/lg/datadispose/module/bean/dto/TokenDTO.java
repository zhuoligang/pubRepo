package com.lg.datadispose.module.bean.dto;

import java.util.List;

/**
 * 
* @ClassName: TokenDTO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年9月23日下午3:34:09
*
 */
public class TokenDTO {

    private List<String> aud;
    
    private String user_name;
    
    private List<String> scope;
    
    private String ati;
    
    private Long exp;
    
    private List<?> authorities;
    
    private String jti;
    
    private String client_id;
    
    public List<?> getAud() {
        return aud;
    }

    public void setAud(List<String> aud) {
        this.aud = aud;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<?> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public String getAti() {
        return ati;
    }

    public void setAti(String ati) {
        this.ati = ati;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public List<?> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<?> authorities) {
        this.authorities = authorities;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

}
