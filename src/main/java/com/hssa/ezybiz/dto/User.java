package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1512890955121146889L;
	private int userId;
	private String loginId;
	private int userDepartment;
	private int franchiseeId;
	private int userStatus;
	private String userEmailId;
	private int createdBy;
	private java.sql.Timestamp createdTime;
	private int updatedBy;
	private java.sql.Timestamp updatedTime;
	private String givenName;
	private String middleName;
	private String familyName;
	private String contactNo;
	private String address;
	private Timestamp lastLoginTime;
	private Boolean enabled;
	private Date lastPasswordResetDate;
	private String gender;
	private Integer mainContactId;
	private String entityUid;
	private int companyId;
	public Timestamp dateOfBirth;
	String password;
	List<RoleMaster> roleList;
	private Integer imageId;
	private Integer employeeLocked;
	private String userName;
	private String userType;
	private String email, mobile;
	private Timestamp deactivationDate;

	private java.sql.Timestamp activationDate;
	private String statusUpdatedBy;

	public String getStatusUpdatedBy() {
		return statusUpdatedBy;
	}

	public void setStatusUpdatedBy(String statusUpdatedBy) {
		this.statusUpdatedBy = statusUpdatedBy;
	}

	public java.sql.Timestamp getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(java.sql.Timestamp activationDate) {
		this.activationDate = activationDate;
	}

	public Timestamp getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(Timestamp deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@SuppressWarnings("unused")
	private String statusLkdDesc;

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatusLkdDesc() {
		String userStatus = null;
		if (getUserStatus() == 2) {
			userStatus = "Active";
		}
		if (getUserStatus() == 3) {
			userStatus = "In-active";
		}
		if (getUserStatus() == 1) {
			userStatus = "New User";
		}
		if (getUserStatus() == 4) {
			userStatus = "Blocked";
		}
		return userStatus;
	}

	public void setStatusLkdDesc(String statusLkdDesc) {
		this.statusLkdDesc = statusLkdDesc;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = (userId);
	}

	public int getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(int userDepartment) {
		this.userDepartment = (userDepartment);
	}

	public int getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(int franchiseeId) {
		this.franchiseeId = (franchiseeId);
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = (userStatus);
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = (userEmailId);
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = (createdBy);
	}

	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = (updatedBy);
	}

	public java.sql.Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(java.sql.Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getMainContactId() {
		return mainContactId;
	}

	public void setMainContactId(Integer mainContactId) {
		this.mainContactId = mainContactId;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleMaster> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleMaster> roleList) {
		this.roleList = roleList;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getEmployeeLocked() {
		return employeeLocked;
	}

	public void setEmployeeLocked(Integer employeeLocked) {
		this.employeeLocked = employeeLocked;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
