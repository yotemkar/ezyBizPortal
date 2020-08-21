/**
 * 
 */
package com.hssa.ezybiz.services;
import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;

 
public interface UserPasswordBO {
 
	 void addUserPassword(ServerSession serverSession, UserPassword userPasswordVO);
	 List<UserPassword> getUserPasswords(ServerSession serverSession);
	 UserPassword getUserPasswordByUserId(int userId);
	 void delete(ServerSession serverSession, UserPassword userPasswordVO);
	
	 void update(ServerSession serverSession, UserPassword userPasswordVO);
	 boolean changeUserPassword(UserPassword passwordVo);
	 int getNextId(ServerSession serverSession);
	void insert(UserPassword passwordVo, int userId);
	int getPasswordAttempts(String param);
	void updateAttempts(User frmUser, int userId);
	void updateAttemptsToZero(User frmUser, int userId);
	
	//to be removed
	/*int forgetPassword(ServerSession serverSession, User userVO,
			SecurityUserQuestion securityUserQuestionVO);*/
	
	//deepak
	Map<String,Object> forgotPassword(String emailId, String mobileNumber);
	
	//to be removed
	/*int changePassword(ServerSession serverSession,
			UserPassword userPasswordVO);*/
	Map<String,String> changePassword(UserPassword userPasswordVO);
	/*int resetPassword(ServerSession serverSession, int currentUserId);*/
	void deletePassByUserId(ServerSession serverSession, int userId);
	int getUserPasswordCountByUserId(int userId);
	Map<String, Object> resetPassword(int userId);
	
}
