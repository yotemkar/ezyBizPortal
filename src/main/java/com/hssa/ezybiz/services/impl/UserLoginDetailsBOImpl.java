package com.hssa.ezybiz.services.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.dao.UserLoginDetailsDAO;
import com.hssa.ezybiz.dto.UserLoginDetails;
import com.hssa.ezybiz.services.UserLoginDetailsBO;

@Service
public class UserLoginDetailsBOImpl implements UserLoginDetailsBO {

	@Autowired
	private UserLoginDetailsDAO userLogInDetailsDAO ;
	
	@Override
	public UserLoginDetails getUserLoginDetailsByUserId(int userId) {
		return userLogInDetailsDAO.getUserLoginDetailsByUserId(userId);
	}

	@Override
	public int getUserLoginDetailsCountByUserId(int userId) {
		return userLogInDetailsDAO.getUserLoginDetailsCountByUserId(userId);
	}

	@Override
	@Async
	@Transactional
	public void updateUserLoginDetails(UserLoginDetails userLoginDetails, UserLoginDetails previousUserLoginDetails,int id, int userId) {
		 userLogInDetailsDAO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails, id, userId);
	}

	@Override
	@Async
	@Transactional
	public void saveUserLoginDetails(UserLoginDetails userLoginDetails) {
		 userLogInDetailsDAO.saveUserLoginDetails(userLoginDetails);
	}

	@Override
	public Timestamp getUserLastLoginTimeByUserId(Integer userId) {
		return  userLogInDetailsDAO.getUserLastLoginTimeByUserId(userId);
	}

	@Override
	public void unlockAllUserLogin() {
		userLogInDetailsDAO.unlockAllUserLogin();	
	}

}
