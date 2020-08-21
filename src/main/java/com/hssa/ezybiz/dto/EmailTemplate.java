package com.hssa.ezybiz.dto;
import java.io.Serializable;

public class EmailTemplate implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	private int id;
	private int templateType;
	private String templateName;
	private int language;
	private String subject;
	private String mailBody;
	private String comment;
	private int createdBy;
	private java.sql.Timestamp createdTime;
	private int updatedBy;
	private java.sql.Timestamp updatedTime;

	public EmailTemplate()
	{
        	init();		
	}
	
	public final void init()
    {
        this.id = 0;
        this.templateType = 0;
        this.language = 0;
        this.subject = "";
        this.mailBody = "";
        this.comment = "";
        this.createdBy = 0;
        this.createdTime = new java.sql.Timestamp(0);
        this.updatedBy = 0;
        this.updatedTime = new java.sql.Timestamp(0);
    }
 
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id=id;
	}
	
	public int getTemplateType()
	{
		return templateType;
	}
	public void setTemplateType(int templateType) 
	{
		this.templateType=templateType;
	}
	
	public int getLanguage()
	{
		return language;
	}
	public void setLanguage(int language) 
	{
		this.language=language;
	}
	
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject=subject;
	}
	
	public String getMailBody()
	{
		return mailBody;
	}
	public void setMailBody(String mailBody) 
	{
		this.mailBody=mailBody;
	}
	
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment) 
	{
		this.comment=comment;
	}
	
	public int getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(int createdBy) 
	{
		this.createdBy=createdBy;
	}
	
	public java.sql.Timestamp getCreatedTime()
	{
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp createdTime) 
	{
		this.createdTime = createdTime;
	}
	
	public int getUpdatedBy()
	{
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) 
	{
		this.updatedBy=updatedBy;
	}
	
	public java.sql.Timestamp getUpdatedTime()
	{
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp updatedTime) 
	{
		this.updatedTime = updatedTime;
	}

	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

  
}
