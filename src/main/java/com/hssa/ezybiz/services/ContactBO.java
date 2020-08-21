package com.hssa.ezybiz.services;

import com.hssa.ezybiz.dto.Contact;

public interface ContactBO {

	/**
	 * Returns 1 if value inserted successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values inserted into contact table. The graphics primitives that
	 * show all rows on the screen with new one.
	 *
	 * @param contact
	 *            arguments having the current inserted values
	 * @param userId 
	 * @param userId
	 *            argument is a current login user id use to set createdBy field
	 * @return 1 if values inserted successfully otherwise return 0.
	 * @see getNextValue method of frmSequence class
	 */
	int insertContact(Contact contact, int userId);

	/**
	 * Returns 1 if value updated successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values updated into contact table. The graphics primitives that
	 * show all rows on the screen with new updated one.
	 *
	 * @param contact
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set updatedBy field
	 * @return 1 if values updated successfully otherwise return 0.
	 * @see nothing
	 */
	int updateContact(Contact contact, int userId);

	/**
	 * Returns only single row based on id.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * current id values loaded in contact object. The graphics primitives show
	 * other fields based on that id from same row.
	 *
	 * @param id
	 *            argument is a current selected row id that use to fetch other
	 *            related data of same row
	 * @return Employees object
	 * @see nothing
	 */
	Contact findContactByID(int id);

	/**
	 * to check email Id exists in system
	 * 
	 * @param emailId
	 * @return
	 */
	public int isEmailIdExist(String emailId );// modified by Ravindra to check companyid

	public int isEmailIdExistForUser(String userEmialId, Integer customerUserId);
	
	
	String getEmailIdByCustomerId(Integer customerId);

	String getEmailIdByUserId(Integer userId);

	String getPhoneNumberByCustomerId(Integer customerId);

	String getPhoneNumberByUserId(Integer userId);
	
	boolean isMobileExistForCustomer(String mobileNumber, Integer companyId);// modifed by Ravindra to check companyid

	boolean isMobileExistForUser(String mobileNumber, Integer companyId); // modifed by Ravindra to check companyid
	
	boolean isEmailExistForUser(String emailId, Integer companyId); // modified by ravindra  to check companyid

}
