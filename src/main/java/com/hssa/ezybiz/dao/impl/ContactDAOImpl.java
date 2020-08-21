package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.ContactDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.utils.DaoHelper;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class ContactDAOImpl extends JdbcDaoSupport implements ContactDAO {
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Contact> search(List<Object> paramList) {

		Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);

		return (List) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Contact"), objArrSearchableTokens,
				BeanPropertyRowMapper.newInstance(Contact.class));

	}

	public List<Contact> getAll() {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Contact"),
				BeanPropertyRowMapper.newInstance(Contact.class));

	}

	public int insertContact(Contact contact, int id, int userId) {

		int k =  getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Contact"),
				new Object[] { contact.getId(), contact.getCreatedate(), contact.getCreator(), contact.getEntityUid(),
						contact.getModifier(), contact.getUpdatedate(), contact.getOptlock(), contact.getEmail(),
						contact.getExtension(), contact.getFax(), contact.getLandline(), contact.getMobile(),
						contact.getName(), contact.getUri(), contact.getAddressid(), contact.getIsdeleted(), });
		return k;
	}

	public int deleteContact(Contact contact) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_Contact_BY_ID"), new Object[] { contact.getId() });
	}

	public int updateContact(Contact oldContact, Contact newContact, int id, int userId) {
		

		int k = getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Contact_BY_ID"),
				new Object[] {newContact.getEntityUid(),
						newContact.getModifier(), newContact.getUpdatedate(), newContact.getOptlock(), newContact.getEmail(),
						newContact.getExtension(), newContact.getFax(), newContact.getLandline(), newContact.getMobile(),
						newContact.getName(), newContact.getUri(), newContact.getAddressid(), newContact.getIsdeleted(),
						newContact.getId() });
		return k;
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Contact findContactByID(int id) {
		return (Contact) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Contact_BY_ID"), new Object[] { id },
				new BeanPropertyRowMapper(Contact.class));
	}

	// Finder methods for ForeignKey fields
	public List<Contact> findContactByFKAddressid(int addressid) {
		return (List<Contact>) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Contact_By_FKAddressid"),
				new Object[] { addressid }, BeanPropertyRowMapper.newInstance(Contact.class));
	}

	
	@Override
	public int isEmailIdExist(String emailId) {// Modified by Ravindra to add company code in query
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Validate_EmailIdExist"),
				new Object[]{emailId}, Integer.class);
	}
	
	@Override
	public int isEmailIdExistForUser(String userEmialId, Integer customerUserId){
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Validate_EmailIdExistForUser"),
				new Object[]{userEmialId, customerUserId}, Integer.class);
	}
	

	@Override
	public String getEmailIdByCustomerId(Integer customerId) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_EMAIL_ID_BY_CUSTOMER_ID"),
				new Object[]{customerId}, String.class);
	}
	
	
	@Override
	public String getEmailIdByUserId(Integer userId) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_EMAIL_ID_BY_USER_ID"),
				new Object[]{userId}, String.class);
	}


	@Override
	public String getPhoneNumberByCustomerId(Integer customerId) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_PHONE_NUMBER_BY_CUSTOMER_ID"),
				new Object[]{customerId}, String.class);
	}


	@Override
	public String getPhoneNumberByUserId(Integer userId) {
		return (String)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_PHONE_NUMBER_BY_USER_ID"),
				new Object[]{userId}, String.class);
	}
	
	@Override
	public boolean isMobileExistForCustomer(String mobileNumber, Integer companyId) { // Modified by Ravindar to add companyid
		List<Customer> customers = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("IS_MOBILE_FOR_CUSTOMER_EXIST"),
				new Object[] { mobileNumber,companyId }, BeanPropertyRowMapper.newInstance(Customer.class));
		if(customers.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isMobileExistForUser(String mobileNumber, Integer companyId) {// Modified by Ravindar to add companyid
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Validate_MobileExistForUser"),
				new Object[]{mobileNumber,companyId}, Integer.class);
		if(count > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isEmailExistForUser(String emailId, Integer companyId) { // Modified by Ravindar to add companyid
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Validate_EmailExistForUser"),
				new Object[]{emailId,companyId}, Integer.class);
		if(count > 0)
			return true;
		else
			return false;
	}
}
