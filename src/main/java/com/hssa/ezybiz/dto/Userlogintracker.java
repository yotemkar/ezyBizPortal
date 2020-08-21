package com.hssa.ezybiz.dto;
import java.io.Serializable;
 
public class Userlogintracker implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	private int id;
	private int userid;
	private int isMobile;
	private java.sql.Timestamp logintime;
 
	private String useridUserLkdDesc;
 
	public int getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(int isMobile) {
		this.isMobile = isMobile;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id=(id);
	}
	
	public int getUserid()
	{
		return userid;
	}
	public void setUserid(int userid) 
	{
		this.userid=(userid);
	}
	
	public java.sql.Timestamp getLogintime()
	{
		return logintime;
	}
	public void setLogintime(java.sql.Timestamp logintime) 
	{
		this.logintime.setTime(logintime.getTime());
	}
	
	
	 public String getUseridUserLkdDesc()
	{
		return useridUserLkdDesc;
	}
	public void setUseridUserLkdDesc(String useridUserLkdDesc) 
	{
		this.useridUserLkdDesc=(useridUserLkdDesc);
	}
 
 
	public Userlogintracker()
	{
        	init();		
	}
 
	public Userlogintracker(int id,int userid,java.sql.Timestamp logintime)
	{
		super();
        	init();		
		this.id = id;
		this.userid = userid;
		this.logintime = logintime;
	}
 
	public Userlogintracker(Userlogintracker userlogintracker)
	{
		super();
		init();
		copy(userlogintracker);
	}
	
	public void copy(Userlogintracker userlogintracker)
	{
	    this.setId(userlogintracker.getId());
	    this.setUserid(userlogintracker.getUserid());
	    this.setLogintime(userlogintracker.getLogintime());
	}
 
	public final void init()
    {
        this.id = 0;
        this.userid = 0;
        this.logintime = new java.sql.Timestamp(0);
    }
 
 
}
