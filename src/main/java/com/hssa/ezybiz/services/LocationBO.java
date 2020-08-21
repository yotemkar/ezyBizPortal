package com.hssa.ezybiz.services;
 
import java.util.List;

import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Location;

/**
 * 
 * @author KPIT
 *
 */
public interface LocationBO {

	
	/**
	 * Returns 1 if value inserted successfully. 
	 * <p>
	 * This method always returns immediately, 
	 * When this method called the the object values inserted into location table. The graphics primitives 
	 * that show all rows on the screen with new one.
	 *
	 * @param  serverSession  argument having the information of current 
	 * sessionID, userID, module, subModule
	 * @param  location arguments having the current inserted values  
	 * @param userId 
	 * @param  userId argument is a current login user id use to set createdBy field  
	 * @return      1 if values inserted successfully otherwise return 0.
	 * @see         getNextValue method of frmSequence class
	 */	
	int insertLocation(Location location, Integer userId);
	/**
	 * find location by locationId
	 * @param locationId
	 * @return
	 * @throws Exception
	 */
	Location findLocationByLocationId(int locationId) throws Exception;
	/**
	 * to update Location
	 * @param location
	 * @param userId
	 * @return
	 */
	int updateLocation(Location location, int userId)  throws Exception;
	
	String getUserDistrict(String userId);
	
	List<Contact> getCustomersContactByDistrict(String districtCode);
}
