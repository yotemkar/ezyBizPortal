/**
 * 
 */
package com.hssa.ezybiz.dto;

/**
 * @author KPIT
 *
 */
public class CustomerUserDto {
	
	private String accessPortal;
	private String name;
	private String loginId;
	private String email;
	private String mobile;
	private Integer userId;
	private Integer contactId;
	private Integer userStatus;	
	private Integer position;
	private String positionValue;
	private Integer customerId;
	
	/**
	 * @return the accessPortal
	 */
	public String getAccessPortal() {
		return accessPortal;
	}
	/**
	 * @param accessPortal the accessPortal to set
	 */
	public void setAccessPortal(String accessPortal) {
		this.accessPortal = accessPortal;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the userStatus
	 */
	public Integer getUserStatus() {
		return userStatus;
	}
	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getPositionValue() {
		return positionValue;
	}
	public void setPositionValue(String positionValue) {
		this.positionValue = positionValue;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
}
