package com.lg.datadispose.module.bean.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lg.datadispose.module.domain.User;

/**
 * 
* @ClassName: SecurityUserDTO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年9月20日下午4:47:59
*
 */
public class SecurityUserDTO implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String username;

	private String password;

	private Integer disable;

	private Collection<? extends GrantedAuthority> authorities;

	public SecurityUserDTO() {
	}

	public SecurityUserDTO(User user) {
		this.id = user.getId();
		this.username = user.getPhone();
		this.password = user.getPassword();
		this.disable = user.getDisable();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getDisable() == 0 ? true : false;
	}

	/**
	 * 重写方法用于sessionRegistry
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return this.username;
	}

	/**
	 * 重写方法用于sessionRegistry
	 * 
	 * @return
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs == null) {
			return false;
		}
		return this.toString().equals(rhs.toString());
	}

	/**
	 * 重写方法用于sessionRegistry
	 * 
	 * @return
	 */
	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

}
