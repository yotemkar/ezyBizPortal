package com.hssa.ezybiz.services;
 
import java.util.List;

import com.hssa.ezybiz.dto.AdditionalUserDTO;
import com.hssa.ezybiz.dto.Customeruser;
public interface CustomeruserBO {
	/**
	 * Returns the list of customeruser as per parameters contain in paramList. 
	 * <p>
	 * Any developer added fields need to be taken care of by developer  
	 * The lookup descriptions and FK descriptions are also fetched in VO. 
	 * <br/>
	 *
	 * @param  serverSession  argument having the information of current 
	 * sessionID, userID, module, subModule
	 * @param  paramList arguments having the zero or more argument with same or different datatypes.
	 * <br/>   The type of paramList values will be identify in getSearchableTokens method of DaoHelper class.
	 * @return      list of customeruser of type Base
	 * @see         getAllCustomeruserList
	 */	
	List<Customeruser> search(List<Object> paramList);	
	/**
	 * This method returns all customeruser. 
	 * <br/>
	 * The lookup descriptions and FK descriptions are also fetched in VO. 
	 * <p>
	 * Any developer added fields need to be taken care of by developer  
	 *
	 * @see         search
	 */	
	List<Customeruser> getAllCustomeruserList();
	/**
	 * Returns 1 if value inserted successfully. 
	 * <p>
	 * This method always returns immediately, 
	 * When this method called the the object values inserted into customeruser table. The graphics primitives 
	 * that show all rows on the screen with new one.
	 *
	 * @param  customeruser arguments having the current inserted values  
	 * @param userId 
	 * @param  userId argument is a current login user id use to set createdBy field  
	 * @return      1 if values inserted successfully otherwise return 0.
	 * @see         getNextValue method of Sequence class
	 */	
	int insertCustomeruser( Customeruser customeruser, int userId);
	
	/**
	 * Returns 1 if value updated successfully. 
	 * <p>
	 * This method always returns immediately, 
	 * When this method called the the object values updated into customeruser table. The graphics primitives 
	 * that show all rows on the screen with new updated one.
	 *
	 * @param  customeruser arguments having the current inserted values  
	 * @param  userId argument is a current login user id use to set updatedBy field  
	 * @return      1 if values updated successfully otherwise return 0.
	 * @see         nothing
	 */	
	int updateCustomeruser( Customeruser customeruser, int userId);
	/**
	 * Returns only single row based on id. 
	 * <p>
	 * This method always returns immediately, 
	 * When this method called the the current id values loaded in customeruser object.
	 * The graphics primitives show other fields based on that id from same row. 
	 *
	 * @param  id argument is a current selected row id that use to fetch other related data of same row 
	 * @return      Employees object
	 * @see         nothing
	 */	
	Customeruser findCustomeruserByID(int id);
public List<Customeruser> findCustomeruserByFKId( int id);
public List<Customeruser> findCustomeruserByFKCustomerid( int customerid);
public List<Customeruser> findCustomeruserByFKSpouseid( int spouseid);
/**
 * check how many user exists in for customer
 * @param code
 * @return
 */
public int getCustomerUserCount(String code);
public List<AdditionalUserDTO> getPendingAdditionalUserForAdmin();
public List<AdditionalUserDTO> getPendingAdditionalUserForEmployee(int userId);
public int approveAdditionalUser(AdditionalUserDTO additionalUserDTO, int userId);
public int rejectAdditionalUser(AdditionalUserDTO additionalUserDTO, int userId);
int updateCustomeruserPosition(Integer id, Integer positionId);
boolean checkCustomerUserAssociate(Integer customerId,Integer userId);
}
