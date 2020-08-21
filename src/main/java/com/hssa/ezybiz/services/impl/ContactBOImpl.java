package com.hssa.ezybiz.services.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.ContactDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.utils.SequenceConstants;


@Service
public class ContactBOImpl implements ContactBO {

	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private SequencesBO sequencesBO;

	public ContactBOImpl() {
		super();
	}

	public int insertContact(Contact contact, int userId) {

		contact.setId(sequencesBO.getNextValue(SequenceConstants.CONTACT_SEQUENCE));
		if (contact.getCreator() == null || contact.getCreator().isEmpty()) {
			contact.setCreator(Integer.toString(userId));
			contact.setModifier(Integer.toString(userId));
			contact.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
			contact.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		}
		contact.setEntityUid(UUID.randomUUID().toString());

		return contactDAO.insertContact(contact, contact.getId(), userId);
	}

	public int updateContact(Contact contact, int userId) {
		Contact oldContact = findContactByID(contact.getId());
		contact.setModifier(String.valueOf(userId));
		return contactDAO.updateContact(oldContact, contact, contact.getId(), userId);
	}

	public Contact findContactByID(int id) {
		return contactDAO.findContactByID(id);
	}

	@Override
	public int isEmailIdExist(String emailId) {
		return contactDAO.isEmailIdExist(emailId);
	}
	
	@Override
	public int isEmailIdExistForUser(String userEmialId, Integer customerUserId){
		return contactDAO.isEmailIdExistForUser(userEmialId, customerUserId);
	}

	@Override
	public String getEmailIdByCustomerId(Integer customerId) {
		return contactDAO.getEmailIdByCustomerId(customerId);
	}

	@Override
	public String getEmailIdByUserId(Integer userId) {
		return contactDAO.getEmailIdByUserId(userId);
	}

	@Override
	public String getPhoneNumberByCustomerId(Integer customerId) {
		return contactDAO.getPhoneNumberByCustomerId(customerId);
	}

	@Override
	public String getPhoneNumberByUserId(Integer userId) {
		return contactDAO.getPhoneNumberByUserId(userId);
	}
	
	// Start Mofified by ravindra to check companyid
	@Override
	public boolean isMobileExistForCustomer(String mobileNumber, Integer companyId) {
		return contactDAO.isMobileExistForCustomer(mobileNumber,companyId);
	}

	@Override
	public boolean isMobileExistForUser(String mobileNumber,Integer companyId) {
		return contactDAO.isMobileExistForUser(mobileNumber,companyId);
	}

	@Override
	public boolean isEmailExistForUser(String emailId, Integer companyId) {
		return contactDAO.isEmailExistForUser(emailId,companyId);
	}
	// End Mofified by ravindra to check companyid
}
