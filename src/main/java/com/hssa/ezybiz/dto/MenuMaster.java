package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class MenuMaster implements Serializable {

	private static final long serialVersionUID = 2624949473517388392L;

	private Integer menuId;
	private String menuName;
	private Integer menuType;
	private int parentMenuId;
	private String pageUrl;
	private Integer createdBy;
	private java.sql.Timestamp createdTime;
	private Integer updatedBy;
	private java.sql.Timestamp updatedTime;
	private int menuSequence;
	private Integer isDeleted;
	private String entityUid;
	private Integer active;
	private Integer extranet;
	private String type;
	private String description;
	private Integer contentPage;
	private Integer userId;
	private String parentMenuName;
	private Integer companyId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = (menuId);
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = (menuName);
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = (menuType);
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = (parentMenuId);
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = (pageUrl);
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = (createdBy);
	}

	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp createdTime) {
		this.createdTime= createdTime;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = (updatedBy);
	}

	public java.sql.Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(java.sql.Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getExtranet() {
		return extranet;
	}

	public void setExtranet(Integer extranet) {
		this.extranet = extranet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMenuSequence() {
		return menuSequence;
	}

	public void setMenuSequence(int menuSequence) {
		this.menuSequence = menuSequence;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public Integer getContentPage() {
		return contentPage;
	}

	public void setContentPage(Integer contentPage) {
		this.contentPage = contentPage;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
