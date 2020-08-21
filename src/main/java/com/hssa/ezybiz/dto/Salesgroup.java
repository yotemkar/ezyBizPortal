package com.hssa.ezybiz.dto;
import java.io.Serializable;
 
public class Salesgroup implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	private int id;
	private java.sql.Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updatedate;
	private int optlock;
	private String code;
	private String name;
	private int salesofficeid;
	private String loginid;
	private String password;
	private int isdeleted;
	private String returnMsg;
 
	private String salesofficeidSalesofficeLkdDesc;

	public Salesgroup()
	{
        	init();		
	}
 
	
	public final void init()
    {
        this.id = 0;
        this.createdate = new java.sql.Timestamp(0);
        this.creator = "";
        this.entityUid = "";
        this.modifier = "";
        this.updatedate = new java.sql.Timestamp(0);
        this.optlock = 0;
        this.code = "";
        this.name = "";
        this.salesofficeid = 0;
        this.loginid = "";
        this.password = "";
        this.isdeleted = 0;
    }
	
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
 
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id=id;
	}
	
	public java.sql.Timestamp getCreatedate()
	{
		return createdate;
	}
	public void setCreatedate(java.sql.Timestamp createdate) 
	{
		this.createdate = createdate;
	}
	
	public String getCreator()
	{
		return creator;
	}
	public void setCreator(String creator) 
	{
		this.creator=creator;
	}
	
	public String getEntityUid()
	{
		return entityUid;
	}
	public void setEntityUid(String entityUid) 
	{
		this.entityUid=entityUid;
	}
	
	public String getModifier()
	{
		return modifier;
	}
	public void setModifier(String modifier) 
	{
		this.modifier=modifier;
	}
	
	public java.sql.Timestamp getUpdatedate()
	{
		return updatedate;
	}
	public void setUpdatedate(java.sql.Timestamp updatedate) 
	{
		this.updatedate = updatedate;
	}
	
	public int getOptlock()
	{
		return optlock;
	}
	public void setOptlock(int optlock) 
	{
		this.optlock=optlock;
	}
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code=code;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name=name;
	}
	
	public int getSalesofficeid()
	{
		return salesofficeid;
	}
	public void setSalesofficeid(int salesofficeid) 
	{
		this.salesofficeid=salesofficeid;
	}
	
	public String getLoginid()
	{
		return loginid;
	}
	public void setLoginid(String loginid) 
	{
		this.loginid=loginid;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password=password;
	}
	
	public int getIsdeleted()
	{
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) 
	{
		this.isdeleted=isdeleted;
	}
	
	
	 public String getSalesofficeidSalesofficeLkdDesc()
	{
		return salesofficeidSalesofficeLkdDesc;
	}
	public void setSalesofficeidSalesofficeLkdDesc(String salesofficeidSalesofficeLkdDesc) 
	{
		this.salesofficeidSalesofficeLkdDesc=salesofficeidSalesofficeLkdDesc;
	}
}
