package com.hssa.ezybiz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.SalesorganisationDAO;
import com.hssa.ezybiz.dto.Salesorganisation;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.services.SalesorganisationBO;

@Service
public class SalesorganisationBOImpl implements SalesorganisationBO {

	@Autowired
	private SalesorganisationDAO salesorganisationDAO;

	public SalesorganisationBOImpl() {
		super();
	}

	public List<Salesorganisation> search(ServerSession serverSession, List<Object> paramList) {
		return salesorganisationDAO.search(serverSession, paramList);
	}

	public List<Salesorganisation> getAllSalesorganisationList() {
		return salesorganisationDAO.getAll();
	}

	public int insertSalesorganisation(Salesorganisation salesorganisation, int id, int userId) {
		return salesorganisationDAO.insertSalesorganisation(salesorganisation, id, userId);
	}

	public int deleteSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation) {
		return salesorganisationDAO.deleteSalesorganisation(serverSession, salesorganisation);
	}

	public int updateSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation, int userId) {
		return salesorganisationDAO.updateSalesorganisation(serverSession, salesorganisation);
	}

	public Salesorganisation findSalesorganisationByID(int id) {
		return salesorganisationDAO.findSalesorganisationByID(id);
	}

	public List<Salesorganisation> findSalesorganisationByFKEnterpriseid(ServerSession serverSession,
			int enterpriseid) {
		return salesorganisationDAO.findSalesorganisationByFKEnterpriseid(serverSession, enterpriseid);
	}

	@Override
	public Salesorganisation findSalesOrganisationByCustomerId(Integer customerId) {
		return salesorganisationDAO.findSalesOrganisationByCustomerId(customerId);
	}

	@Override
	public int getSaleOrganisationCountByCode(String code) {
		return salesorganisationDAO.getSaleOrganisationCountByCode(code);
	}

	@Override
	public Salesorganisation getSaleOrganisationByCode(String salesOrgCode) {
		return salesorganisationDAO.getSaleOrganisationByCode(salesOrgCode);
	}

	@Override
	public String findSalesOrganisationCodeByCustomerId(Integer customerId) {
		return salesorganisationDAO.findSalesOrganisationCodeByCustomerId(customerId);
	}

	@Override
	public String getSalesOrgCodeByCustomerCode(String customerCode) {
		return salesorganisationDAO.getSalesOrgCodeByCustomerCode(customerCode);
	}
	@Override
	public List<Salesorganisation> getAllSalesOrgByCustomerCode(String customerCode) {
		return salesorganisationDAO.getAllSalesOrgByCustomerCode(customerCode);
	}

}
