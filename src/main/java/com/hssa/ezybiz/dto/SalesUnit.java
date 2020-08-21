package com.hssa.ezybiz.dto;
import java.io.Serializable;
import java.sql.Timestamp;
 
 
//[-- Protected Block signature. Add your code here. 
//--]
public class SalesUnit implements Serializable 
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
	private Integer salesregionid;
	private Integer zonalheadid;
	private Integer isdeleted;
 
	private String salesregionidSalesregionLkdDesc;
	private String zonalheadidZonalheadLkdDesc;
 
//[-- Protected Block signature. Add your code here. 
//--]
 
 
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
	
	public Integer getSalesregionid()
	{
		return salesregionid;
	}
	public void setSalesregionid(Integer salesregionid) 
	{
		this.salesregionid=(salesregionid);
	}
	
	public Integer getZonalheadid()
	{
		return zonalheadid;
	}
	public void setZonalheadid(Integer zonalheadid) 
	{
		this.zonalheadid=(zonalheadid);
	}
	
	public Integer getIsdeleted()
	{
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) 
	{
		this.isdeleted=(isdeleted);
	}
	
	
	 public String getSalesregionidSalesregionLkdDesc()
	{
		return salesregionidSalesregionLkdDesc;
	}
	public void setSalesregionidSalesregionLkdDesc(String salesregionidSalesregionLkdDesc) 
	{
		this.salesregionidSalesregionLkdDesc=(salesregionidSalesregionLkdDesc);
	}
	 public String getZonalheadidZonalheadLkdDesc()
	{
		return zonalheadidZonalheadLkdDesc;
	}
	public void setZonalheadidZonalheadLkdDesc(String zonalheadidZonalheadLkdDesc) 
	{
		this.zonalheadidZonalheadLkdDesc=(zonalheadidZonalheadLkdDesc);
	}
 
 
	public SalesUnit()
	{
        	init();		
	}
 
	public SalesUnit(Integer id,Timestamp createdate,String creator,String entityUid,String modifier,Timestamp updatedate,Integer optlock,String code,String name,Integer salesregionid,Integer zonalheadid,Integer isdeleted)
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
		this.salesregionid = salesregionid;
		this.zonalheadid = zonalheadid;
		this.isdeleted = isdeleted;
	}
 
	public SalesUnit(SalesUnit salesunit)
	{
		super();
		init();
		copy(salesunit);
	}
	
	public void copy(SalesUnit salesunit)
	{
	    this.setId(salesunit.getId());
	    this.setCreatedate(salesunit.getCreatedate());
	    this.setCreator(salesunit.getCreator());
	    this.setEntityUid(salesunit.getEntityUid());
	    this.setModifier(salesunit.getModifier());
	    this.setUpdatedate(salesunit.getUpdatedate());
	    this.setOptlock(salesunit.getOptlock());
	    this.setCode(salesunit.getCode());
	    this.setName(salesunit.getName());
	    this.setSalesregionid(salesunit.getSalesregionid());
	    this.setZonalheadid(salesunit.getZonalheadid());
	    this.setIsdeleted(salesunit.getIsdeleted());
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
        this.salesregionid = 0;
        this.zonalheadid = 0;
        this.isdeleted = 0;
    }
 
 
 
//[-- Protected Block signature. Add your code here. 
//--]
 
}
