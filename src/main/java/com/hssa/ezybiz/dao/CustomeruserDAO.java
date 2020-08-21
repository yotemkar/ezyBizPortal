package com.hssa.ezybiz.dao;

import java.util.List;

import com.hssa.ezybiz.dto.AdditionalUserDTO;
import com.hssa.ezybiz.dto.Customeruser;

public interface CustomeruserDAO {
	List<Customeruser> search(List<Object> paramList);

	List<Customeruser> getAll();

	int insertCustomeruser(Customeruser customeruser, int id, int userId);

	int updateCustomeruser(Customeruser oldCustomeruser, Customeruser newCustomeruser, int id, int userId);
	
	/**
	 * @param id
	 * @return
	 */
	Customeruser findCustomeruserByID(int id);

	public List<Customeruser> findCustomeruserByFKId(int id);

	public List<Customeruser> findCustomeruserByFKCustomerid(int customerid);

	public List<Customeruser> findCustomeruserByFKSpouseid(int spouseid);

	public int getCustomerUserCount(String code);

	public List<AdditionalUserDTO> getPendingAdditionalUserForAdmin();

	List<AdditionalUserDTO> getPendingAdditionalUserForEmployee(int userId);

	int updateCustomeruserPosition(Integer id, Integer positionId);

	String findPostionForCustomerUser(Integer customerId, Integer userId);

}
