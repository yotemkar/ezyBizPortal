package com.hssa.ezybiz.dto;


public class ServerSession {

	private String sessionID;
	private int userID;
	private int module;
	private int subModule;

	
	private static final long serialVersionUID = 8730067275784185257L;
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int i) {
		this.userID = i;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getSubModule() {
		return subModule;
	}
	public void setSubModule(int subModule) {
		this.subModule = subModule;
	}
	
}
