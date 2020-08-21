package com.hssa.ezybiz.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Employee;
import com.hssa.ezybiz.dto.EmployeeUpload;
import com.hssa.ezybiz.dto.UserProfile;

public interface EmployeeBO {

	/**
	 * Returns the list of employee as per parameters contain in paramList.
	 * <p>
	 * Any developer added fields need to be taken care of by developer The
	 * lookup descriptions and FK descriptions are also fetched in VO. <br/>
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param paramList
	 *            arguments having the zero or more argument with same or
	 *            different datatypes. <br/>
	 *            The type of paramList values will be identify in
	 *            getSearchableTokens method of DaoHelper class.
	 * @return list of employee of type Base
	 * @see getAllEmployeeList
	 */
	List<Employee> search(List<Object> paramList);

	/**
	 * This method returns all employee. <br/>
	 * The lookup descriptions and FK descriptions are also fetched in VO.
	 * <p>
	 * Any developer added fields need to be taken care of by developer
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @return list of Employees
	 * @see search
	 */
	List<Employee> getAllEmployeeList(Integer companyId);

	/**
	 * Returns 1 if value inserted successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values inserted into employee table. The graphics primitives that
	 * show all rows on the screen with new one.
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param employee
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set createdBy field
	 * @return 1 if values inserted successfully otherwise return 0.
	 * @see getNextValue method of Sequence class
	 */
	int insertEmployee(Employee employee, int userId);

	/**
	 * Returns 1 if value updated successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values updated into employee table. The graphics primitives that
	 * show all rows on the screen with new updated one.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID,
	 *            module, subModule
	 * @param employee
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set updatedBy field
	 * @return 1 if values updated successfully otherwise return 0.
	 * @see nothing
	 */
	int updateEmployee(Employee employee, int userId);

	/**
	 * Returns only single row based on id.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * current id values loaded in employee object. The graphics primitives show
	 * other fields based on that id from same row.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID,
	 *            module, subModule
	 * @param id
	 *            argument is a current selected row id that use to fetch other
	 *            related data of same row
	 * @return Employees object
	 * @see nothing
	 */
	Employee findEmployeeByID(int id);

	// Finder methods for ForeignKey fields
	public List<Employee> findEmployeeByFKId(int id);

	public List<Employee> findEmployeeByFKSalesofficeid(int salesofficeid);
	
	public List<Employee> findBookingOfficerByFKSalesofficeid(int salesofficeid);
	
	public List<Employee> findSupervisorByFKSalesofficeid(int salesofficeid);
	
	/**
	 * to Upload Employee details from Excel
	 * @param file
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> uploadEmployee(File file, int userId, Company company) throws Exception;

	/**
	 * insert Employee Details
	 * @param employeeUpload
	 * @return
	 * @throws Exception
	 */
	public int insertEmployeeDetail(EmployeeUpload employeeUpload, int userId, Company company,boolean isWarehouseOperator) throws Exception;
	/**
	 * to check if employee code already exists in system or not
	 * @param EmployeeCode
	 * @return
	 * @throws Exception
	 */
	public boolean isEmployeeCodeExists(String EmployeeCode) throws Exception;
	
	/**
	 * get User Profile Detail 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserProfile getUserProfileDetail(int userId) throws Exception;
	
	Boolean checkForWareHouseOperator(Integer userId);
	
	Map<String, Object> uploadWHOperator(File file, int userId, Company company) throws Exception;

	Employee findEmployeeByCode(String employeeCode);
	
}
