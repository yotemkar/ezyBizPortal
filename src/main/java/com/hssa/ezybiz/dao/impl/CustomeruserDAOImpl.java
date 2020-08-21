package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.CustomeruserDAO;
import com.hssa.ezybiz.dto.AdditionalUserDTO;
import com.hssa.ezybiz.dto.Customeruser;
import com.hssa.ezybiz.utils.DaoHelper;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class CustomeruserDAOImpl extends JdbcDaoSupport implements CustomeruserDAO {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Customeruser> search(List<Object> paramList) {

		Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);

		return (List) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Customeruser"),
				objArrSearchableTokens, BeanPropertyRowMapper.newInstance(Customeruser.class));

	}

	public List<Customeruser> getAll() {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Customeruser"),
				BeanPropertyRowMapper.newInstance(Customeruser.class));

	}

	public int insertCustomeruser(Customeruser customeruser, int id, int userId) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Customeruser"),
				new Object[] { customeruser.getAccesskey(), customeruser.getJobtxt(), customeruser.getMarried(),
						customeruser.getOrgtxt(), customeruser.getOrgunit(), customeruser.getPosition(),
						customeruser.getUserfunction(), customeruser.getWeddinganniversary(), customeruser.getId(),
						customeruser.getCustomerid(), customeruser.getSpouseId(), customeruser.getIsDeleted(),customeruser.getIsAdditionalUser(), customeruser.getIsPending() });
	}
	
	public int updateCustomeruser(Customeruser oldCustomeruser, Customeruser newCustomeruser, int id, int userId) {

		int k =  getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Customeruser_BY_ID"),
				new Object[] {  newCustomeruser.getMarried(), newCustomeruser.getPosition(), 
						newCustomeruser.getUserfunction(), newCustomeruser.getWeddinganniversary(),
						newCustomeruser.getSpouseId(), newCustomeruser.getIsDeleted(),newCustomeruser.getIsPending(), newCustomeruser.getId()});
		
		return k;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Customeruser findCustomeruserByID(int id) {
		return (Customeruser) getJdbcTemplate().queryForObject(
				LoadJPAFQueries.getQueryById("SELECT_Customeruser_BY_ID"), new Object[] { id },
				new BeanPropertyRowMapper(Customeruser.class));
	}

	public List<Customeruser> findCustomeruserByFKId(int id) {
		return (List<Customeruser>) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Customeruser_By_FKId"),
				new Object[] { id }, BeanPropertyRowMapper.newInstance(Customeruser.class));
	}

	public List<Customeruser> findCustomeruserByFKCustomerid(int customerid) {
		return (List<Customeruser>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("SELECT_Customeruser_By_FKCustomerid"), new Object[] { customerid },
				BeanPropertyRowMapper.newInstance(Customeruser.class));
	}

	public List<Customeruser> findCustomeruserByFKSpouseid(int spouseid) {
		return (List<Customeruser>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("SELECT_Customeruser_By_FKSpouseid"), new Object[] { spouseid },
				BeanPropertyRowMapper.newInstance(Customeruser.class));
	}

	@Override
	public int getCustomerUserCount(String code) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_CustomerUserCount"),
				Integer.class, new Object[]{code});
	}

	@Override
	public List<AdditionalUserDTO> getPendingAdditionalUserForAdmin() {
		return (List<AdditionalUserDTO>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("get_PendingAdditionalUser_For_Admin"), new Object[] {1},
				BeanPropertyRowMapper.newInstance(AdditionalUserDTO.class));
	}

	@Override
	public List<AdditionalUserDTO> getPendingAdditionalUserForEmployee(int userId) {
		return (List<AdditionalUserDTO>) getJdbcTemplate().query(
				LoadJPAFQueries.getQueryById("get_PendingAdditionalUser_For_Employee"), new Object[] {1,userId},
				BeanPropertyRowMapper.newInstance(AdditionalUserDTO.class));
	}

	@Override
	public int updateCustomeruserPosition(Integer id, Integer positionId) {
		return  getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_CUSTOMER_USER_POSITION"),
				new Object[] {positionId,id});
	}

	@Override
	public String findPostionForCustomerUser(Integer customerId, Integer userId) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("FIND_POSTION_FOR_CUSTOMER_USER"),
				String.class, new Object[]{customerId,userId});
	}

	
}
