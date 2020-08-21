package com.hssa.ezybiz.dto;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Material implements Serializable 
{
	private static final long serialVersionUID = 1L;
	@JsonProperty("materials_ID")
	private int id;
	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private int optlock;
	private int active;
	private String articleno;
	@JsonProperty("materialDescription")
	private String description;
	@JsonProperty("materialsGroup")
	private String materialgroup;
	private String uom;
	private int enterpriseid;
	private String enterpriseidEnterpriseLkdDesc;
	private String material;
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getOptlock() {
		return optlock;
	}
	public void setOptlock(int optlock) {
		this.optlock = optlock;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMaterialgroup() {
		return materialgroup;
	}
	public void setMaterialgroup(String materialgroup) {
		this.materialgroup = materialgroup;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public int getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(int enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public String getEnterpriseidEnterpriseLkdDesc() {
		return enterpriseidEnterpriseLkdDesc;
	}
	public void setEnterpriseidEnterpriseLkdDesc(String enterpriseidEnterpriseLkdDesc) {
		this.enterpriseidEnterpriseLkdDesc = enterpriseidEnterpriseLkdDesc;
	}
 
}
