package com.hssa.ezybiz.dto;
import java.io.Serializable;

public class Customeruser implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	private int accesskey;
	private String jobtxt;
	private int married;
	private String orgtxt;
	private String orgunit;
	private String position;
	private String userfunction;
	private java.sql.Timestamp weddinganniversary;
	private int id;
	private int customerid;
	private Integer spouseId;
	private int isDeleted;
	private int isAdditionalUser;
	private int isPending;
 
	private String customeridCustomerLkdDesc;
	private String spouseidRelationLkdDesc;
 
	public int getAccesskey()
	{
		return accesskey;
	}
	public void setAccesskey(int accesskey) 
	{
		this.accesskey=(accesskey);
	}
	
	public String getJobtxt()
	{
		return jobtxt;
	}
	public void setJobtxt(String jobtxt) 
	{
		this.jobtxt=(jobtxt);
	}
	
	public int getMarried()
	{
		return married;
	}
	public void setMarried(int married) 
	{
		this.married=(married);
	}
	
	public String getOrgtxt()
	{
		return orgtxt;
	}
	public void setOrgtxt(String orgtxt) 
	{
		this.orgtxt=(orgtxt);
	}
	
	public String getOrgunit()
	{
		return orgunit;
	}
	public void setOrgunit(String orgunit) 
	{
		this.orgunit=(orgunit);
	}
	
	public String getPosition()
	{
		return position;
	}
	public void setPosition(String position) 
	{
		this.position=(position);
	}
	
	public String getUserfunction()
	{
		return userfunction;
	}
	public void setUserfunction(String userfunction) 
	{
		this.userfunction=(userfunction);
	}
	
	public java.sql.Timestamp getWeddinganniversary()
	{
		return weddinganniversary;
	}
	public void setWeddinganniversary(java.sql.Timestamp weddinganniversary) 
	{
		this.weddinganniversary = weddinganniversary;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id=(id);
	}
	
	public int getCustomerid()
	{
		return customerid;
	}
		
	public Integer getSpouseId() {
		return spouseId;
	}
	public void setSpouseId(Integer spouseId) {
		this.spouseId = spouseId;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomeridCustomerLkdDesc()
	{
		return customeridCustomerLkdDesc;
	}
	public void setCustomeridCustomerLkdDesc(String customeridCustomerLkdDesc) 
	{
		this.customeridCustomerLkdDesc=(customeridCustomerLkdDesc);
	}
	 public String getSpouseidRelationLkdDesc()
	{
		return spouseidRelationLkdDesc;
	}
	public void setSpouseidRelationLkdDesc(String spouseidRelationLkdDesc) 
	{
		this.spouseidRelationLkdDesc=(spouseidRelationLkdDesc);
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public void copy(Customeruser customeruser)
	{
	    this.setAccesskey(customeruser.getAccesskey());
	    this.setJobtxt(customeruser.getJobtxt());
	    this.setMarried(customeruser.getMarried());
	    this.setOrgtxt(customeruser.getOrgtxt());
	    this.setOrgunit(customeruser.getOrgunit());
	    this.setPosition(customeruser.getPosition());
	    this.setUserfunction(customeruser.getUserfunction());
	    this.setWeddinganniversary(customeruser.getWeddinganniversary());
	    this.setId(customeruser.getId());
	    this.setCustomerid(customeruser.getCustomerid());
	    this.setSpouseId(customeruser.getSpouseId());
	    this.setIsDeleted(customeruser.getIsDeleted());
	}
	public int getIsAdditionalUser() {
		return isAdditionalUser;
	}
	public void setIsAdditionalUser(int isAdditionalUser) {
		this.isAdditionalUser = isAdditionalUser;
	}
	public int getIsPending() {
		return isPending;
	}
	public void setIsPending(int isPending) {
		this.isPending = isPending;
	}
	
}
