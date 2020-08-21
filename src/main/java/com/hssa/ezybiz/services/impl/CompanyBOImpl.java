package com.hssa.ezybiz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.CompanyDAO;
import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.services.CompanyBO;



@Service
public class CompanyBOImpl implements CompanyBO {

	@Autowired
	private  CompanyDAO companyDAO;

	@Override
	public int validateCompanyById(String companyId) {
		return companyDAO.validateCompanyById(companyId);
	}
	
	public Company findCompanyByID(Integer id) {
		return companyDAO.findCompanyByID(id);
	}

	@Override
	public Integer getCompanyIdByURL(String host) {
		return companyDAO.getCompanyIdByURL(host);
	}

	@Override
	public String getPortalURLByCompanyId(Integer companyId) {
		return companyDAO.getPortalURLByCompanyId(companyId);
	}

	@Override
	public Company findCompanyByCustomerCode(String customerCode) {
		return companyDAO.findCompanyByCustomerCode(customerCode);
	}

	@Override
	public Company findCompanyBySalesOrgCode(String salesOrgCOde) {
		return companyDAO.findCompanyBySalesOrgCode(salesOrgCOde);

	}

	@Override
	public Company findCompanyBySalesGroupId(Integer salesGroupId) {
		return companyDAO.findCompanyBySalesGroupId(salesGroupId);

	}

	@Override
	public Company findCompanyBySalesOfficeId(Integer salesOfficeId) {
		return companyDAO.findCompanyBySalesOfficeId(salesOfficeId);

	}

	@Override
	public Company findCompanyByEnterpriseID(Integer enterpriseID) {
		return companyDAO.findCompanyByEnterpriseID(enterpriseID);

	}

	@Override
	public Company findCompanyByCreditRepGroupCode(String CRGCode) {
		return companyDAO.findCompanyByCreditRepGroupCod(CRGCode);
	}

	@Override
	public Company findCompanyByCustomerId(int customerId) {
		return companyDAO.findCompanyByCustomerId(customerId);
	}

	@Override
	public List<Company> getAllCompanyList() {
		return companyDAO.getAllCompanyList();

	}
	
}
