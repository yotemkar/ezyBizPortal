package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 
 * @author KPIT
 * 
 * 
 * This class keeps track of user log in.
 * Lock the user after 5 wrong password attempts.
 * Show the last log in date.
 */
public class UserLoginDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private Integer userId;
	private Timestamp lastLogInSuccess;
	private Timestamp lastLogInFailure;
	private Integer wrongPasswordAttempts;
	private Boolean active;
	private Boolean locked;
	private String source;
	private Boolean passwordChanged;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getLastLogInSuccess() {
		return lastLogInSuccess;
	}
	public void setLastLogInSuccess(Timestamp lastLogInSuccess) {
		this.lastLogInSuccess = lastLogInSuccess;
	}
	public Timestamp getLastLogInFailure() {
		return lastLogInFailure;
	}
	public void setLastLogInFailure(Timestamp lastLogInFailure) {
		this.lastLogInFailure = lastLogInFailure;
	}
	public Integer getWrongPasswordAttempts() {
		return wrongPasswordAttempts;
	}
	public void setWrongPasswordAttempts(Integer wrongPasswordAttempts) {
		this.wrongPasswordAttempts = wrongPasswordAttempts;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public Boolean getPasswordChanged() {
		return passwordChanged;
	}
	public void setPasswordChanged(Boolean passwordChanged) {
		this.passwordChanged = passwordChanged;
	}
	@Override
	public String toString() {
		return "UserLoginDetails [id=" + id + ", userId=" + userId + ", lastLogInSuccess=" + lastLogInSuccess
				+ ", lastLogInFailure=" + lastLogInFailure + ", wrongPasswordAttempts=" + wrongPasswordAttempts
				+ ", active=" + active + ", locked=" + locked + ", source=" + source + "]";
	}
	
	
	
	
}
