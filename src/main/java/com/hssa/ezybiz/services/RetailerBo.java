package com.hssa.ezybiz.services;

import java.util.List;

import com.hssa.ezybiz.dto.Retailer;
import com.hssa.ezybiz.dto.RetailerDealers;

public interface RetailerBo {

	public Retailer findRetailerById(Integer retailerId);
	public String findRetailerNameById(Integer retailerId);
	
	// Added by Mohsin for RetailPos
	Retailer findRetailerByMobile(String mobile);
	List<RetailerDealers> findRetailerDealers(Integer retailerId);
	List<Retailer> findChildRetailers(Integer retailerId);
	boolean retailerRegistration(Retailer retailer);
	
}
