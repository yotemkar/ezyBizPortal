package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class Customer implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private java.sql.Timestamp createDate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updateDate;
	private int optLock;
	private String accessKey;
	private Integer accessPortal;
	private String code;
	private int headQuarter;
	private String customerName;
	private String singleLocation;
	private Integer customerGroupId;
	private Integer mainContactId;
	private Integer parentHqId;
	private Integer salesAreaId;
	private Integer salesGroupId;
	private Integer salesOfficeId;
	private Integer salesPromoter;
	private Integer salesPromoterId;
	private Integer isDirectPosting;
	private String virtualAccount;
	private int isDeleted;

	private String customerGroupName;
	private String email;
	private String mobileNumber;
	private String addressName;
	private String streetName;
	private String streetSide;
	private String floor;
	private String houseNumber;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String parenthqidCustomerLkdDesc;
	private String salesareaidSalesareaLkdDesc;
	private String salesGroup;
	private String salesOffice;
	private String salesOfficeCode;
	private String salesPromoterName;
	private String salesOrganisation;
	private String distChannel;
	private String salesUnit;
	private String salesUnitCode;
	private String salesRegion;
	private String salesRegionCode;
	private String role;
	private Integer companyId;
	private String companyName;

	private String divisionCode;
	private String divisionName;
	
	//added for search purpose
	private Integer employeeId;
	private String employeeEmail;
	
	private Integer monthId;
	
	private boolean secondarySalesAccess;
	
	private String year;
	
	private String salesOrgCode;
	
	// Added by Mohsin
	private String companyCode;
	private String loginId;
	private Integer delcreder;
	private Integer showSalesData;
	
	private java.sql.Timestamp deactivationDate;
	
	private java.sql.Timestamp activationDate;
	
	private String statusUpdatedBy;
	private int OwnerCount;
	
	
	public int getOwnerCount() {
		return OwnerCount;
	}
	public void setOwnerCount(int ownerCount) {
		OwnerCount = ownerCount;
	}
	public String getStatusUpdatedBy() {
		return statusUpdatedBy;
	}
	public void setStatusUpdatedBy(String statusUpdatedBy) {
		this.statusUpdatedBy = statusUpdatedBy;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public java.sql.Timestamp getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(java.sql.Timestamp activationDate) {
		this.activationDate = activationDate;
	}
	public java.sql.Timestamp getDeactivationDate() {
		return deactivationDate;
	}
	public void setDeactivationDate(java.sql.Timestamp deactivationDate) {
		this.deactivationDate = deactivationDate;
	}
	public Integer getShowSalesData() {
		return showSalesData;
	}
	public void setShowSalesData(Integer showSalesData) {
		this.showSalesData = showSalesData;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public Integer getDelcreder() {
		return delcreder;
	}
	public void setDelcreder(Integer delcreder) {
		this.delcreder = delcreder;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
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
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getOptLock() {
		return optLock;
	}
	public void setOptLock(int optLock) {
		this.optLock = optLock;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public Integer getAccessPortal() {
		return accessPortal;
	}
	public void setAccessPortal(Integer accessPortal) {
		this.accessPortal = accessPortal;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getHeadQuarter() {
		return headQuarter;
	}
	public void setHeadQuarter(int headQuarter) {
		this.headQuarter = headQuarter;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSingleLocation() {
		return singleLocation;
	}
	public void setSingleLocation(String singleLocation) {
		this.singleLocation = singleLocation;
	}	
	public String getVirtualAccount() {
		return virtualAccount;
	}
	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCustomerGroupName() {
		return customerGroupName;
	}
	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public String getParenthqidCustomerLkdDesc() {
		return parenthqidCustomerLkdDesc;
	}
	public void setParenthqidCustomerLkdDesc(String parenthqidCustomerLkdDesc) {
		this.parenthqidCustomerLkdDesc = parenthqidCustomerLkdDesc;
	}
	public String getSalesareaidSalesareaLkdDesc() {
		return salesareaidSalesareaLkdDesc;
	}
	public void setSalesareaidSalesareaLkdDesc(String salesareaidSalesareaLkdDesc) {
		this.salesareaidSalesareaLkdDesc = salesareaidSalesareaLkdDesc;
	}
	public String getSalesGroup() {
		return salesGroup;
	}
	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}
	public String getSalesOffice() {
		return salesOffice;
	}
	public void setSalesOffice(String salesOffice) {
		this.salesOffice = salesOffice;
	}
	public String getSalesPromoterName() {
		return salesPromoterName;
	}
	public void setSalesPromoterName(String salesPromoterName) {
		this.salesPromoterName = salesPromoterName;
	}
	public String getSalesOrganisation() {
		return salesOrganisation;
	}
	public void setSalesOrganisation(String salesOrganisation) {
		this.salesOrganisation = salesOrganisation;
	}
	public String getDistChannel() {
		return distChannel;
	}
	public void setDistChannel(String distChannel) {
		this.distChannel = distChannel;
	}
	public String getSalesUnit() {
		return salesUnit;
	}
	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}
	public String getSalesRegion() {
		return salesRegion;
	}
	public void setSalesRegion(String salesRegion) {
		this.salesRegion = salesRegion;
	}
	public Integer getCustomerGroupId() {
		return customerGroupId;
	}
	public void setCustomerGroupId(Integer customerGroupId) {
		this.customerGroupId = customerGroupId;
	}
	public Integer getMainContactId() {
		return mainContactId;
	}
	public void setMainContactId(Integer mainContactId) {
		this.mainContactId = mainContactId;
	}
	public Integer getParentHqId() {
		return parentHqId;
	}
	public void setParentHqId(Integer parentHqId) {
		this.parentHqId = parentHqId;
	}
	public Integer getSalesAreaId() {
		return salesAreaId;
	}
	public void setSalesAreaId(Integer salesAreaId) {
		this.salesAreaId = salesAreaId;
	}
	public Integer getSalesGroupId() {
		return salesGroupId;
	}
	public void setSalesGroupId(Integer salesGroupId) {
		this.salesGroupId = salesGroupId;
	}
	public Integer getSalesOfficeId() {
		return salesOfficeId;
	}
	public void setSalesOfficeId(Integer salesOfficeId) {
		this.salesOfficeId = salesOfficeId;
	}
	public Integer getSalesPromoter() {
		return salesPromoter;
	}
	public void setSalesPromoter(Integer salesPromoter) {
		this.salesPromoter = salesPromoter;
	}
	public Integer getSalesPromoterId() {
		return salesPromoterId;
	}
	public void setSalesPromoterId(Integer salesPromoterId) {
		this.salesPromoterId = salesPromoterId;
	}
	public Integer getIsDirectPosting() {
		return isDirectPosting;
	}
	public void setIsDirectPosting(Integer isDirectPosting) {
		this.isDirectPosting = isDirectPosting;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public boolean isSecondarySalesAccess() {
		return secondarySalesAccess;
	}
	public void setSecondarySalesAccess(boolean secondarySalesAccess) {
		this.secondarySalesAccess = secondarySalesAccess;
	}
	/**
	 * @return the salesOfficeCode
	 */
	public String getSalesOfficeCode() {
		return salesOfficeCode;
	}
	/**
	 * @param salesOfficeCode the salesOfficeCode to set
	 */
	public void setSalesOfficeCode(String salesOfficeCode) {
		this.salesOfficeCode = salesOfficeCode;
	}
	/**
	 * @return the salesUnitCode
	 */
	public String getSalesUnitCode() {
		return salesUnitCode;
	}
	/**
	 * @param salesUnitCode the salesUnitCode to set
	 */
	public void setSalesUnitCode(String salesUnitCode) {
		this.salesUnitCode = salesUnitCode;
	}
	/**
	 * @return the salesRegionCode
	 */
	public String getSalesRegionCode() {
		return salesRegionCode;
	}
	/**
	 * @param salesRegionCode the salesRegionCode to set
	 */
	public void setSalesRegionCode(String salesRegionCode) {
		this.salesRegionCode = salesRegionCode;
	}
	public Integer getMonthId() {
		return monthId;
	}
	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSalesOrgCode() {
		return salesOrgCode;
	}
	public void setSalesOrgCode(String salesOrgCode) {
		this.salesOrgCode = salesOrgCode;
	}
	
}