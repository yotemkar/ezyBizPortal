package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private int employeetypeid;
	private String employeecode;
	private String externalid;
	private String name;
	private int id;
	private String cmsdesignation;
	private int isdeleted;
	private String givenName;
	private String middleName;
	private String familyName;
	private String loginId;
	private String userId;
	private String mobileNumber;
	private String emailId;
	private String companyName;

	private int salesofficeid;
	private String salesOfficeCode;
	private String salesOfficeName;
	private int salesUnitId;
	private String salesUnitCode;
	private String salesUnitName;
	private String dateOfBirth;

	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private int locked;
	private String addressName;
	private String streetName;
	private String streetSide;
	private String floor;
	private String houseNumber;
	private String city;
	private String state;
	private String country;
	private String postalCode;

	private String roleName;
	
	private Boolean isWareHouseOperator;
	
	private List<Salesoffice> mappedSalesOffice;
	private List<CrdRepGrp> mappedCrdRepGrp;

	
	private int activeStatus;
	
	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getEmployeetypeid() {
		return employeetypeid;
	}

	public void setEmployeetypeid(int employeetypeid) {
		this.employeetypeid =employeetypeid;
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode =employeecode;
	}

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid =externalid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id =id;
	}

	public int getSalesofficeid() {
		return salesofficeid;
	}

	public void setSalesofficeid(int salesofficeid) {
		this.salesofficeid =salesofficeid;
	}

	public String getCmsdesignation() {
		return cmsdesignation;
	}

	public void setCmsdesignation(String cmsdesignation) {
		this.cmsdesignation =cmsdesignation;
	}

	public int getIsDeleted() {
		return isdeleted;
	}

	public void setIsDeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSalesOfficeCode() {
		return salesOfficeCode;
	}

	public void setSalesOfficeCode(String salesOfficeCode) {
		this.salesOfficeCode = salesOfficeCode;
	}

	public String getSalesOfficeName() {
		return salesOfficeName;
	}

	public void setSalesOfficeName(String salesOfficeName) {
		this.salesOfficeName = salesOfficeName;
	}

	public int getSalesUnitId() {
		return salesUnitId;
	}

	public void setSalesUnitId(int salesUnitId) {
		this.salesUnitId = salesUnitId;
	}

	public String getSalesUnitCode() {
		return salesUnitCode;
	}

	public void setSalesUnitCode(String salesUnitCode) {
		this.salesUnitCode = salesUnitCode;
	}

	public String getSalesUnitName() {
		return salesUnitName;
	}

	public void setSalesUnitName(String salesUnitName) {
		this.salesUnitName = salesUnitName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public java.sql.Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.sql.Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public java.sql.Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.sql.Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Salesoffice> getMappedSalesOffice() {
		return mappedSalesOffice;
	}

	public void setMappedSalesOffice(List<Salesoffice> mappedSalesOffice) {
		this.mappedSalesOffice = mappedSalesOffice;
	}

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
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

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
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

	public Boolean getIsWareHouseOperator() {
		return isWareHouseOperator;
	}

	public void setIsWareHouseOperator(Boolean isWareHouseOperator) {
		this.isWareHouseOperator = isWareHouseOperator;
	}

	public List<CrdRepGrp> getMappedCrdRepGrp() {
		return mappedCrdRepGrp;
	}

	public void setMappedCrdRepGrp(List<CrdRepGrp> mappedCrdRepGrp) {
		this.mappedCrdRepGrp = mappedCrdRepGrp;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	


}
