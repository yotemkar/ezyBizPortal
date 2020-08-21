package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Retailer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonIgnore // Added by Mohsin
	private java.sql.Timestamp createDate;
	@JsonIgnore // Added by Mohsin
	private String creator;
	@JsonIgnore // Added by Mohsin
	private String entityUid;
	@JsonIgnore // Added by Mohsin
	private String modifier;
	@JsonIgnore // Added by Mohsin
	private java.sql.Timestamp updateDate;
	@JsonIgnore // Added by Mohsin
	private Integer optLock;
	@JsonIgnore // Added by Mohsin
	private Integer active;
	private String city;
	private String code;
	private String email;
	private String mobile;
	private String name;

	/**
	 * added for rp_retailer
	 */
	private Integer parentId;
	private Boolean isParent;
	private String shopAddress;
	private String shopContact;
	private String shopName;

	private Integer salesOfficeid;

	// Added by Mohsin for RetailPos
	private List<RetailerDealers> customers;
	private List<Retailer> childRetailers;

	public List<RetailerDealers> getCustomers() {
		return customers;
	}

	public void setCustomers(List<RetailerDealers> customers) {
		this.customers = customers;
	}

	public List<Retailer> getChildRetailers() {
		return childRetailers;
	}

	public void setChildRetailers(List<Retailer> childRetailers) {
		this.childRetailers = childRetailers;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopContact() {
		return shopContact;
	}

	public void setShopContact(String shopContact) {
		this.shopContact = shopContact;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getOptLock() {
		return optLock;
	}

	public void setOptLock(Integer optLock) {
		this.optLock = optLock;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalesOfficeid() {
		return salesOfficeid;
	}

	public void setSalesOfficeid(Integer salesOfficeid) {
		this.salesOfficeid = salesOfficeid;
	}

}
