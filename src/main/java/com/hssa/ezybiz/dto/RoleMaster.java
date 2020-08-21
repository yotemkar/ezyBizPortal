package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.List;

public class RoleMaster  implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int roleId;
	private String roleName;
	private String description;
	private String roleTypes;
	private Integer roleTypeId;
	private int createdBy;
	private java.sql.Timestamp createdTime;
	private int updatedBy;
	private java.sql.Timestamp updatedTime;
	private int isDeleted;
	private String entityUid;
	private int userId;
	List<MenuMaster> mappedMenus;
	private Integer companyId;
 
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId)  {
		this.roleId=(roleId);
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName)  {
		this.roleName=(roleName);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description)  {
		this.description=(description);
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy)  {
		this.createdBy=(createdBy);
	}
	
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp createdTime)  {
		this.createdTime = createdTime;
	}
	
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy)  {
		this.updatedBy=(updatedBy);
	}
	
	public java.sql.Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp updatedTime)  {
		this.updatedTime = updatedTime;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

 	public List<MenuMaster> getMappedMenus() {
		return mappedMenus;
	}
 	
	public void setMappedMenus(List<MenuMaster> mappedMenus) {
		this.mappedMenus = mappedMenus;
	}
	public String getRoleTypes() {
		return roleTypes;
	}
	public void setRoleTypes(String roleTypes) {
		this.roleTypes = roleTypes;
	}
	public Integer getRoleTypeId() {
		return roleTypeId;
	}
	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
