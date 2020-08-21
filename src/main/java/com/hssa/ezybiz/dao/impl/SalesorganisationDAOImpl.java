package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.SalesorganisationDAO;
import com.hssa.ezybiz.dto.Salesorganisation;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.utils.DaoHelper;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class SalesorganisationDAOImpl extends JdbcDaoSupport implements SalesorganisationDAO {

	@Autowired 
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	
	public List<Salesorganisation> search(ServerSession serverSession, List<Object> paramList) {
		Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
		return (List<Salesorganisation>) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Salesorganisation"), objArrSearchableTokens, BeanPropertyRowMapper.newInstance(Salesorganisation.class)); 
	}
	public List<Salesorganisation> getAll() {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Salesorganisation"), BeanPropertyRowMapper.newInstance(Salesorganisation.class)); 

	}
	public int insertSalesorganisation(Salesorganisation salesorganisation, int id, int userId){
		return getJdbcTemplate().update( LoadJPAFQueries.getQueryById("INSERT_Salesorganisation"), 
				new Object[] { 
						salesorganisation.getId(),
						salesorganisation.getCreatedate(),
						salesorganisation.getCreator(),
						salesorganisation.getEntityUid(),
						salesorganisation.getModifier(),
						salesorganisation.getUpdatedate(),
						salesorganisation.getOptlock(),
						salesorganisation.getCode(),
						salesorganisation.getName(),
						salesorganisation.getEnterpriseid(),
						salesorganisation.getIsdeleted(),
		});
	}
	public int deleteSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_Salesorganisation_BY_ID"),new Object[] { salesorganisation.getId() });
	}
	public int updateSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation){

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Salesorganisation_BY_ID"), new Object[] {
				salesorganisation.getCreatedate(),
				salesorganisation.getCreator(),
				salesorganisation.getEntityUid(),
				salesorganisation.getModifier(),
				salesorganisation.getUpdatedate(),
				salesorganisation.getOptlock(),
				salesorganisation.getCode(),
				salesorganisation.getName(),
				salesorganisation.getEnterpriseid(),
				salesorganisation.getIsdeleted(),
				salesorganisation.getId()
		});
	}
	public Salesorganisation findSalesorganisationByID(int id){
		return (Salesorganisation)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Salesorganisation_BY_ID"), new Object[] {id}, new BeanPropertyRowMapper<Salesorganisation>(Salesorganisation.class));
	}



	//Finder methods for ForeignKey fields
	public List<Salesorganisation> findSalesorganisationByFKEnterpriseid(ServerSession serverSession, int enterpriseid){
		return (List<Salesorganisation>)getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Salesorganisation_By_FKEnterpriseid"), new Object[] {
				enterpriseid }, BeanPropertyRowMapper.newInstance(Salesorganisation.class)); 
	}


	@Override
	public Salesorganisation findSalesOrganisationByCustomerId(Integer customerId) {
		return (Salesorganisation)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("FIND_Sale_organisation_By_Cutomer_Id"),
				new Object[] {customerId}, new BeanPropertyRowMapper<Salesorganisation>(Salesorganisation.class));
	}


	@Override
	public int getSaleOrganisationCountByCode(String code) {
		return (Integer)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_SALEORG_COUNT_BY_CODE"), new Object[] {code}, Integer.class);
	}


	@Override
	public Salesorganisation getSaleOrganisationByCode(String salesOrgCode) {
		return (Salesorganisation)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_SALEORG_BY_CODE"), 
				new Object[] {salesOrgCode}, new BeanPropertyRowMapper<Salesorganisation>(Salesorganisation.class));
	}


	@Override
	public String findSalesOrganisationCodeByCustomerId(Integer customerId) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.
				getQueryById("FIND_SALES_ORGANISATION_CODE_BY_CUSTOMER_ID"),
				new Object[]{customerId}, String.class);
	}


	@Override
	public String getSalesOrgCodeByCustomerCode(String customerCode) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.
				getQueryById("FIND_SALES_ORGANISATION_CODE_BY_CUSTOMER_CODE"),
				new Object[]{customerCode}, String.class);
	}
	@Override
	public List<Salesorganisation> getAllSalesOrgByCustomerCode(String customerCode) {
		return (List<Salesorganisation>)getJdbcTemplate().query(LoadJPAFQueries.getQueryById("FIND_All_SALES_ORGANISATION_BY_CUSTOMER_CODE"), new Object[] {
				customerCode }, BeanPropertyRowMapper.newInstance(Salesorganisation.class)); 
		
	}


	@Override
	public Salesorganisation findSalesOrganisationByCompanyId(Integer companyId) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("FIND_SALES_ORGANISATION_BY_COMPANYID"), new Object[] {
				companyId }, new BeanPropertyRowMapper<Salesorganisation>(Salesorganisation.class)); 
	
	}

}

