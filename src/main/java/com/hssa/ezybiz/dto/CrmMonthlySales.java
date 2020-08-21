package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class CrmMonthlySales implements Serializable{
	private static final long serialVersionUID = -6173292495698851896L;
	
	private String companyCode;
	private Integer year;
	private String month;
	private String customerCode;
	private String materialGrp1;
	private String materialGrb2;
	private Double trgtSls;
	private Double actlSls;
	private Double trgtSlsVal;
	private Double actlSlsVal;
	private String yearMonth;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getMaterialGrp1() {
		return materialGrp1;
	}
	public void setMaterialGrp1(String materialGrp1) {
		this.materialGrp1 = materialGrp1;
	}
	public String getMaterialGrb2() {
		return materialGrb2;
	}
	public void setMaterialGrb2(String materialGrb2) {
		this.materialGrb2 = materialGrb2;
	}
	public Double getTrgtSls() {
		return trgtSls;
	}
	public void setTrgtSls(Double trgtSls) {
		this.trgtSls = trgtSls;
	}
	public Double getActlSls() {
		return actlSls;
	}
	public void setActlSls(Double actlSls) {
		this.actlSls = actlSls;
	}
	public Double getTrgtSlsVal() {
		return trgtSlsVal;
	}
	public void setTrgtSlsVal(Double trgtSlsVal) {
		this.trgtSlsVal = trgtSlsVal;
	}
	public Double getActlSlsVal() {
		return actlSlsVal;
	}
	public void setActlSlsVal(Double actlSlsVal) {
		this.actlSlsVal = actlSlsVal;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
}
