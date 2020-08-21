package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.CompanyDAO;
import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class CompanyDAOImpl extends JdbcDaoSupport implements CompanyDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public Company findCompanyByID(Integer id) {
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Company_BY_ID"),
				new Object[] { id }, new BeanPropertyRowMapper<Company>(Company.class));
	}

	@Override
	public int validateCompanyById(String companyId) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Count_Company_By_Id"),
				new Object[] { companyId }, Integer.class);
		if (count > 0) {
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_Company_By_Id"),
					new Object[] { companyId }, Integer.class);
		}
		return 0;
	}

	@Override
	public Integer getCompanyIdByURL(String host) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_ID_BY_URL"),
				new Object[] { host, host }, Integer.class);
	}

	@Override
	public String getPortalURLByCompanyId(Integer companyId) {
		return (String) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_PORTAL_URL_BY_COMPANY_ID"),
				new Object[] { companyId }, String.class);
	}

	@Override
	public Company findCompanyByCustomerCode(String customerCode) {
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_BY_CUSTOMER_CODE"),
				new Object[] { customerCode }, new BeanPropertyRowMapper<Company>(Company.class));
	}

	@Override
	public Company findCompanyBySalesOrgCode(String salesOrgCOde) {
		// get company base on sales group code
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_BY_SALES_ORG_CODE"),
				new Object[] { salesOrgCOde }, new BeanPropertyRowMapper<Company>(Company.class));
	}

	@Override
	public Company findCompanyBySalesGroupId(Integer salesGroupId) {
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_BY_SALES_GROUP_ID"),
				new Object[] { salesGroupId }, new BeanPropertyRowMapper<Company>(Company.class));

	}

	@Override
	public Company findCompanyBySalesOfficeId(Integer salesOfficeId) {
		return (Company) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_COMPANY_BY_SALES_OFFICE_ID"), new Object[] { salesOfficeId },
				new BeanPropertyRowMapper<Company>(Company.class));

	}

	@Override
	public Company findCompanyByEnterpriseID(Integer enterpriseID) {
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_BY_Enterprise_ID"),
				new Object[] { enterpriseID }, new BeanPropertyRowMapper<Company>(Company.class));

	}

	@Override
	public Company findCompanyByCreditRepGroupCod(String cRGCode) {
		return (Company) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_COMPANY_BY_CRED_REP_GRP_CODE"), new Object[] { cRGCode },
				new BeanPropertyRowMapper<Company>(Company.class));

	}

	@Override
	public Company findCompanyByCustomerId(int customerId) {
		return (Company) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_COMPANY_BY_CUSTOMER_ID"),
				new Object[] { customerId }, new BeanPropertyRowMapper<Company>(Company.class));

	}

	@Override
	public List<Company> getAllCompanyList() {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_All_COMPANY"),
				new BeanPropertyRowMapper<Company>(Company.class));

	}

}
