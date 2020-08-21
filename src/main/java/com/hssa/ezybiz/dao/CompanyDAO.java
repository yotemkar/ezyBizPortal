package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.Company;


public interface CompanyDAO 
{

	Company findCompanyByID(Integer id);

	public int validateCompanyById(String companyId);

	Integer getCompanyIdByURL(String host);

	String getPortalURLByCompanyId(Integer companyId);
	
	Company findCompanyByCustomerCode(String customerCode);

	Company findCompanyBySalesOrgCode(String salesOrgCOde);

	Company findCompanyBySalesGroupId(Integer salesGroupId);

	Company findCompanyBySalesOfficeId(Integer salesOfficeId);

	Company findCompanyByEnterpriseID(Integer enterpriseID);

	Company findCompanyByCreditRepGroupCod(String cRGCode);

	Company findCompanyByCustomerId(int customerId);

	List<Company> getAllCompanyList();
}
