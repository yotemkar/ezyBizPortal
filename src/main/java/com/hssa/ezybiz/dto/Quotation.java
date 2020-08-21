package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Quotation implements Serializable {

	private static final long serialVersionUID = 8707512218924287595L;
	private int QuotationID;
	private String opportunityId;
	private String salesOrg;
	private String division;
	private String salesOff;
	private String salesGroup;
	private String plant;
	private String customer;
	private String opportunityName;
	private String street;
	private String city;
	private String region;
	private String postcode;
	private Date closeDate;
	private Date oppStartDate;
	private Date validFromDate;
	private Date validToDate;
	private Double quoteVolume;
	private String poNumber;
	private String incoterm;
	private String truckType;
	private String altTruckType;
	private Double altTruckRate;
	private String exclMinCartage;
	private String marketSegm;
	private String endUse;
	private Double quotedKm;
	private Date risefallDate;
	private String allAreas;
	private String address;
	private String state;
	private Double projectVolume;
	private String showVolumesonQuote;
	private String validforAcceptance;
	private Date supplyStartDate;
	private Date supplyEndDate;
	private String accountLookup;
	private String account;
	private String status;
	private String salesforceOpportunityId;
	private Date creationDate;
	private String createdBy;
	private String userSalesforceId;
	private String accountManager;
	private Double wetmixrate;
	private Double stabliserrate;
	private String print_quantities;
	private Double map_Reference;
	private List<Material> materials;

	public int getQuotationID() {
		return QuotationID;
	}

	public void setQuotationID(int quotationID) {
		QuotationID = quotationID;
	}

	public String getShowVolumesonQuote() {
		return showVolumesonQuote;
	}

	public String getPrint_quantities() {
		return print_quantities;
	}

	public String getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSalesOff() {
		return salesOff;
	}

	public void setSalesOff(String salesOff) {
		this.salesOff = salesOff;
	}

	public String getSalesGroup() {
		return salesGroup;
	}

	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getOppStartDate() {
		return oppStartDate;
	}

	public void setOppStartDate(Date oppStartDate) {
		this.oppStartDate = oppStartDate;
	}

	public Date getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	public Date getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}

	public Double getQuoteVolume() {
		return quoteVolume;
	}

	public void setQuoteVolume(Double quoteVolume) {
		this.quoteVolume = quoteVolume;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public String getAltTruckType() {
		return altTruckType;
	}

	public void setAltTruckType(String altTruckType) {
		this.altTruckType = altTruckType;
	}

	public Double getAltTruckRate() {
		return altTruckRate;
	}

	public void setAltTruckRate(Double altTruckRate) {
		this.altTruckRate = altTruckRate;
	}

	public String getExclMinCartage() {
		return exclMinCartage;
	}

	public void setExclMinCartage(String exclMinCartage) {
		this.exclMinCartage = exclMinCartage;
	}

	public String getMarketSegm() {
		return marketSegm;
	}

	public void setMarketSegm(String marketSegm) {
		this.marketSegm = marketSegm;
	}

	public String getEndUse() {
		return endUse;
	}

	public void setEndUse(String endUse) {
		this.endUse = endUse;
	}

	public Double getQuotedKm() {
		return quotedKm;
	}

	public void setQuotedKm(Double quotedKm) {
		this.quotedKm = quotedKm;
	}

	public Date getRisefallDate() {
		return risefallDate;
	}

	public void setRisefallDate(Date risefallDate) {
		this.risefallDate = risefallDate;
	}

	public String getAllAreas() {
		return allAreas;
	}

	public void setAllAreas(String allAreas) {
		this.allAreas = allAreas;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getProjectVolume() {
		return projectVolume;
	}

	public void setProjectVolume(Double projectVolume) {
		this.projectVolume = projectVolume;
	}

	public String isShowVolumesonQuote() {
		return showVolumesonQuote;
	}

	public void setShowVolumesonQuote(String showVolumesonQuote) {
		this.showVolumesonQuote = showVolumesonQuote;
	}

	public String getValidforAcceptance() {
		return validforAcceptance;
	}

	public void setValidforAcceptance(String validforAcceptance) {
		this.validforAcceptance = validforAcceptance;
	}

	public Date getSupplyStartDate() {
		return supplyStartDate;
	}

	public void setSupplyStartDate(Date supplyStartDate) {
		this.supplyStartDate = supplyStartDate;
	}

	public Date getSupplyEndDate() {
		return supplyEndDate;
	}

	public void setSupplyEndDate(Date supplyEndDate) {
		this.supplyEndDate = supplyEndDate;
	}

	public String getAccountLookup() {
		return accountLookup;
	}

	public void setAccountLookup(String accountLookup) {
		this.accountLookup = accountLookup;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSalesforceOpportunityId() {
		return salesforceOpportunityId;
	}

	public void setSalesforceOpportunityId(String salesforceOpportunityId) {
		this.salesforceOpportunityId = salesforceOpportunityId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserSalesforceId() {
		return userSalesforceId;
	}

	public void setUserSalesforceId(String userSalesforceId) {
		this.userSalesforceId = userSalesforceId;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public Double getWetmixrate() {
		return wetmixrate;
	}

	public void setWetmixrate(Double wetmixrate) {
		this.wetmixrate = wetmixrate;
	}

	public Double getStabliserrate() {
		return stabliserrate;
	}

	public void setStabliserrate(Double stabliserrate) {
		this.stabliserrate = stabliserrate;
	}

	public String isPrint_quantities() {
		return print_quantities;
	}

	public void setPrint_quantities(String print_quantities) {
		this.print_quantities = print_quantities;
	}

	public Double getMap_Reference() {
		return map_Reference;
	}

	public void setMap_Reference(Double map_Reference) {
		this.map_Reference = map_Reference;
	}

	public List<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

}
