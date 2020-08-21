package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.LocationDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Location;
import com.hssa.ezybiz.utils.LoadJPAFQueries;


@Repository
public class LocationDAOImpl extends JdbcDaoSupport implements LocationDAO {

	@Autowired 
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	public int insertLocation(Location location, Integer id, Integer userId) {
		int k = getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Location"),
				new Object[] { location.getId(), location.getCreatedate(), location.getCreator(),
						location.getEntityUid(), location.getModifier(), location.getUpdatedate(),
						location.getOptLock(), location.getCity(), location.getCountry(), location.getPostalCode(),
						location.getStateProv(), location.getIsDeleted(), location.getDistrict() });
		
		return k;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Location findLocationByLocationId(int locationId) throws Exception {
		return (Location) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("SELECT_Location_BY_ID"), new Object[] { locationId },
				new BeanPropertyRowMapper(Location.class));
	}
	
	public int updateLocation(Location oldLocation, Location newLocation, int id, int userId)  throws Exception{
		
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Location_BY_ID"), new Object[] {
				newLocation.getCreatedate(),
				newLocation.getCreator(),
				newLocation.getEntityUid(),
				newLocation.getModifier(),
				newLocation.getUpdatedate(),
				newLocation.getOptLock(),
				newLocation.getCity(),
				newLocation.getCountry(),
				newLocation.getPostalCode(),
				newLocation.getStateProv(),
				newLocation.getIsDeleted(),
				newLocation.getId()
		});
	}
	
	@Override
	public String getUserDistrict(String userId) {
		return (String) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("GET_WAREHOUSE_DISTRICT"), new Object[] { userId }, String.class);
				//new BeanPropertyRowMapper<String>(String.class));
	}
	
	@Override
	public List<Contact> getContactsIdByEmployeeLocation(Integer userId){
		List<Contact> data = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_CONTACTS_BY_DISTRICT_EMPLOYEE"), new Object[] { userId },
				BeanPropertyRowMapper.newInstance(Contact.class));
		return data;
	}
	
	@Override
	public List<Contact> getCustomersContactByDistrict(String districtCode) {
		List<Contact> data = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("GET_CUSTOMERS_CONTACT_BY_DISTRICT"), new Object[] { districtCode },
				BeanPropertyRowMapper.newInstance(Contact.class));
		return data;
	}
}
