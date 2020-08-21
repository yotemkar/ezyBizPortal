package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.Salesorganisation;
import com.hssa.ezybiz.dto.ServerSession;

public interface SalesorganisationDAO 
{

	List<Salesorganisation> search(ServerSession serverSession, List<Object> paramList);
	List<Salesorganisation> getAll( );
	int insertSalesorganisation(Salesorganisation salesorganisation, int id, int userId);
	int deleteSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation);
	int updateSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation);
	Salesorganisation findSalesorganisationByID(int id);
	public List<Salesorganisation> findSalesorganisationByFKEnterpriseid(ServerSession serverSession, int enterpriseid);
	Salesorganisation findSalesOrganisationByCustomerId(Integer customerId);
	int getSaleOrganisationCountByCode(String code);
	Salesorganisation getSaleOrganisationByCode(String salesOrgCode);
	String findSalesOrganisationCodeByCustomerId(Integer customerId);
	String getSalesOrgCodeByCustomerCode(String customerCode);
	List<Salesorganisation> getAllSalesOrgByCustomerCode(String customerCode);
	Salesorganisation findSalesOrganisationByCompanyId(Integer companyId);

}
