package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Location;
 
public interface LocationDAO 
{
	/**
	 * Method to insert location.
	 * @param location
	 * @param userId 
	 * @param id 
	 * @return
	 */
	int insertLocation(Location location, Integer id, Integer userId);
	/**
	 * find location by locationId
	 * @param locationId
	 * @return
	 * @throws Exception
	 */
	Location findLocationByLocationId(int locationId) throws Exception;
	/**
	 * to update Location
	 * @param oldLocation for audit trail
	 * @param newLocation 
	 * @param id for audit trail
	 * @param userId for audit trail
	 * @return
	 */
	int updateLocation(Location oldLocation, Location newLocation, int id, int userId) throws Exception;
	
	String getUserDistrict(String userId);
	 
	List<Contact> getContactsIdByEmployeeLocation(Integer userId);
	
	List<Contact> getCustomersContactByDistrict(String districtCode);
}
