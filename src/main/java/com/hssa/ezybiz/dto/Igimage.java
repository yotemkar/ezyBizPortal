package com.hssa.ezybiz.dto;

import java.io.Serializable;


public class Igimage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private int optlock;
	private String description;
	private String url;
	private String imagePath;

	private byte[] imageFile;
	
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
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public java.sql.Timestamp getUpdatedate() {
		return updatedate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	
	
}
