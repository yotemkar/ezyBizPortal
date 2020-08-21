package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class Contentdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private java.sql.Timestamp createDate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updateDate;
	private Integer optLock;
	private String header;
	private String contentText;
	private int isDeleted;
	private String url;
	
	

	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
}
