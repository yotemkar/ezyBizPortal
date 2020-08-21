package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.Employee;
import com.hssa.ezybiz.dto.UserProfile;
 
public interface EmployeeDAO 
{

	List<Employee> search( List<Object> paramList);
	List<Employee> getAll(Integer companyId);
	int insertEmployee(Employee employee, int id, int userId);
	int updateEmployee(Employee oldEmployee, Employee employee, int id, int userId);
	Employee findEmployeeByID( int id);
 
	 
public List<Employee> findEmployeeByFKId( int id);
public List<Employee> findEmployeeByFKSalesofficeid( int salesofficeid);
public List<Employee> findBookingOfficerByFKSalesofficeid( int salesofficeid);

public List<Employee> findSupervisorByFKSalesofficeid( int salesofficeid);
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
Employee findEmployeeByCode(String employeeCode);

}
