package com.hssa.ezybiz.services;
 
import java.util.List;

import com.hssa.ezybiz.dto.Company;


public interface CompanyBO {
	
	Company findCompanyByID(Integer id);
	
	int validateCompanyById(String companyId);

	Integer getCompanyIdByURL(String host);

	String getPortalURLByCompanyId(Integer companyId);
	
	Company findCompanyByCustomerCode(String customerCode);

	Company findCompanyBySalesOrgCode(String string);

	Company findCompanyBySalesGroupId(Integer salesGroupId);

	Company findCompanyBySalesOfficeId(Integer salesOfficeId);

	Company findCompanyByEnterpriseID(Integer enterpriseID);

	Company findCompanyByCreditRepGroupCode(String CRGCode);

	Company findCompanyByCustomerId(int customerId);
	List<Company> getAllCompanyList();
}
