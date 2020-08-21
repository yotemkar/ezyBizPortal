package com.hssa.ezybiz.dao;

import java.util.List;

import com.hssa.ezybiz.dto.Contact;

public interface ContactDAO {

	List<Contact> search( List<Object> paramList);

	List<Contact> getAll();

	int insertContact(Contact contact, int id, int userId);

	int deleteContact(Contact contact);

	int updateContact(Contact oldContact, Contact newContact, int id, int userId);

	Contact findContactByID(int id);

	public int isEmailIdExist(String emailId);// Modified by Ravindra to check companyid

	public int isEmailIdExistForUser(String userEmialId, Integer customerUserId);

	public List<Contact> findContactByFKAddressid( int addressid);

	String getEmailIdByCustomerId(Integer customerId);

	String getEmailIdByUserId(Integer userId);

	String getPhoneNumberByCustomerId(Integer customerId);

	String getPhoneNumberByUserId(Integer userId);
	
	boolean isMobileExistForCustomer(String mobileNumber, Integer companyId);// Modified by Ravindra to check companyid
	
	boolean isMobileExistForUser(String mobileNumber, Integer companyId);// Modified by Ravindra to check companyid
	
	boolean isEmailExistForUser(String emailId, Integer companyId);// Modified by Ravindra to check companyid

}
