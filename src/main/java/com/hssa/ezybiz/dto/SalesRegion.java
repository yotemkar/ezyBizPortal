package com.hssa.ezybiz.dto;
import java.io.Serializable;
import java.sql.Timestamp;
 
 
public class SalesRegion implements Serializable 
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
	private Integer enterpriseid;
	private Integer isdeleted;
 
	private String enterpriseidEnterpriseLkdDesc;
 
 
 
	public Integer getId()
	{
		return id;
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
	
	public Integer getEnterpriseid()
	{
		return enterpriseid;
	}
	public void setEnterpriseid(Integer enterpriseid) 
	{
		this.enterpriseid=(enterpriseid);
	}
	
	public Integer getIsdeleted()
	{
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) 
	{
		this.isdeleted=(isdeleted);
	}
	
	
	 public String getEnterpriseidEnterpriseLkdDesc()
	{
		return enterpriseidEnterpriseLkdDesc;
	}
	public void setEnterpriseidEnterpriseLkdDesc(String enterpriseidEnterpriseLkdDesc) 
	{
		this.enterpriseidEnterpriseLkdDesc=(enterpriseidEnterpriseLkdDesc);
	}
 
 
	public SalesRegion()
	{
        	init();		
	}
 
	public SalesRegion(Integer id,Timestamp createdate,String creator,String entityUid,String modifier,Timestamp updatedate,Integer optlock,String code,String name,Integer enterpriseid,Integer isdeleted)
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
		this.enterpriseid = enterpriseid;
		this.isdeleted = isdeleted;
	}
 
	public SalesRegion(SalesRegion salesregion)
	{
		super();
		init();
		copy(salesregion);
	}
	
	public void copy(SalesRegion salesregion)
	{
	    this.setId(salesregion.getId());
	    this.setCreatedate(salesregion.getCreatedate());
	    this.setCreator(salesregion.getCreator());
	    this.setEntityUid(salesregion.getEntityUid());
	    this.setModifier(salesregion.getModifier());
	    this.setUpdatedate(salesregion.getUpdatedate());
	    this.setOptlock(salesregion.getOptlock());
	    this.setCode(salesregion.getCode());
	    this.setName(salesregion.getName());
	    this.setEnterpriseid(salesregion.getEnterpriseid());
	    this.setIsdeleted(salesregion.getIsdeleted());
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
        this.enterpriseid = 0;
        this.isdeleted = 0;
    }
 
}
