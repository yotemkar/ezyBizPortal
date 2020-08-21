package com.hssa.ezybiz.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtRetailerUser implements UserDetails{

	private int userId;
	private String userLoginName;
	private String retailerName;
	private String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private int companyid;
	
	public JwtRetailerUser(
			int id,
			String userLoginName,
			String retailerName,
			String password,
			Collection<? extends GrantedAuthority> authorities,
			boolean enabled,
			int companyid
			) {
		this.userId = id;
		this.userLoginName = retailerName;
		this.retailerName = retailerName;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.companyid = companyid;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getRetailername() {
		return retailerName;
	}
	public void setRetailername(String retailername) {
		this.retailerName = retailername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.retailerName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
