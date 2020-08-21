package com.hssa.ezybiz.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.BaseDAO;
import com.hssa.ezybiz.dao.UserDAO;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.TempUserRegister;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserDto;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

	private static final Logger logger = Logger.getLogger("UserDAOImpl");

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<User> search(User user) {
		String query = getQueryById("SEARCH_User");
		String tmpUserGivenName = user.getUserName();
		if (tmpUserGivenName == null || "".equals(tmpUserGivenName)) {
			tmpUserGivenName = null;
		} else {
			query += "AND ([user].givenName like '%" + tmpUserGivenName + "%' or [user].middleName like'%"
					+ tmpUserGivenName + "%' or [user].FamilyName like '%" + tmpUserGivenName + "%')";

		}
		String tmpemailId = user.getUserEmailId();
		if (tmpemailId == null || "".equals(tmpemailId)) {
			tmpemailId = null;
		} else {
			query += "AND  c.email like '%" + tmpemailId + "%'";
		}
		String loginId = user.getLoginId();
		if (loginId == null || "".equals(loginId)) {
			loginId = null;
		} else {
			query += "and [user].loginId like '%" + loginId + "%'";
		}
		String contactNo = user.getContactNo();
		if (contactNo == null || "".equals(contactNo)) {
			contactNo = null;
		} else {
			query += "and c.mobile like '%" + contactNo + "%'";
		}
		query += "ORDER BY [user].user_id";
		return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(User.class));
	}

	public List<User> getAll(Integer companyId) {
		return getJdbcTemplate().query(getQueryById("SELECT_ALL_User"), new Object[] { companyId },
				BeanPropertyRowMapper.newInstance(User.class));
	}

	public int deleteUser(ServerSession serverSession, User user) {

		return getJdbcTemplate().update(getQueryById("DELETE_User_BY_ID"), new Object[] { user.getUserId() });
	}

	public int insertUser(User user, int id, int userId) {
		int k = getJdbcTemplate().update(getQueryById("INSERT_User"),
				new Object[] { user.getUserId(), user.getLoginId(), user.getUserStatus(), user.getUserEmailId(),
						user.getCreatedBy(), user.getCreatedTime(), user.getUpdatedBy(), user.getUpdatedTime(),
						user.getGivenName(), user.getMiddleName(), user.getFamilyName(), user.getContactNo(),
						user.getAddress(), user.getGender(), user.getMainContactId(), user.getEntityUid(),
						user.getDateOfBirth(), user.getCompanyId(), user.getActivationDate(),
						user.getStatusUpdatedBy() });
		return k;
	}

	public int updateUser(User oldUser, User newUser, int id, int userId) {
		return getJdbcTemplate().update(getQueryById("UPDATE_User_BY_ID"),
				new Object[] { newUser.getLoginId(), newUser.getUserEmailId(), newUser.getUserStatus(),
						newUser.getCreatedBy(), newUser.getCreatedTime(), newUser.getUpdatedBy(),
						newUser.getUpdatedTime(), newUser.getDateOfBirth(), newUser.getGivenName(),
						newUser.getMiddleName(), newUser.getFamilyName(), newUser.getContactNo(), newUser.getAddress(),
						newUser.getLastLoginTime(), newUser.getImageId(), newUser.getEmployeeLocked(),
						newUser.getMainContactId(), newUser.getGender(), newUser.getDeactivationDate(),
						newUser.getActivationDate(), newUser.getStatusUpdatedBy(), newUser.getUserId() });
	}

	public User findUserByID(int userId) {
		return getJdbcTemplate().queryForObject(getQueryById("SELECT_User_BY_ID"), new Object[] { userId },
				BeanPropertyRowMapper.newInstance(User.class));
	}

	public User getUserByName(ServerSession serverSession, String userLoginName) {
		User userInfo = null;
		int count = getJdbcTemplate().queryForObject(getQueryById("COUNT_user_BY_NAME"), new Object[] { userLoginName },
				Integer.class);
		if (count > 0) {
			userInfo = (User) getJdbcTemplate().queryForObject(getQueryById("SELECT_user_BY_NAME"),
					new Object[] { userLoginName }, BeanPropertyRowMapper.newInstance(User.class));
		}
		return userInfo;
	}

	@Override
	public int deletSecurityQuest(ServerSession serverSession, User user) {
		return getJdbcTemplate().update(getQueryById("DELETE_security_user_question_by_userId"),
				new Object[] { user.getUserId() });
	}

	@Override
	public List<User> getUserByEmail(ServerSession serverSession, User user) {
		return getJdbcTemplate().query(getQueryById("SELECT_user_BY_EmailID"),
				new Object[] { user.getUserEmailId(), user.getLoginId() },
				BeanPropertyRowMapper.newInstance(User.class));

	}

	@Override
	public User getUserByEmailID(ServerSession serverSession, User user) {
		User user1 = null;
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SELECT_User_BY_EmailID"),
					new Object[] { user.getUserEmailId() }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			user1 = null;
			return user1;
		}
	}

	@Override
	public User getUserByEmailID(User user) {
		User user1 = null;
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SELECT_User_BY_EmailID"),
					new Object[] { user.getUserEmailId() }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			user1 = null;
			return user1;
		}
	}

	@Override
	public User getUserByEmailIdAndCompanyId(String emailId, int companyId)
			throws IncorrectResultSizeDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SEARCH_User_By_Emailid_CompanyId"),
					new Object[] { emailId, companyId }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	@Override
	public User getUserOnlyByEmailId(String emailId)
			throws IncorrectResultSizeDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SEARCH_User_Only_By_Emailid"),
					new Object[] { emailId }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	

	@Override
	public User getUserByMobileAndCompanyId(String mobileNumber, int companyId)
			throws IncorrectResultSizeDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SEARCH_User_By_Mobile_CompanyId"),
					new Object[] { mobileNumber, companyId }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<UserDto> getAllFilteredUser(UserDto userDto, Integer companyId) {
		String query = null;
		if (userDto.getRoleId() != null && (userDto.getRoleId() == 8 ||userDto.getRoleId()==34 || userDto.getRoleId()==35)) {
			query = getQueryById("GET_ALL_FILTERED_USER_EMPLOYEE");

		} else {
			query = getQueryById("GET_ALL_FILTERED_USER");

		}
		// String query = getQueryById("GET_ALL_FILTERED_USER");
		String tmpUserGivenName = userDto.getName();
		if (tmpUserGivenName == null || "".equals(tmpUserGivenName)) {
			tmpUserGivenName = null;
		} else {
			query += " AND (CASE WHEN u.middleName IS NOT NULL AND u.FAMILYNAME IS NOT NULL THEN Concat(u.givenName,' ',u.middleName,' ', u.FamilyName) WHEN u.middleName IS NULL AND u.familyName IS NOT NULL THEN Concat(u.givenName,' ', u.FamilyName) WHEN u.middleName IS NOT NULL AND u.familyName IS NULL THEN Concat(u.givenName,' ', u.middleName)ELSE u.givenName END like '%"
					+ tmpUserGivenName + "%') ";
		}
		String loginId = userDto.getUserName();
		if (loginId == null || "".equals(loginId)) {
			loginId = null;
		} else {
			query += " and u.loginId like '%" + loginId + "%'";
			// loginId = "%" + loginId + "%";
		}
		String tmpemailId = userDto.getEmailId();
		if (tmpemailId == null || "".equals(tmpemailId)) {
			tmpemailId = null;
		} else {
			query += " and c.email like '%" + tmpemailId + "%' ";
			// tmpemailId = "%" + tmpemailId + "%";
		}
		Integer rolId = userDto.getRoleId();
		if (rolId == null || rolId == 0) {
			rolId = null;
		} else {
			query += " and ur.role_id=" + rolId;
		}

		Integer active = userDto.getActive();
		if (active == null) {
			active = null;
		} else {
			query += " and ((cu.id IS NOT NULL  and  cu.ACCESSPORTAL = " + active
					+ ")  or (cu.id IS NULL AND u.user_status = " + active + "))";
			// query += "and cu.ACCESSPORTAL =" + active;
		}
		Integer locked = userDto.getLocked();
		if (locked == null) {
			locked = null;
		} else {
			query += " AND ((cu.id IS NOT NULL  and  uLD.locked = " + locked
					+ ") or (cu.id IS NULL and u.employeelocked = " + locked + "))";
			// query += "AND u.user_status =" + locked + "and uLD.locked = " + locked;
		}

		String tmpCustomerCode = userDto.getCustomerCode();
		if (tmpCustomerCode == null || "".equals(tmpCustomerCode)) {
			tmpCustomerCode = null;
		} else {
			query += " and cu.CODE like '%" + tmpCustomerCode + "%'";

			// tmpCustomerCode = "%" + tmpCustomerCode + "%";
		}

		String tmpCustomerName = userDto.getCustomerName();
		if (tmpCustomerName == null || "".equals(tmpCustomerName)) {
			tmpCustomerName = null;
		} else {
			query += " and cu.CUSTOMERNAME like '%" + tmpCustomerName + "%'";
			// tmpCustomerName = "%" + tmpCustomerName + "%";
		}
		String creditRepgroupID = userDto.getCredRepGroup();
		if (creditRepgroupID == null || creditRepgroupID.equals("")) {
		} else {
			query += "and crg.id=" + creditRepgroupID;
		}
		String saleOrg = userDto.getSalesOrganisation();

		if (saleOrg == null || saleOrg.equals("")) {
		} else {
			query += "and salesorgLkd.CODE='" + saleOrg + "'";
		}
		query += " AND ur.role_id !=1 AND ur.role_id !=33 ORDER BY u.user_id";
		System.out.println("query======="+query);	
		return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(UserDto.class));
	}

	@Override
	public UserDto findUserDtoByID(Integer loggedInUserId) {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SELECT_User_Dto_BY_ID"),
					new Object[] { loggedInUserId }, BeanPropertyRowMapper.newInstance(UserDto.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public String getUserTypeByUserId(Integer userId) {
		return getJdbcTemplate().queryForObject(getQueryById("getUserType"), String.class, new Object[] { userId });
	}

	@Override
	public List<Map<String, Object>> findUserSalesRegionAndSalesOffice(Integer userId) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_CUSTOMER_SALES_OFFICE_AND_REGION"),
				userId);
	}

	@Override
	public List<Map<String, Object>> findEmployeeSalesRegionAndSalesOffice(Integer userId) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("GET_EMPLOYEE_SALES_OFFICE_AND_REGION"),
				userId);
	}

	@Override
	public List<User> getOwnerUsersByCustomerCode(String customerCode) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SQL_GET_OWNER_USERS_FROM_CUSTOMER_CODE"),
				new Object[] { customerCode }, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public User getUserByEmailID(Object object, String email) {

		try {
			return getJdbcTemplate().queryForObject(getQueryById("SEARCH_User_By_Emailid"), new Object[] { email },
					BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public User getUserByMobile(String mobileNumber) throws IncorrectResultSizeDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SEARCH_User_By_Mobile"),
					new Object[] { mobileNumber }, BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<User> getUserByLoginId(String loginId) throws IncorrectResultSizeDataAccessException {
		try {
			return getJdbcTemplate().query(getQueryById("SEARCH_User_By_LoginId"), new Object[] { loginId },
					BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public User getUserByEmailID(String email) {
		try {
			return getJdbcTemplate().queryForObject(getQueryById("SELECT_User_ONLY_BY_EmailID"), new Object[] { email },
					BeanPropertyRowMapper.newInstance(User.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	@Override
	public List<String> getLoginIDsByLoginId(String loginID) {
		try {
			return getJdbcTemplate().query(getQueryById("SELECT_loginIDs_ONLY_BY_LOGINID"), new Object[] { loginID },
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(1);
						}
					});
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int addregisterUserApplication(TempUserRegister restServiceRequest) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			
		int k = getJdbcTemplate().update(connection -> {
			PreparedStatement ps = connection.prepareStatement(getQueryById("INSERT_User_Register_Application"),
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, sdf.format(restServiceRequest.getCreateDate()));
			ps.setString(2, restServiceRequest.getCreator());
			ps.setString(3, restServiceRequest.getModifier());
			ps.setString(4,  sdf.format(restServiceRequest.getUpdateDate()));
			ps.setString(5, restServiceRequest.getLoginId());
			ps.setString(6, restServiceRequest.getFirstName());
			ps.setString(7, restServiceRequest.getGivenName());
			ps.setString(8, restServiceRequest.getFamilyName());
			ps.setString(9, restServiceRequest.getEmail());
			ps.setString(10, restServiceRequest.getMobileNumber());
			ps.setString(11, restServiceRequest.getAddressName());
			ps.setString(12, restServiceRequest.getStreetName());
			ps.setString(13, restServiceRequest.getStreetSide());
			ps.setString(14, restServiceRequest.getFloor());
			ps.setString(15, restServiceRequest.getHouseNumber());
			ps.setString(16, restServiceRequest.getCity());
			ps.setString(17, restServiceRequest.getState());
			ps.setString(18, restServiceRequest.getCountry());
			ps.setString(19, restServiceRequest.getPostalCode());
			ps.setString(20, restServiceRequest.getApplicationStatus());
			ps.setString(21, restServiceRequest.getCustomerId());
			ps.setString(22, restServiceRequest.getRoleId());
			ps.setString(23, restServiceRequest.getEmailVerification());
			ps.setString(24, restServiceRequest.getUserComments());
			ps.setString(25, restServiceRequest.getCompanyName());
			ps.setDate(26,
					restServiceRequest.getDateOfBirth() != null
							? new Date(restServiceRequest.getDateOfBirth().getTime())
							: null);
			ps.setString(27, restServiceRequest.getRequestTo());
			ps.setBoolean(28, restServiceRequest.getIsEmployee());

			return ps;
		}, keyHolder);
		if (k > 0)
			restServiceRequest.setId(keyHolder.getKey() + "");

		return k;
	}

	public TempUserRegister userApplicationOtpValidetion(String oTP, String id) {

		int k = getJdbcTemplate().update(getQueryById("User_Register_Application_OTP_UPDATE"),
				new Object[] { id, oTP });
		if (k > 0)
			return getJdbcTemplate().queryForObject(getQueryById("select_TempUserRegister"), new Object[] { id },
					BeanPropertyRowMapper.newInstance(TempUserRegister.class));
		return null;

	}

	@Override
	public List<User> getOwnerUsersWithEmailByCustomerID(String customerId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SQL_GET_OWNER_USERS_FROM_CUSTOMER_ID"),
				new Object[] { customerId }, BeanPropertyRowMapper.newInstance(User.class));

	}

	@Override
	public List<TempUserRegister> getUserApplicationRequest(String fromDate, String toDate, String id, String status,
			String customerId,String requestTo) {

		String query = LoadJPAFQueries.getQueryById("SQL_GET_User_Register_Application");
		if (id != null && !id.equalsIgnoreCase("undefined"))
			query += " And tu.id=" + id;
		if (status != null && !status.equalsIgnoreCase("undefined") && !status.trim().isEmpty())
			query += " And tu.applicationStatus='" + status+"'";
		if (requestTo != null && !requestTo.equalsIgnoreCase("undefined") && !requestTo.trim().isEmpty())
			query += " And tu.requestTo='" + requestTo+"'";

		if (fromDate != null && toDate != null)
			query += " And tu.createDate between '" + fromDate + "' And '" + toDate + " 23:59:59'";
		if (customerId != null && !customerId.equalsIgnoreCase("undefined"))
			query += "And ((tu.customerid in (select id from customer where SALESPROMOTERID=" + customerId
					+ "))OR tu.customerid=" + customerId + ")";
		 query += "order by createDate desc" ;

		return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(TempUserRegister.class));

	}

	@Override
	public boolean updateTempUserRegister(TempUserRegister userRegistrationRequest) {
		String query = LoadJPAFQueries.getQueryById("Update_User_Register_Application");

		return getJdbcTemplate().update(query,
				new Object[] { userRegistrationRequest.getApplicationStatus(), userRegistrationRequest.getModifier(),
						userRegistrationRequest.getApproversComments(), userRegistrationRequest.getId() }) > 0 ? true
								: false;
	}

	

	@Override
	public List<User> getPortalAdminUsersWithEmail() {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SQL_GET_ADMIN_USERS"),
				BeanPropertyRowMapper.newInstance(User.class));

	}

}
