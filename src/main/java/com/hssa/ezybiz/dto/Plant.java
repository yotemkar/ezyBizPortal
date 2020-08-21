package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class Plant implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private Integer optlock;
	private String code;
	private String name;
	private Integer enterpriseid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = (id);
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
		this.creator = (creator);
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = (entityUid);
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = (modifier);
	}

	public java.sql.Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.sql.Timestamp updatedate) {
		
		this.updatedate = updatedate;
	}

	public Integer getOptlock() {
		return optlock;
	}

	public void setOptlock(Integer optlock) {
		this.optlock = (optlock);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = (code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = (name);
	}

	public Integer getEnterpriseid() {
		return enterpriseid;
	}

	public void setEnterpriseid(Integer enterpriseid) {
		this.enterpriseid = (enterpriseid);
	}
}
