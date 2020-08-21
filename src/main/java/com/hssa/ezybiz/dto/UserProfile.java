package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserProfile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String	loginId;
	private String	lastLoginTime;
	private Integer contactId;
	private Integer spouseId;
	private Integer userId;
	private Integer addressId;
	private Integer locationId;
	private String givenName;
	private String familyName;
	private Timestamp dateOfBirth;
	private String gender;
	private String position;
	private String userFunction;
	private String maritalStatus;
	private String spouseGivenName;	
	private Timestamp weddingAnniversary;
	private String email;
	private String phone;
	private String mobile;
	private String houseNumber;
	private String floor;
	private String streetSide;
	private String streetName;
	private String city;
	private String postalCode;
	private String state;
	private String country;
	private String imagePath;
	private String role;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public Integer getSpouseId() {
		return spouseId;
	}
	public void setSpouseId(Integer spouseId) {
		this.spouseId = spouseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUserFunction() {
		return userFunction;
	}
	public void setUserFunction(String userFunction) {
		this.userFunction = userFunction;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSpouseGivenName() {
		return spouseGivenName;
	}
	public void setSpouseGivenName(String spouseGivenName) {
		this.spouseGivenName = spouseGivenName;
	}
	public Timestamp getWeddingAnniversary() {
		return weddingAnniversary;
	}
	public void setWeddingAnniversary(Timestamp weddingAnniversary) {
		this.weddingAnniversary = weddingAnniversary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getStreetSide() {
		return streetSide;
	}
	public void setStreetSide(String streetSide) {
		this.streetSide = streetSide;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
