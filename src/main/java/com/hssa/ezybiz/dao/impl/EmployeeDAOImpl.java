package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.EmployeeDAO;
import com.hssa.ezybiz.dto.Employee;
import com.hssa.ezybiz.dto.UserProfile;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class EmployeeDAOImpl extends JdbcDaoSupport implements EmployeeDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Employee> search(List<Object> paramList) {
		String query = null;
		// Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
		String isWareHouseOperator = paramList.get(9) + "";

		if (isWareHouseOperator.equals("false")) {
			query = LoadJPAFQueries.getQueryById("SEARCH_Employee");
			query += "and (e.ISWAREHOUSEOPERATOR is null or e.ISWAREHOUSEOPERATOR=0)";

		} else {
			query = LoadJPAFQueries.getQueryById("SEARCH_WARE_HOUSE_OPERATOR");
			query += "and e.ISWAREHOUSEOPERATOR =1";
		}

		String tempEmpCode = paramList.get(0) + "";
		if ("".equals(tempEmpCode) || "null".equals(tempEmpCode)) {
			tempEmpCode = "%";
		} else {
			query += "and e.EMPLOYEECODE like '%" + tempEmpCode + "%'";
		}

		String tempEmpName = paramList.get(1) + "";
		if ("".equals(tempEmpName) || "null".equals(tempEmpName)) {
			tempEmpName = "%";
		} else {
			query += "and Concat(u.givenName,' ', u.FamilyName) like  '%" + tempEmpName + "%'";
		}

		String tempLoginId = paramList.get(2) + "";
		if ("".equals(tempLoginId) || "null".equals(tempLoginId)) {
			tempLoginId = "%";
		} else {
			query += "and u.loginId like '%" + tempLoginId + "%'";
		}

		String tempEmailId = paramList.get(3) + "";
		if ("".equals(tempEmailId) || "null".equals(tempEmailId)) {
			tempEmailId = "%";
		} else {
			query += "c.EMAIL Like '%" + tempEmailId + "%'";
		}

		String tempSalesOfficeName = paramList.get(4) + "";
		if ("".equals(tempSalesOfficeName) || "null".equals(tempSalesOfficeName)) {
			tempSalesOfficeName = "%";
		} else {
			query += "and (so.NAME LIKE '%" + tempSalesOfficeName + "%' OR so.CODE LIKE '%" + tempSalesOfficeName
					+ "%') ";
		}

		String tempSaleUnitName = paramList.get(5) + "";
		if ("".equals(tempSaleUnitName) || "null".equals(tempSaleUnitName)) {
			tempSaleUnitName = null;
		} else {
			query += "and (su.NAME LIKE '%" + tempSaleUnitName + "%' OR su.CODE LIKE '%" + tempSaleUnitName + "%') ";
		}

		String tmpEmpLocked = paramList.get(6) + "";
		if ("".equals(tmpEmpLocked) || "null".equals(tmpEmpLocked)) {
			tmpEmpLocked = "%";
		} else {
			query += "AND u.EMPLOYEELOCKED Like '%" + tmpEmpLocked + "%'";
		}

		String tempMobileNo = paramList.get(7) + "";
		if ("".equals(tempMobileNo) || "null".equals(tempMobileNo)) {
			tempMobileNo = "%";
		} else {
			query += "AND c.MOBILE Like '%" + tempMobileNo + "%'";
		}

		String tmpRole = paramList.get(8) + "";
		if ("".equals(tmpRole) || "null".equals(tmpRole)) {
			tmpRole = "%";
		} else {
			query += "and rm.role_name Like '%" + tmpRole + "%'";
		}

		// String tempCompanyId = objArrSearchableTokens[10] + "";
		query += "Order By EMPLOYEECODE";
		
		return (List<Employee>) getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(Employee.class));

	}

	public List<Employee> getAll(Integer companyId) {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Employee"), new Object[] { companyId },
				BeanPropertyRowMapper.newInstance(Employee.class));

	}

	public int insertEmployee(Employee employee, int id, int userId) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Employee"),
				new Object[] { employee.getEmployeetypeid(), employee.getEmployeecode(), employee.getExternalid(),
						employee.getId(), employee.getSalesofficeid(), employee.getCmsdesignation(),
						employee.getIsDeleted(), employee.getIsWareHouseOperator() });
	}

	public int updateEmployee(Employee oldEmployee, Employee employee, int id, int userId) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Employee_BY_ID"),
				new Object[] { employee.getEmployeetypeid(), employee.getEmployeecode(), employee.getExternalid(),
						employee.getSalesofficeid(), employee.getCmsdesignation(), employee.getIsDeleted(),
						employee.getId() });
	}

	public Employee findEmployeeByID(int id) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Employee_BY_ID"),
				new Object[] { id }, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	public Employee findEmployeeByCode(String employeeCode) {
		try {
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Employee_BY_CODE"),
					new Object[] { employeeCode }, new BeanPropertyRowMapper<Employee>(Employee.class));
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}

	// Finder methods for ForeignKey fields
	public List<Employee> findEmployeeByFKId(int id) {
		return (List<Employee>) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Employee_By_FKId"),
				new Object[] { id }, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	public List<Employee> findEmployeeByFKSalesofficeid(int salesofficeid) {
		return (List<Employee>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("SELECT_Employee_By_FKSalesofficeid"), new Object[] { salesofficeid },
				BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public List<Employee> findBookingOfficerByFKSalesofficeid(int salesofficeid) {
		return (List<Employee>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("SELECT_BookingOfficer_By_FKSalesofficeid"),
				new Object[] { salesofficeid }, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public List<Employee> findSupervisorByFKSalesofficeid(int salesofficeid) {
		return (List<Employee>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("SELECT_Supervisor_By_FKSalesofficeid"), new Object[] { salesofficeid },
				BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public boolean isEmployeeCodeExists(String employeeCode) throws Exception {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Count_Employee_By_Code"),
				new Object[] { employeeCode }, Integer.class) > 0;
	}

	@Override
	public UserProfile getUserProfileDetail(int userId) throws Exception {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_USERPROFILE_FOR_EMPLOYEE"),
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(UserProfile.class));
	}

	@Override
	public Boolean checkForWareHouseOperator(Integer userId) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("CHECK_FOR_WARE_HOUSE_OPERATOR"),
				new Object[] { userId }, Boolean.class);
	}

}
