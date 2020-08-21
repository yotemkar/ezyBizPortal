package com.hssa.ezybiz.dao;

import java.util.Date;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;

public interface UserLoginDao {
	
	 User findByUserName(ServerSession serverSession, String userLoginName, String passsword);
	 boolean changeUserPassword(ServerSession serverSession, String userName, String userNewPassword, String userOldPassword);
	UserPassword findUserByID(int userId);
	int getPasswordExpiredTime(String paramName, ServerSession serverSession);
	void updateLastLoginTime(int userId,Date currentDate, ServerSession serverSession);
	void blockStatus(User frmUser, ServerSession serverSession);
	User findUserByLoginName(String userLoginName);
	

}
