package com.hssa.ezybiz.dto;

import java.util.Date;

public class EmployeeUpload {

	private int companyId;
	private String employeeCode;
	private String loginId;
	private String firstName;
	private String middleName;
	private String lastName;
	private int gender;
	private Date dateOfBirth;
	private String email;
	private String mobile;
	private int salesOfficeId;
	private int salesUnitId;
	private int roleId;
	private Boolean isWareHouseOperator;
	private String district;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public int getSalesOfficeId() {
		return salesOfficeId;
	}

	public void setSalesOfficeId(int salesOfficeId) {
		this.salesOfficeId = salesOfficeId;
	}

	public int getSalesUnitId() {
		return salesUnitId;
	}

	public void setSalesUnitId(int salesUnitId) {
		this.salesUnitId = salesUnitId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Boolean getIsWareHouseOperator() {
		return isWareHouseOperator;
	}

	public void setIsWareHouseOperator(Boolean isWareHouseOpertor) {
		this.isWareHouseOperator = isWareHouseOpertor;
	}

}
