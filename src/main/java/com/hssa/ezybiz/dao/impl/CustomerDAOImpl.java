package com.hssa.ezybiz.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.CompanyDAO;
import com.hssa.ezybiz.dao.CustomerDAO;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.CustomerDetails;
import com.hssa.ezybiz.dto.CustomerUserDto;
import com.hssa.ezybiz.dto.UserProfile;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class CustomerDAOImpl extends JdbcDaoSupport implements CustomerDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Autowired
	CompanyDAO companyDAO;

	@Override
	public List<Customer> search(Customer customer) {
		String query = LoadJPAFQueries.getQueryById("SEARCH_Customer");
		// Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
		String tmpCustName = customer.getCustomerName() + "";
		if ("".equals(tmpCustName) || "null".equals(tmpCustName)) {
			tmpCustName = "%";
		} else {
			query += "AND customer.CUSTOMERNAME like '%" + tmpCustName + "%' ";
			// tmpCustName = "%" + tmpCustName + "%";
		}

		String tmpCustCode = customer.getCode() + "";
		if ("".equals(tmpCustCode) || "null".equals(tmpCustCode)) {
			tmpCustCode = "%";
		} else {
			query += "AND customer.CODE like '%" + tmpCustCode + "%' ";

			// tmpCustCode = "%" + tmpCustCode + "%";
		}

		String tmpEmail = customer.getEmail() + "";
		if ("".equals(tmpEmail) || "null".equals(tmpEmail)) {
			tmpEmail = "%";
		} else {
			query += "and contactLkd.email like '%" + tmpEmail + "%'";
			// tmpEmail = "%" + tmpEmail + "%";
		}

		String tmpSalesOrg = customer.getSalesOrganisation() + "";
		if ("".equals(tmpSalesOrg) || "null".equals(tmpSalesOrg)) {
			tmpSalesOrg = "%";
		} else {
			query += "and (salesorgLkd.name like '%" + tmpSalesOrg + "%' OR salesorgLkd.CODE LIKE '%" + tmpSalesOrg
					+ "%')";
			// tmpSalesOrg = "%" + tmpSalesOrg + "%";
		}

		String tmpDistChnl = customer.getDistChannel() + "";
		if ("".equals(tmpDistChnl) || "null".equals(tmpDistChnl)) {
			tmpDistChnl = "%";
		} else {
			query += "and (distchannelLkd.name like '%" + tmpDistChnl + "%' OR distchannelLkd.code like '%"
					+ tmpDistChnl + "%')";
			// tmpDistChnl = "%" + tmpDistChnl + "%";
		}

		String tmpSalesOffice = customer.getSalesOffice() + "";
		if ("".equals(tmpSalesOffice) || "null".equals(tmpSalesOffice)) {
			tmpSalesOffice = "%";
		} else {
			query += "and (salesofficeLkd.name like '%" + tmpSalesOffice + "%' OR salesofficeLkd.CODE like '%"
					+ tmpSalesOffice + "%') ";
			// tmpSalesOffice = "%" + tmpSalesOffice + "%";
		}
		String tmpCustGroup = customer.getCustomerGroupName() + "";
		if ("".equals(tmpCustGroup) || "null".equals(tmpCustGroup)) {
			tmpCustGroup = null;
		} else {
			query += "AND (" + tmpCustGroup + " IS NULL or customergroupLkd.name LIKE '%" + tmpCustGroup + "%')";
			// tmpCustGroup = "%" + tmpCustGroup + "%";
		}

		String tmpSalesUnit = customer.getSalesUnit() + "";
		if ("".equals(tmpSalesUnit) || "null".equals(tmpSalesUnit)) {
			tmpSalesUnit = "%";
		} else {
			query += "and (salesunitLkd.name like '%" + tmpSalesUnit + "%' OR salesunitLkd.CODE like '%" + tmpSalesUnit
					+ "%' )";
			// tmpSalesUnit = "%" + tmpSalesUnit + "%";
		}

		Integer tmpAutoPosting = customer.getIsDirectPosting();
		if (tmpAutoPosting != null) {
			query += "and (" + tmpAutoPosting + " IS NULL OR customer.ISDIRECTPOSTING = " + tmpAutoPosting + ")";
		}
		Integer tmpAccessPortal = customer.getAccessPortal();
		if (tmpAccessPortal != null) {
			query += "and (" + tmpAccessPortal + " IS NULL OR customer.ACCESSPORTAL = " + tmpAccessPortal + ")";

		}
		// if (customer.getCompanyId() != null) {
		// query += "AND en.COMPANYID =" + customer.getCompanyId();
		// }
		/*
		 * Object[] parameterObject = new Object[] { tmpCustName, tmpCustCode, tmpEmail,
		 * tmpSalesOrg, tmpSalesOrg, tmpDistChnl, tmpDistChnl, tmpSalesOffice,
		 * tmpSalesOffice, tmpSalesUnit, tmpSalesUnit, tmpAutoPosting, tmpAutoPosting,
		 * tmpCustGroup, tmpCustGroup, tmpAccessPortal, tmpAccessPortal,
		 * customer.getCompanyId()
		 * 
		 * }; return
		 * getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Customer"),
		 * parameterObject, BeanPropertyRowMapper.newInstance(Customer.class));
		 */
		query += "ORDER BY customer.ID";
		return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(Customer.class));

	}

	@Override
	public List<Customer> getAll(Integer companyId) {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Customer"), new Object[] { companyId },
				BeanPropertyRowMapper.newInstance(Customer.class));

	}

	@Override
	public int updateCustomer(Customer oldCustomer, Customer customer, int id, int userId, boolean salesPromoterUpdateFlag) {
		int k=0;
		if(salesPromoterUpdateFlag){
		 k = getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Customer_BY_ID_SalesPromoter"),
				new Object[] { customer.getModifier(), customer.getUpdateDate(), customer.getIsDirectPosting(),
						customer.getAccessPortal(),  customer.getAccessKey(),
						customer.isSecondarySalesAccess(), customer.getDeactivationDate(), customer.getActivationDate(),
						customer.getStatusUpdatedBy(),customer.getCustomerName(),customer.getSalesPromoterId(), customer.getId() });
		}else{
		 k = getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Customer_BY_ID"),
				new Object[] { customer.getModifier(), customer.getUpdateDate(), customer.getIsDirectPosting(),
						customer.getAccessPortal(),  customer.getAccessKey(),
						customer.isSecondarySalesAccess(), customer.getDeactivationDate(), customer.getActivationDate(),
						customer.getStatusUpdatedBy(),customer.getCustomerName(), customer.getId() });
		}	
		
		return k;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Customer findCustomerByID(int id) {
		return (Customer) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("SELECT_Customer_By_Customer_Id"), new Object[] { id },
				new BeanPropertyRowMapper(Customer.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Customer findCustomerByCustomerCodeAndAccessKey(String code, Integer companyId)
			throws EmptyResultDataAccessException {
		try {
			return (Customer) getJdbcTemplate().queryForObject(
					LoadJPAFQueries.getQueryById("SELECT_Customer_BY_Code_AccessKey"), new Object[] { code, companyId },
					new BeanPropertyRowMapper(Customer.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Customer> getCustomerListforEmployee(int employeeId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Customer_By_EmployeeId"),
				new Object[] { employeeId }, BeanPropertyRowMapper.newInstance(Customer.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Customer findCustomerByUserId(int userId) {
		try {
			return (Customer) getJdbcTemplate().queryForObject(
					LoadJPAFQueries.getQueryById("Select_Customer_By_UserId"), new Object[] { userId },
					new BeanPropertyRowMapper(Customer.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public UserProfile getUserProfileDetail(int userId) throws Exception {
		try {
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_CustomerUser_For_Profile"),
					new Object[] { userId }, new BeanPropertyRowMapper<UserProfile>(UserProfile.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean isCustomerCodeExist(String code, Integer companyId) throws Exception {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("CHECK_CustomerCode_Exists"),
				new Object[] { code, companyId }, Integer.class) > 0;
	}

	@Override
	public boolean isDelcrederCodeExist(String code, Integer companyId) throws Exception {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("CHECK_DelcrederCode_Exists"),
				new Object[] { code }, Integer.class) > 0;
	}

	@Override
	public int insertCustomer(Customer customer, int id, int userId) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_CUSTOMER"),
				new Object[] { customer.getId(), customer.getCreateDate(), customer.getCreator(),
						customer.getEntityUid(), customer.getModifier(), customer.getUpdateDate(),
						customer.getOptLock(), customer.getAccessKey(), customer.getAccessPortal(), customer.getCode(),
						customer.getHeadQuarter(), customer.getCustomerName(), customer.getSingleLocation(),
						customer.getCustomerGroupId(), customer.getMainContactId(), customer.getParentHqId(),
						customer.getSalesAreaId(), customer.getSalesGroupId(), customer.getSalesOfficeId(),
						customer.getSalesPromoter(), customer.getSalesPromoterId(), customer.getIsDirectPosting(),
						customer.getVirtualAccount(), customer.isSecondarySalesAccess(), customer.getIsDeleted(),
						customer.getDelcreder(), customer.getShowSalesData(), customer.getActivationDate(),
						customer.getDeactivationDate(), customer.getStatusUpdatedBy() });
	}

	@Override
	public List<Customer> getAllFilteredCustomerByEmoployeeId(Customer customer) {

		// Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
		boolean isSalesPromotor = false;
		String query = "GET_ALL_FILTERED_CUSTOMER_BY_EMOPLOYEE_ID";

		if (customer.getRole().equalsIgnoreCase(CustomerPortalConstants.Salespromotor_ROLE)) {
			query = "GET_CustomerOfSalesPromotorUserId";
			isSalesPromotor = true;
		}
		query = LoadJPAFQueries.getQueryById(query);

		String tmpCustName = customer.getCustomerName() + "";
		if ("".equals(tmpCustName) || "null".equals(tmpCustName)) {
			tmpCustName = "%";
		} else {
			query += "AND customer.CUSTOMERNAME like '%" + tmpCustName + "%'";
			// tmpCustName = "%" + tmpCustName + "%";
		}

		String tmpCustCode = customer.getCode() + "";
		if ("".equals(tmpCustCode) || "null".equals(tmpCustCode)) {
			tmpCustCode = "%";
		} else {
			query += "AND customer.CODE like '%" + tmpCustCode + "%'";

			// tmpCustCode = "%" + tmpCustCode + "%";
		}

		String tmpEmail = customer.getEmail() + "";
		if ("".equals(tmpEmail) || "null".equals(tmpEmail)) {
			tmpEmail = "%";
		} else {
			query += "AND contactLkd.email like '%" + tmpEmail + "%'";

			// tmpEmail = "%" + tmpEmail + "%";
		}

		String tmpSalesOrg = customer.getSalesOrganisation() + "";
		if ("".equals(tmpSalesOrg) || "null".equals(tmpSalesOrg)) {
			tmpSalesOrg = "%";
		} else {
			query += "and (salesorgLkd.name like '%" + tmpSalesOrg + "%' OR salesorgLkd.CODE LIKE '%" + tmpSalesOrg
					+ "%')";
			// tmpSalesOrg = "%" + tmpSalesOrg + "%";
		}

		String tmpDistChnl = customer.getDistChannel() + "";
		if ("".equals(tmpDistChnl) || "null".equals(tmpDistChnl)) {
			tmpDistChnl = "%";
		} else {

			query += "and (distchannelLkd.name like '%" + tmpDistChnl + "%' OR distchannelLkd.CODE LIKE '%"
					+ tmpDistChnl + "%')";

			// tmpDistChnl = "%" + tmpDistChnl + "%";
		}

		String tmpSalesOffice = customer.getSalesOffice() + "";
		if ("".equals(tmpSalesOffice) || "null".equals(tmpSalesOffice)) {
			tmpSalesOffice = "%";
		} else {

			query += "and (salesofficeLkd.name like '%" + tmpSalesOffice + "%' OR salesofficeLkd.CODE LIKE '%"
					+ tmpSalesOffice + "%')";

			// tmpSalesOffice = "%" + tmpSalesOffice + "%";
		}
		String tmpCustGroup = customer.getCustomerGroupName() + "";
		if ("".equals(tmpCustGroup) || "null".equals(tmpCustGroup)) {
			tmpCustGroup = null;
		} else {
			query += "AND (customergroupLkd.name LIKE '%" + tmpCustGroup + "%')";

			// tmpCustGroup = "%" + tmpCustGroup + "%";
		}

		String tmpSalesUnit = customer.getSalesUnit() + "";
		if ("".equals(tmpSalesUnit) || "null".equals(tmpSalesUnit)) {
			tmpSalesUnit = "%";
		} else {
			query += "and (salesunitLkd.name like '%" + tmpSalesUnit + "%' OR salesunitLkd.CODE like '%" + tmpSalesUnit
					+ "%')";
			// tmpSalesUnit = "%" + tmpSalesUnit + "%";
		}

		Integer tmpAutoPosting = customer.getIsDirectPosting();
		if (tmpAutoPosting != null)
			query += "and (customer.ISDIRECTPOSTING =" + tmpAutoPosting + ")";
		Integer tmpAccessPortal = customer.getAccessPortal();
		if (tmpAccessPortal != null)
			query += "and (customer.ACCESSPORTAL =" + tmpAccessPortal + ")";
		Integer employeeId = customer.getEmployeeId();

		if (isSalesPromotor) {
			query += "AND customeruserLkd1.id = " + employeeId + "";
			query += " and (customer.SALESPROMOTER IS NULL OR customer.SALESPROMOTER = 0)";
			query += "ORDER BY customer.ID";

		} else {
			if (employeeId != null)
				query += " and (ergm.EMPLOYEE_ID=" + employeeId + ")";
			query += "and (customer.delcreder is NULL OR customer.delcreder = 0) ORDER BY customer.ID";

		}

		/*
		 * Object[] parameterObject = new Object[] { tmpCustName, tmpCustCode, tmpEmail,
		 * tmpSalesOrg, tmpSalesOrg, tmpDistChnl, tmpDistChnl, tmpSalesOffice,
		 * tmpSalesOffice, tmpSalesUnit, tmpSalesUnit, tmpAutoPosting, tmpAutoPosting,
		 * tmpCustGroup, tmpCustGroup, tmpAccessPortal, tmpAccessPortal, employeeId
		 * 
		 * }; if (customer.getRole().equalsIgnoreCase(CustomerPortalConstants.
		 * Salespromotor_ROLE)) { parameterObject = new Object[] { tmpCustName,
		 * tmpCustCode, tmpEmail, tmpSalesOrg, tmpSalesOrg, tmpDistChnl, tmpDistChnl,
		 * tmpSalesOffice, tmpSalesOffice, tmpSalesUnit, tmpSalesUnit, tmpAutoPosting,
		 * tmpAutoPosting, tmpCustGroup, tmpCustGroup, tmpAccessPortal, tmpAccessPortal,
		 * employeeId
		 * 
		 * }; } return getJdbcTemplate().query(LoadJPAFQueries.getQueryById(query),
		 * parameterObject, BeanPropertyRowMapper.newInstance(Customer.class));
		 */
		
		System.out.println("query========"+query);
		return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(Customer.class));

	}

	@Override
	public List<Customer> getAllFilteredCustomerByDelcrederId(Customer customer) {
		String query = "GET_ALL_FILTERED_CUSTOMER_BY_DELCREDER_ID";

		String tmpCustName = customer.getCustomerName() + "";
		if ("".equals(tmpCustName) || "null".equals(tmpCustName)) {
			tmpCustName = "%";
		} else {
			tmpCustName = "%" + tmpCustName + "%";
		}

		String tmpCustCode = customer.getCode() + "";
		if ("".equals(tmpCustCode) || "null".equals(tmpCustCode)) {
			tmpCustCode = "%";
		} else {
			tmpCustCode = "%" + tmpCustCode + "%";
		}

		Integer tmpAccessPortal = customer.getAccessPortal();
		Integer delCrederId = customer.getId();

		Object[] parameterObject = new Object[] { tmpCustName, tmpCustCode, tmpAccessPortal, tmpAccessPortal,
				delCrederId };
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById(query), parameterObject,
				BeanPropertyRowMapper.newInstance(Customer.class));

	}

	@Override
	public int updateCustomerAccessKey(Customer oldCustomer, Customer customer, int id, Integer userId) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("Update_CUSTOMER_AccessKey"),
				new Object[] { customer.getAccessKey(), customer.getId() });
	}

	@Override
	public List<CustomerUserDto> getAllCustomerUsers(Integer userId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_CUSTOMER_USERS"), new Object[] { userId },
				BeanPropertyRowMapper.newInstance(CustomerUserDto.class));
	}

	@Override
	public String findCustomerCodeByID(Integer customerId) {
		return (String) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("FIND_CUSTOMER_CODE_BY_ID"),
				new Object[] { customerId }, String.class);
	}

	// Added by Mohsin for RetailPos
	@Override
	public String findCustomerMobileByID(Integer customerId) {
		return (String) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_CUSTOMER_MOBILE_BY_ID"),
				new Object[] { customerId }, String.class);
	}

	@Override
	public List<Map<String, Object>> getCustomerDetailsByUserId(int userId) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_CUSTOMER_DETAILS_BY_USER_ID"), userId);
	}

	@Override
	public Customer getCustomerCompanyIdByMobile(String mobileNumber) {
		try {
			return (Customer) getJdbcTemplate().queryForObject(
					LoadJPAFQueries.getQueryById("GET_CUSTOMER_COMPANY_ID_BY_MOBILE"), new Object[] { mobileNumber },
					new BeanPropertyRowMapper<Customer>(Customer.class));
		} catch (EmptyResultDataAccessException e) {
			Customer c = new Customer();
			c.setId(-1);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getActiveCustomersCount(Integer companyId) {
		return (String) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_CUSTOMER_ACTIVE_INACTIVE_COUNT"), new Object[] { companyId, 1 },
				String.class);
	}

	@Override
	public String getInactiveCustomersCount(Integer companyId) {
		return (String) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_CUSTOMER_ACTIVE_INACTIVE_COUNT"), new Object[] { companyId, 0 },
				String.class);
	}

	@Override
	public String getActiveCustomerCountByLoginDuration(Integer companyId) {
		// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
		return (String) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_ACL_ACTIVE_CUSTOMERS_COUNT_BY_LOGIN_DURATION"), new Object[] {},
				String.class);
		// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
		// return (String) getJdbcTemplate().queryForObject(
		// LoadJPAFQueries.getQueryById("GET_ACC_ACTIVE_CUSTOMERS_COUNT_BY_LOGIN_DURATION"),
		// new Object[] {},
		// String.class);
		// else
		// return null;
	}

	@Override
	public String getInActiveCustomerCountByLoginDuration(Integer companyId) {
		// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
		return (String) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_ACL_INACTIVE_CUSTOMERS_COUNT_BY_LOGIN_DURATION"), new Object[] {},
				String.class);
		// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
		// return (String) getJdbcTemplate().queryForObject(
		// LoadJPAFQueries.getQueryById("GET_ACC_INACTIVE_CUSTOMERS_COUNT_BY_LOGIN_DURATION"),
		// new Object[] {},
		// String.class);
		// else
		// return null;
	}

	@Override
	public Long getCustomersCount(Integer companyId) {
		return (Long) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_TOTAL_CUSTOMERS_COUNT"),
				/* new Object[] { companyId }, */ Long.class);
	}

	@Override
	public Long getRegisteredCustomersCount(Integer companyId) {
		// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
		return (Long) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_ACL_CUSTOMERS_REGISTERED_COUNT"), new Object[] {}, Long.class);
		// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
		// return (Long) getJdbcTemplate().queryForObject(
		// LoadJPAFQueries.getQueryById("GET_ACC_CUSTOMERS_REGISTERED_COUNT"), new
		// Object[] {}, Long.class);
		// else
		// return null;
	}

	@Override
	public Long getCustomerLockedCount(Integer companyId) {
		return (Long) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_USERS_LOCKED_COUNT"),
				new Object[] { companyId }, Long.class);
	}

	@Override
	public Long getCustomersCurrentDayLoginCount(Integer companyId, LocalDate date) {
		return (Long) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_CUSTOMER_LOGIN_COUNT_CURRENT_DAY"),
				new Object[] { companyId, date.toString() }, Long.class);
	}

	@Override
	public List<Customer> getCustomersByCurrentDayLogin(Integer companyId, LocalDate date) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_CUSTOMER_BY_CURRENT_DAY_LOGIN"),
				new Object[] { companyId, date.toString() }, BeanPropertyRowMapper.newInstance(Customer.class));
	}

	@Override
	public List<Customer> getActiveCustomersByDuration(Integer companyId) {
		try {
			// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
			return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACL_ACTIVE_CUSTOMERS_BY_DURATION"),
					new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
			// return
			// getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACC_ACTIVE_CUSTOMERS_BY_DURATION"),
			// new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> getInActiveCustomersByDuration(Integer companyId) {
		try {
			// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
			return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACL_INACTIVE_CUSTOMERS_BY_DURATION"),
					new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
			// return
			// getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACC_INACTIVE_CUSTOMERS_BY_DURATION"),
			// new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> getActiveInactiveCustomersListForDashboard(Integer companyId, Integer activeFlag) {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACTIVE_INACTIVE_CUSTOMERS"),
				new Object[] { companyId, activeFlag }, BeanPropertyRowMapper.newInstance(Customer.class));
	}

	@Override
	public List<Customer> getAllCustomersListForDashboard(Integer companyId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ALL_CUSTOMERS_LIST"),
				/* ew Object[] { companyId }, */ BeanPropertyRowMapper.newInstance(Customer.class));
	}

	@Override
	public List<Customer> getLockedCustomersList(Integer companyId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ALL_LOCKED_USERS_LIST"),
				/* new Object[] { companyId }, */ BeanPropertyRowMapper.newInstance(Customer.class));
	}

	@Override
	public List<Customer> getRegisteredCustomersList(Integer companyId) {
		try {
			// if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acl"))
			return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACL_REGISTERED_CUSTOMERS"),
					new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else if (companyDAO.findCompanyByID(companyId).getCompanyId().equals("acc"))
			// return
			// getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_ACC_REGISTERED_CUSTOMERS"),
			// new Object[] {}, BeanPropertyRowMapper.newInstance(Customer.class));
			// else
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> getNonRegisteredCustomersList(Integer companyId) {
		try {
			return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_NON_REGISTERED_CUSTOMERS_LIST"),
					/* new Object[] { companyId }, */ BeanPropertyRowMapper.newInstance(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateCustomerDistrict(CustomerDetails customer) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_DISTRICT"),
				new Object[] { customer.getSalesDiscrict(), customer.getCustomerCode()

				});

	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomerByCode(String customerCode) {
		try {
			return (Customer) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Select_Customer_By_Code"),
					new Object[] { customerCode }, new BeanPropertyRowMapper(Customer.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// @SuppressWarnings("unchecked")
	public void finddelcrederCustomerByIds(int delcrederId, int customerId) {
		try {

			int count = (int) getJdbcTemplate().queryForObject(
					LoadJPAFQueries.getQueryById("SELECT_DELCREDER_Customer"), new Object[] { delcrederId, customerId },
					Integer.class);
			if (count <= 0) {
				getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_DELCREDER_Customer"),
						new Object[] { delcrederId, customerId });
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// for updating customer from staging tables
	@Override
	public List<Map<String, Object>> getAllCustomerStg() {

		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("SELECT_ALL_CUSTOMER_MASTER_STG"));

	}

	@Override
	public List<Map<String, Object>> getCustomerSalesGroupStg(String code) {

		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("SELECT_CUSTOMER_SALESGROUP_STG_BY_CODE"),
				new Object[] { code });

	}

	@Override
	public List<Map<String, Object>> getAllCustomerSalesGroupStg() {

		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("SELECT_ALL_CUSTOMER_SALESGROUP_STG"));

	}

	@Override
	public List<Map<String, Object>> getSalesGroupPlantStg() {

		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("SELECT_ALL_SALESGROUP_PLANT_STG"));

	}

	@Override
	public List<Map<String, Object>> getSalesGroupPlantStgForGrpOrgOfc(String office, String org, String group) {
		// getting plant base on office group and org
		return getJdbcTemplate().queryForList(
				LoadJPAFQueries.getQueryById("SELECT_SALESGROUP_PLANT_STG_FOR_OFC_ORG_GRP"),
				new Object[] { office, org, group });
	}

	@Override
	public Integer insertCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode,
			String groupCode, String plantCode) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_CUSTOMER_SAELSOFFICE_PLANT_MAPPING"),
				new Object[] { code, officeCode, orgCode, groupCode, plantCode, 0 });

	}

	@Override
	public void insertCustomerRepGroupMapping(int code, int repGroup, int creGroup) {
		getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_CUSTOMER_Rep_Group_MAPPING"),
				new Object[] { code, repGroup, creGroup });

	}

	@Override
	public void updateCustomerMasterIsProcessed(int id, int i,String comment) {
		getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_MASTER_STG_IS_PROCESSED"),
				new Object[] { i, comment,id });

	}

	@Override
	public void updateSalesGrpPlantStgIsProcessed(int id, int i) {
		getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_SALES_GRP_PLANT_STG_IS_PROCESSED"),
				new Object[] { i, id });

	}

	@Override
	public void updateCustomerSalesGrpStgIsProcessed(int id, int i) {
		getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_SALES_GRP_STG_IS_PROCESSED"),
				new Object[] { i, id });

	}

	@Override
	public int updateCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode, String groupCode,
			String plantCode, int IsDelete) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_SAELSOFFICE_PLANT_MAPPING"),
				new Object[] { IsDelete, code, officeCode, orgCode, groupCode, plantCode });

	}

	@Override
	public int getCustomerIdByCode(String code) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_CUSTOMER_ID_BY_CODE"),
				new Object[] { code }, Integer.class);

	}

	@Override
	public int getCreditRepGroupIdByCode(String CreditRepGroupCode) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_CREDIT_REP_GROUP_ID_BY_CODE"),
				new Object[] { CreditRepGroupCode }, Integer.class);

	}

	@Override
	public int getCustomerCreditGroupIdByCode(String CustomerCreditGroupCode) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_CUSTOMER_CREDIT_GROUP_ID_BY_CODE"),
				new Object[] { CustomerCreditGroupCode }, Integer.class);

	}

	@Override
	public List<Map<String, Object>> getCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode,
			String groupCode, String plantCode, int IS_DELETED) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_CUSTOMER_SALESOFFICE_PLANT_MAPPING"),
				new Object[] { code, officeCode, orgCode, groupCode, plantCode, IS_DELETED });

	}

	@Override
	public List<Map<String, Object>> getAllCustomerSalesOfficePlantMapping(String code, String officeCode,
			String orgCode, String groupCode, String plantCode) {
		return getJdbcTemplate().queryForList(
				LoadJPAFQueries.getQueryById("GET_All_CUSTOMER_SALESOFFICE_PLANT_MAPPING"),
				new Object[] { code, officeCode, orgCode, groupCode, plantCode });

	}

	@Override
	public List<Map<String, Object>> getCustomerRepGroupMapping(int CUSTOMER_ID) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_CUSTOMER_REP_GROUP_MAPPING"),
				new Object[] { CUSTOMER_ID });

	}

	@Override
	public List<Map<String, Object>> getCustomerUserMapping(int CUSTOMER_ID) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_CUSTOMER_USER_MAPPING"),
				new Object[] { CUSTOMER_ID });

	}

	@Override
	public void updateCustomerRepGroupMapping(int id, int creditRepGroupId, int customerCreditGroupId) {
		getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_Rep_Group_MAPPING"),
				new Object[] { id, creditRepGroupId, customerCreditGroupId });

	}

	@Override
	public List<Customer> searchForRegisteration(String customerCode) {
		customerCode="0000000000".substring(customerCode.length())+customerCode;
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("Search_Customer_For_User_Register"),
				new Object[] {customerCode,customerCode},BeanPropertyRowMapper.newInstance(Customer.class));

	}

}
