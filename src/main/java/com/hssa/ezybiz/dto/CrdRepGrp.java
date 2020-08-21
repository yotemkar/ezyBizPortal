package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class CrdRepGrp implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CrdRepGrp() {
		init();
	}

	public CrdRepGrp(Integer id, String code, String description) {
		super();
		init();
		this.id = id;

		this.code = code;
		this.description = description;

	}

	public CrdRepGrp(CrdRepGrp salesoffice) {
		super();
		init();
		copy(salesoffice);
	}

	public void copy(CrdRepGrp salesoffice) {
		this.setId(salesoffice.getId());
		this.setCode(salesoffice.getCode());
		this.setDescription(salesoffice.getDescription());
	}

	public final void init() {
		this.id = 0;
		this.code = "";
		this.description = "";
	}

}
