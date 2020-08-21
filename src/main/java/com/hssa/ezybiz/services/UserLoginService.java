package com.hssa.ezybiz.services;

import java.util.Date;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;

public interface UserLoginService {
	
	User findUserByName(ServerSession serverSession, String userLoginName, String passsword);
	boolean changeUserPassword(ServerSession serverSession, String userName, String userNewPassword, String oldPassword);
	UserPassword findUserByID(int userId);
	int getPasswordExpiredTime(String paramName, ServerSession serverSession);
	void updateLastLoginTime(int userId, Date date, ServerSession serverSession);
	void blockStatus(User frmUser, ServerSession serverSession);
	
	boolean validateUserByPassword(User user,String password);
	User findUserByLoginName(String userLoginName);

}
