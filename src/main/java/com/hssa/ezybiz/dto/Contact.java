package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private int optlock;
	private String email;
	private String extension;
	private String fax;
	private String landline;
	private String mobile;
	private String name;
	private String uri;
	private Integer addressid;
	private int isdeleted;

	private String addressidAddressLkdDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id =id;
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
		this.creator =creator;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid =entityUid;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier =modifier;
	}

	public java.sql.Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.sql.Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public int getOptlock() {
		return optlock;
	}

	public void setOptlock(int optlock) {
		this.optlock =optlock;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email =email;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension =extension;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax =fax;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline =landline;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile =mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name =name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri =uri;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid =addressid;
	}

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted =isdeleted;
	}

	public String getAddressidAddressLkdDesc() {
		return addressidAddressLkdDesc;
	}

	public void setAddressidAddressLkdDesc(String addressidAddressLkdDesc) {
		this.addressidAddressLkdDesc =addressidAddressLkdDesc;
	}

	public void copy(Contact contact)
	{
	    this.setId(contact.getId());
	    this.setCreatedate(contact.getCreatedate());
	    this.setCreator(contact.getCreator());
	    this.setEntityUid(contact.getEntityUid());
	    this.setModifier(contact.getModifier());
	    this.setUpdatedate(contact.getUpdatedate());
	    this.setOptlock(contact.getOptlock());
	    this.setEmail(contact.getEmail());
	    this.setExtension(contact.getExtension());
	    this.setFax(contact.getFax());
	    this.setLandline(contact.getLandline());
	    this.setMobile(contact.getMobile());
	    this.setName(contact.getName());
	    this.setUri(contact.getUri());
	    this.setAddressid(contact.getAddressid());
	    this.setIsdeleted(contact.getIsdeleted());
	}
}
