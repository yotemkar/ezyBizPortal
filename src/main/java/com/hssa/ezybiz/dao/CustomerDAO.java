package com.hssa.ezybiz.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.CustomerDetails;
import com.hssa.ezybiz.dto.CustomerUserDto;
import com.hssa.ezybiz.dto.UserProfile;
public interface CustomerDAO {

	public List<Customer> search(Customer customer);

	public List<Customer> getAll(Integer companyId);

	public int updateCustomer(Customer oldCustomer, Customer customer, int id, int userId, boolean salesPromoterUpdateFlag);

	public Customer findCustomerByID(int id);

	public Customer findCustomerByCustomerCodeAndAccessKey(String code, Integer companyId);

	public List<Customer> getCustomerListforEmployee(int employeeId);

	public Customer findCustomerByUserId(int userId);

	public UserProfile getUserProfileDetail(int userId) throws Exception;

	/**
	 * check if customerCode is already exist in system
	 * 
	 * @param code
	 * @throws Exception
	 */
	public boolean isCustomerCodeExist(String code, Integer companyId) throws Exception;

	public boolean isDelcrederCodeExist(String code, Integer companyId) throws Exception;

	/**
	 * insert customer
	 * 
	 * @param customer
	 * @param id
	 * @param userId
	 * @return
	 */
	public int insertCustomer(Customer customer, int id, int userId);

	public List<Customer> getAllFilteredCustomerByEmoployeeId(Customer customer);

	public List<Customer> getAllFilteredCustomerByDelcrederId(Customer customer);

	public int updateCustomerAccessKey(Customer oldCustomer, Customer customer, int id, Integer userId);

	List<CustomerUserDto> getAllCustomerUsers(Integer userId);

	public String findCustomerCodeByID(Integer customerId);

	// Added by Mohsin for RetailPos
	String findCustomerMobileByID(Integer customerId);

	List<Map<String, Object>> getCustomerDetailsByUserId(int userId);

	Customer getCustomerCompanyIdByMobile(String mobileNumber);

	// Added by Mohsin for Admin dashboard reports
	String getActiveCustomersCount(Integer companyId);

	String getInactiveCustomersCount(Integer companyId);

	String getActiveCustomerCountByLoginDuration(Integer companyId);

	String getInActiveCustomerCountByLoginDuration(Integer companyId);

	Long getCustomersCount(Integer companyId);

	Long getRegisteredCustomersCount(Integer companyId);

	Long getCustomerLockedCount(Integer companyId);

	Long getCustomersCurrentDayLoginCount(Integer companyId, LocalDate date);

	List<Customer> getActiveCustomersByDuration(Integer companyId);

	List<Customer> getInActiveCustomersByDuration(Integer companyId);

	List<Customer> getActiveInactiveCustomersListForDashboard(Integer companyId, Integer activeFlag);

	List<Customer> getAllCustomersListForDashboard(Integer companyId);

	List<Customer> getLockedCustomersList(Integer companyId);

	List<Customer> getRegisteredCustomersList(Integer companyId);

	List<Customer> getNonRegisteredCustomersList(Integer companyId);

	List<Customer> getCustomersByCurrentDayLogin(Integer companyId, LocalDate date);

	public int updateCustomerDistrict(CustomerDetails customer);

	public Customer findCustomerByCode(String customerCode);

	void finddelcrederCustomerByIds(int delcrederId, int CustomerId);

	// for updating customer from staging tables
	List<Map<String, Object>> getAllCustomerStg();

	List<Map<String, Object>> getCustomerSalesGroupStg(String code);

	List<Map<String, Object>> getSalesGroupPlantStg();

	public List<Map<String, Object>> getSalesGroupPlantStgForGrpOrgOfc(String office, String org, String group);

	public Integer insertCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode,
			String groupCode, String plantCode);

	public void insertCustomerRepGroupMapping(int code, int repGroup, int creGroup);

	public void updateCustomerMasterIsProcessed(int id, int i,String commentr);

	public void updateSalesGrpPlantStgIsProcessed(int id, int i);

	public void updateCustomerSalesGrpStgIsProcessed(int id, int i);

	List<Map<String, Object>> getAllCustomerSalesGroupStg();

	public int updateCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode, String groupCode,
			String plantCode, int IsDelete);

	public int getCustomerIdByCode(String code);

	public int getCreditRepGroupIdByCode(String string);

	public int getCustomerCreditGroupIdByCode(String string);

	public List<Map<String, Object>> getCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode, String groupCode,
			String plantCode, int i);

	public List<Map<String, Object>> getAllCustomerSalesOfficePlantMapping(String code, String officeCode, String orgCode, String groupCode,
			String plantCode);

	public List<Map<String, Object>> getCustomerRepGroupMapping(int CUSTOMER_ID);

	public List<Map<String, Object>> getCustomerUserMapping(int CUSTOMER_ID);

	public void updateCustomerRepGroupMapping(int id, int creditRepGroupIdByCode, int customerCreditGroupIdByCode);

	public List<Customer> searchForRegisteration(String customerCode);

}
