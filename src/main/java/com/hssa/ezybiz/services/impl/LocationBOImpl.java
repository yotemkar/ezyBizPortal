package com.hssa.ezybiz.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.LocationDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Location;
import com.hssa.ezybiz.services.LocationBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.utils.SequenceConstants;

@Service
public class LocationBOImpl implements LocationBO{
 
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired
	SequencesBO sequencesBO;
	
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public int insertLocation(Location location, Integer userId){
		if(null == location.getId() || location.getId()==0){
			int locationId = sequencesBO.getNextValue(SequenceConstants.LOCATION_SEQUENCE);
			location.setId(locationId);
		}
		return locationDAO.insertLocation(location, location.getId(), userId);
	}

	@Override
	public Location findLocationByLocationId(int locationId) throws Exception {
		return locationDAO.findLocationByLocationId(locationId);
	}

	@Override
	public int updateLocation(Location location, int userId) throws Exception{
		Location oldLocation = findLocationByLocationId(location.getId());
		return locationDAO.updateLocation(oldLocation, location, location.getId(), userId);
	}

	@Override
	public String getUserDistrict(String userId) {
		return locationDAO.getUserDistrict(userId);
	}

	@Override
	public List<Contact> getCustomersContactByDistrict(String districtCode) {
		return locationDAO.getCustomersContactByDistrict(districtCode);
	}

}
