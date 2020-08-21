package com.hssa.ezybiz.services;
 
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import org.springframework.dao.EmptyResultDataAccessException;

import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.CustomerDetails;
import com.hssa.ezybiz.dto.CustomerUserDto;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.Quotation;
import com.hssa.ezybiz.dto.UserProfile;


public interface CustomerBO {
	

	public List<Customer> search(Customer customer);	
		
	public List<Customer> getAllCustomerList(Integer companyId);
		
	public Map<String, String> updateCustomer(Customer customer, Integer userId, boolean salesPromoterUpdateFlag);

	public Customer findCustomerByID(Integer id);
 	
	public Customer findCustomerByCustomerCodeAndAccessKey(String code, Integer companyId) throws EmptyResultDataAccessException;;

	public List<Customer> getCustomerListforEmployee(int employeeId);

	public Customer findCustomerByUserId(int userId);
	/**
	 * get User Profile Detail 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserProfile getUserProfileDetail(int userId) throws Exception;
	/**
	 * check if customerCode is already exist in system
	 * @param code
	 * @throws Exception
	 */
	public boolean isCustomerCodeExist(String code, Integer companyId) throws Exception;
	
	boolean isDelcrederCodeExist(String code, Integer companyId) throws Exception;
	
	/**
	 * to Upload Customer details from Excel
	 * @param file
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> uploadCustomer(File file, int userId, Company company, boolean isSalespromotor) throws Exception;
	
	public Map<String, Object> uploadDelcreder(File file, int userId, Company company) throws Exception;

	public List<Igimage> getPageImages(String pageName,Integer companyId);

	public String getPageContent(String pageName, Integer companyId);

	public List<Customer> getAllFilteredCustomerByEmoployeeId(Customer customer);
	
	public List<Customer> getAllFilteredCustomerByDelcrederId(Customer customer);

	public int sendAccesskey(Customer customer, Integer userId, Company company);

	List<CustomerUserDto> getAllCustomerUsers(Integer userId);

	List<Map<String, Object>> getCustomerDetailsByUserId(int userId);
	
	Map<String, String> getActiveInactiveCustomersCount(Integer companyId);
	
	Map<String, String> getActiveInactiveCustomersCountByLoginDuration(Integer companyId);
	 
	Map<String, String> getRegisteredUsersCount(Integer companyId);
	
	String getCustomerLockedCount(Integer companyId);
	
	Long getCustomersCurrentDayLoginCount(Integer companyId);
	
	List<Customer> getActiveInactiveCustomersListForDashboard(Integer companyId, Integer activeFlag);
	
	List<Customer> getActiveCustomersByDuration(Integer companyId);
	
	List<Customer> getInActiveCustomersByDuration(Integer companyId);
	
	List<Customer> getAllCustomersListForDashboard(Integer companyId);
	
	List<Customer> getLockedCustomersList(Integer companyId);
	
	List<Customer> getRegisteredCustomersList(Integer companyId);
	
	List<Customer> getNonRegisteredCustomersList(Integer companyId);	
	
	List<Customer> getCustomersByCurrentDayLogin(Integer companyId);

	int updateCustomerDistrict(CustomerDetails customer);
	
	public Customer findCustomerByCode(String customerCode);
	
	//for updating customer details from stg tables

	//void updateCustomerList();

	public void updateCustomerSalesOfficeMappingList();
	public void updateCustomer(Map<String, Object> obj) throws Exception;

	public void updatecustomerMasterSTG(int id,int value,String comment);
	public List<Customer> searchForRegisteration(String customerCode);

	Map<String, Content> getLoginPageContent();

	public List<Quotation> getQuotationListForCustomer(String customerCode,String salesOrg);

	
}
