package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class RetailerDealers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065945315344311536L;
	private long id;
	private String code, name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
