package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class CustomerDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String customerCode;
	private String customerName;//customer name
	private String name2;
	private String street;
	private String city1; //city
	private String city2;
	private String district;
	private String postalCode;
	private String region;
	private String mailId;
	private String stcdTax1;
	private String stcdTax2;
	private String salesDiscrict;
	private String salesOffice;
	private String salesGroup;
	private String taxKD;// Tax classification for customer
	private String companyCode;
	private String salesOrg;
	private String distChannel;
	private String division;
	private String blockStatus;
	private String returnMsg;
	private String regionSO;
	private String mobile;
	private String sapAccountGroup;
	private String salesUnit;



	public String getSalesUnit() {
	return salesUnit;
	}
	public void setSalesUnit(String salesUnit) {
	this.salesUnit = salesUnit;
	}
	
	//added
	private String companyId;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
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
	public void setCustomerName(String name1) {
		this.customerName = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getStcdTax1() {
		return stcdTax1;
	}
	public void setStcdTax1(String stcdTax1) {
		this.stcdTax1 = stcdTax1;
	}
	public String getStcdTax2() {
		return stcdTax2;
	}
	public void setStcdTax2(String stcdTax2) {
		this.stcdTax2 = stcdTax2;
	}
	public String getSalesDiscrict() {
		return salesDiscrict;
	}
	public void setSalesDiscrict(String salesDiscrict) {
		this.salesDiscrict = salesDiscrict;
	}
	public String getSalesOffice() {
		return salesOffice;
	}
	public void setSalesOffice(String salesOffice) {
		this.salesOffice = salesOffice;
	}
	public String getSalesGroup() {
		return salesGroup;
	}
	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}
	public String getTaxKD() {
		return taxKD;
	}
	public void setTaxKD(String taxKD) {
		this.taxKD = taxKD;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getDistChannel() {
		return distChannel;
	}
	public void setDistChannel(String distChannel) {
		this.distChannel = distChannel;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getRegionSO() {
		return regionSO;
	}
	public void setRegionSO(String regionSO) {
		this.regionSO = regionSO;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSapAccountGroup() {
		return sapAccountGroup;
	}
	public void setSapAccountGroup(String sapAccountGroup) {
		this.sapAccountGroup = sapAccountGroup;
	}
	@Override
	public String toString() {
		return "CustomerDetails [customerCode=" + customerCode + ", customerName=" + customerName + ", name2=" + name2
				+ ", street=" + street + ", city1=" + city1 + ", city2=" + city2 + ", district=" + district
				+ ", postalCode=" + postalCode + ", region=" + region + ", mailId=" + mailId + ", stcdTax1=" + stcdTax1
				+ ", stcdTax2=" + stcdTax2 + ", salesDiscrict=" + salesDiscrict + ", salesOffice=" + salesOffice
				+ ", salesGroup=" + salesGroup + ", taxKD=" + taxKD + ", companyCode=" + companyCode + ", salesOrg="
				+ salesOrg + ", distChannel=" + distChannel + ", division=" + division + ", blockStatus=" + blockStatus
				+ ", returnMsg=" + returnMsg + ", regionSO=" + regionSO + ", mobile=" + mobile + ", sapAccountGroup="
				+ sapAccountGroup + ", companyId=" + companyId + "]";
	}
	
}
