package com.hssa.ezybiz.dto;

/**
 * This dto is used for switch user search user functionality
 * 
 * @author KPIT
 *
 */
public class UserDto {

	private String userId;
	private Integer roleId;
	private String roleName;
	private Integer active;
	private Integer locked;
	private String name;
	private String userName;
	private String emailId;
	private String mobile;
	private String addressName;
	private String houseNumber;
	private String floor;
	private String streetName;
	private String streetSide;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private Integer companyId;
	private String customerCode;
	private String customerName;
	private String salesOrganisation;
	private String credRepGroup;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetSide() {
		return streetSide;
	}

	public void setStreetSide(String streetSide) {
		this.streetSide = streetSide;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", roleId=" + roleId + ", roleName=" + roleName + ", active=" + active
				+ ", locked=" + locked + ", name=" + name + ", userName=" + userName + ", emailId=" + emailId
				+ ", mobile=" + mobile + ", getUserId()=" + getUserId() + ", getRoleId()=" + getRoleId()
				+ ", getRoleName()=" + getRoleName() + ", getActive()=" + getActive() + ", getLocked()=" + getLocked()
				+ ", getName()=" + getName() + ", getUserName()=" + getUserName() + ", getEmailId()=" + getEmailId()
				+ ", getMobile()=" + getMobile() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public String getSalesOrganisation() {
		return salesOrganisation;
	}

	public void setSalesOrganisation(String salesOrganisation) {
		this.salesOrganisation = salesOrganisation;
	}

	public String getCredRepGroup() {
		return credRepGroup;
	}

	public void setCredRepGroup(String credRepGroup) {
		this.credRepGroup = credRepGroup;
	}

}
