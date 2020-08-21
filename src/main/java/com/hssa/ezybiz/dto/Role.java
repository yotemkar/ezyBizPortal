/**
 * 
 */
package com.hssa.ezybiz.dto;
 
import java.io.Serializable;
import java.util.Date;
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleName;
	private int createdBy;
	private Date createdOn;
	private int lastUpdatedBy;
	private Date lastUpdatedOn;
	
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedTime() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedTime(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the lastUpdatedBy
	 */
	public int getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	/**
	 * @return the lastUpdatedOn
	 */
	public Date getUpdatedTime() {
		return lastUpdatedOn;
	}
	/**
	 * @param lastUpdatedOn the lastUpdatedOn to set
	 */
	public void setUpdatedTime(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
 
}
