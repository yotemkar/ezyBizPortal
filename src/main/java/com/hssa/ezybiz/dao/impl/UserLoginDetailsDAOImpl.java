package com.hssa.ezybiz.dao.impl;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.dao.UserLoginDetailsDAO;
import com.hssa.ezybiz.dto.UserLoginDetails;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class UserLoginDetailsDAOImpl extends JdbcDaoSupport implements UserLoginDetailsDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public UserLoginDetails getUserLoginDetailsByUserId(int userId) {
		return (UserLoginDetails)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_USER_LOGIN_DETAILS_BY_USER_ID"), 
				new Object[] {userId}, new BeanPropertyRowMapper<UserLoginDetails>(UserLoginDetails.class));
	
	}

	@Override
	public int getUserLoginDetailsCountByUserId(int userId) {
		return (Integer)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_ENTRY_COUNT_FOR_USER_ID"), new Object[] {userId}, Integer.class);
	}

	@Override
	@Transactional
	public void updateUserLoginDetails(UserLoginDetails userLoginDetails, UserLoginDetails previousUserLoginDetails,int id, int userId) {
			 getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_USER_LOGIN_DETAILS"), new Object[] {
					userLoginDetails.getLastLogInSuccess(),
					userLoginDetails.getLastLogInFailure(),
					userLoginDetails.getWrongPasswordAttempts(),
					Boolean.TRUE.equals(userLoginDetails.getActive()) ? 1: 0,
					Boolean.TRUE.equals(userLoginDetails.getLocked()) ? 1: 0,
					userLoginDetails.getSource(),
					Boolean.TRUE.equals(userLoginDetails.getPasswordChanged()) ? 1: 0,
					userLoginDetails.getUserId(),
			});
	}

	@Override
	@Transactional
	public void saveUserLoginDetails(UserLoginDetails userLoginDetails) {
			getJdbcTemplate().update( LoadJPAFQueries.getQueryById("SAVE_USER_LOGIN_DETAILS"), 
					new Object[] { 
							userLoginDetails.getId(),
							userLoginDetails.getUserId(),
							userLoginDetails.getLastLogInSuccess(),
							userLoginDetails.getLastLogInFailure(),
							userLoginDetails.getWrongPasswordAttempts(),
							Boolean.TRUE.equals(userLoginDetails.getActive()) ? 1 : 0,
							Boolean.TRUE.equals(userLoginDetails.getLocked()) ? 1 : 0,
							userLoginDetails.getSource(),
							Boolean.TRUE.equals(userLoginDetails.getPasswordChanged()) ? 1 : 0,});
		}

	@Override
	public Timestamp getUserLastLoginTimeByUserId(Integer userId) {
		return (Timestamp)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("GET_USER_LAST_LOGIN_TIME_BY_USER_ID"), 
				new Object[] {userId}, Timestamp.class);
	}

	@Override
	public void unlockAllUserLogin() {
		getJdbcTemplate().update( LoadJPAFQueries.getQueryById("UNLOCK_ALL_USERS"));		
	}	
}
