package com.hssa.ezybiz.dto;
import java.io.Serializable;
import java.sql.Timestamp;
 
public class Salesoffice implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	private Integer id;
	private Timestamp createdate;
	private String creator;
	private String entityUid;
	private String modifier;
	private Timestamp updatedate;
	private Integer optlock;
	private String code;
	private String name;
	private Integer salesunitid;
	private Integer isdeleted;
	private String returnMsg;
	private String salesunitidSalesunitLkdDesc;
  
	public Integer getId()
	{
		return id;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public void setId(Integer id) 
	{
		this.id=(id);
	}
	
	public Timestamp getCreatedate()
	{
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) 
	{
		this.createdate = createdate;
	}
	
	public String getCreator()
	{
		return creator;
	}
	public void setCreator(String creator) 
	{
		this.creator=(creator);
	}
	
	public String getEntityUid()
	{
		return entityUid;
	}
	public void setEntityUid(String entityUid) 
	{
		this.entityUid=(entityUid);
	}
	
	public String getModifier()
	{
		return modifier;
	}
	public void setModifier(String modifier) 
	{
		this.modifier=(modifier);
	}
	
	public Timestamp getUpdatedate()
	{
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) 
	{
		this.updatedate = updatedate;
	}
	
	public Integer getOptlock()
	{
		return optlock;
	}
	public void setOptlock(Integer optlock) 
	{
		this.optlock=(optlock);
	}
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code=(code);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name=(name);
	}
	
	public Integer getSalesunitid()
	{
		return salesunitid;
	}
	public void setSalesunitid(Integer salesunitid) 
	{
		this.salesunitid=(salesunitid);
	}
	
	public Integer getIsdeleted()
	{
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) 
	{
		this.isdeleted=(isdeleted);
	}
	
	
	 public String getSalesunitidSalesunitLkdDesc()
	{
		return salesunitidSalesunitLkdDesc;
	}
	public void setSalesunitidSalesunitLkdDesc(String salesunitidSalesunitLkdDesc) 
	{
		this.salesunitidSalesunitLkdDesc=(salesunitidSalesunitLkdDesc);
	}
 
 
	public Salesoffice()
	{
        	init();		
	}
 
	public Salesoffice(Integer id,Timestamp createdate,String creator,String entityUid,String modifier,Timestamp updatedate,Integer optlock,String code,String name,Integer salesunitid,Integer isdeleted)
	{
		super();
        	init();		
		this.id = id;
		this.createdate = createdate;
		this.creator = creator;
		this.entityUid = entityUid;
		this.modifier = modifier;
		this.updatedate = updatedate;
		this.optlock = optlock;
		this.code = code;
		this.name = name;
		this.salesunitid = salesunitid;
		this.isdeleted = isdeleted;
	}
 
	public Salesoffice(Salesoffice salesoffice)
	{
		super();
		init();
		copy(salesoffice);
	}
	
	public void copy(Salesoffice salesoffice)
	{
	    this.setId(salesoffice.getId());
	    this.setCreatedate(salesoffice.getCreatedate());
	    this.setCreator(salesoffice.getCreator());
	    this.setEntityUid(salesoffice.getEntityUid());
	    this.setModifier(salesoffice.getModifier());
	    this.setUpdatedate(salesoffice.getUpdatedate());
	    this.setOptlock(salesoffice.getOptlock());
	    this.setCode(salesoffice.getCode());
	    this.setName(salesoffice.getName());
	    this.setSalesunitid(salesoffice.getSalesunitid());
	    this.setIsdeleted(salesoffice.getIsdeleted());
	}
 
	public final void init()
    {
        this.id = 0;
        this.createdate = new Timestamp(0);
        this.creator = "";
        this.entityUid = "";
        this.modifier = "";
        this.updatedate = new Timestamp(0);
        this.optlock = 0;
        this.code = "";
        this.name = "";
        this.salesunitid = 0;
        this.isdeleted = 0;
    }
 
}
