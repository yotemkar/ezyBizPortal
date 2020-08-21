package com.hssa.ezybiz.services.impl;

import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.CustomeruserDAO;
import com.hssa.ezybiz.dto.AdditionalUserDTO;
import com.hssa.ezybiz.dto.Customeruser;
import com.hssa.ezybiz.services.CustomeruserBO;
import com.hssa.ezybiz.services.SequencesBO;

@Service
public class CustomeruserBOImpl implements CustomeruserBO {
	
	@Autowired
	private CustomeruserDAO customeruserDAO;
	@Autowired
	private SequencesBO sequencesBO;

	public CustomeruserBOImpl() {
		super();
	}

	public SequencesBO getSequencesBO() {
		return sequencesBO;
	}

	public void setSequencesBO(SequencesBO SequencesBO) {
		this.sequencesBO = SequencesBO;
	}

	public CustomeruserDAO getCustomeruserDAO() {
		return customeruserDAO;
	}

	public void setCustomeruserDAO(CustomeruserDAO customeruserDAO) {
		this.customeruserDAO = customeruserDAO;
	}

	public List<Customeruser> search(List<Object> paramList) {
		return customeruserDAO.search(paramList);
	}

	public List<Customeruser> getAllCustomeruserList() {
		return customeruserDAO.getAll();
	}

	public int insertCustomeruser(Customeruser customeruser, int userId) {
		return customeruserDAO.insertCustomeruser(customeruser, customeruser.getId(), userId);
	}

	public int updateCustomeruser(Customeruser customeruser, int userId) {
		Customeruser oldCustomeruser = findCustomeruserByID(customeruser.getId());
		return customeruserDAO.updateCustomeruser(oldCustomeruser, customeruser, customeruser.getId(), userId);
	}

	public Customeruser findCustomeruserByID(int id) {
		return customeruserDAO.findCustomeruserByID(id);
	}
	public List<Customeruser> findCustomeruserByFKId(int id) {
		return customeruserDAO.findCustomeruserByFKId(id);
	}

	public List<Customeruser> findCustomeruserByFKCustomerid(int customerid) {
		return customeruserDAO.findCustomeruserByFKCustomerid(customerid);
	}

	public List<Customeruser> findCustomeruserByFKSpouseid(int spouseid) {
		return customeruserDAO.findCustomeruserByFKSpouseid(spouseid);
	}

	@Override
	public int getCustomerUserCount(String code) {
		return customeruserDAO.getCustomerUserCount(code);
	}

	@Override
	public List<AdditionalUserDTO> getPendingAdditionalUserForAdmin() {
		return customeruserDAO.getPendingAdditionalUserForAdmin();
	}

	@Override
	public List<AdditionalUserDTO> getPendingAdditionalUserForEmployee(int userId) {
		return customeruserDAO.getPendingAdditionalUserForEmployee(userId);
	}

	@Override
	public int approveAdditionalUser(AdditionalUserDTO additionalUserDTO, int userId) {
		Customeruser oldCustomeruser = findCustomeruserByID(additionalUserDTO.getUserId());
		Customeruser customerUser = (Customeruser)SerializationUtils.clone(oldCustomeruser);
		customerUser.setIsPending(0);
		return customeruserDAO.updateCustomeruser(oldCustomeruser, customerUser, customerUser.getId(), userId);
	}

	@Override
	public int rejectAdditionalUser(AdditionalUserDTO additionalUserDTO, int userId) {
		Customeruser oldCustomeruser = findCustomeruserByID(additionalUserDTO.getUserId());
		Customeruser customerUser = (Customeruser)SerializationUtils.clone(oldCustomeruser);
		customerUser.setIsDeleted(1);
		return customeruserDAO.updateCustomeruser(oldCustomeruser, customerUser, customerUser.getId(), userId);
	}

	@Override
	public int updateCustomeruserPosition(Integer id, Integer positionId) {
		return customeruserDAO.updateCustomeruserPosition(id, positionId);
	}

	@Override
	public boolean checkCustomerUserAssociate(Integer customerId, Integer userId) {
		String position =  customeruserDAO.findPostionForCustomerUser(customerId, userId);
		return !position.equalsIgnoreCase("owner");
	}
}
