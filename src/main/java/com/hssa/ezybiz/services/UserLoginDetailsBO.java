package com.hssa.ezybiz.services;



import java.sql.Timestamp;

import com.hssa.ezybiz.dto.UserLoginDetails;

public interface UserLoginDetailsBO {

	UserLoginDetails getUserLoginDetailsByUserId(int userId);

	int getUserLoginDetailsCountByUserId(int userId);

	void updateUserLoginDetails(UserLoginDetails userLoginDetails, UserLoginDetails previousUserLoginDetails,int id, int userId);

	void saveUserLoginDetails(UserLoginDetails userLoginDetails);

	Timestamp getUserLastLoginTimeByUserId(Integer userId);
	
	void unlockAllUserLogin();

}
