/**
 * 
 */
package com.hssa.ezybiz.dao;
import java.util.List;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;
 
public interface UserPasswordDAO {
	 void insert(UserPassword userPasswordVO, int id, int userId);
	 List<UserPassword> findAll(ServerSession serverSession);
	 UserPassword findUserPasswordByUserId(int userId);
	 void delete(ServerSession serverSession, UserPassword userPasswordVO);
	 void update(ServerSession serverSession, UserPassword userPasswordVO);
	 boolean changeUserPassword(UserPassword userPass);
	 int getMaxId(ServerSession serverSession);
	void insertNewPassword(ServerSession serverSession, UserPassword userPass);
	void updateAttempts(User oldUser, User frmUser, int id, int userId);
	void updateAttemptsToZero(User oldUser, User frmUser, int id, int userId);
	int getPasswordAttempts(String param);
	void deletePassByUserId(ServerSession serverSession, int userId);
	int getUserPasswordCountByUserId(int userId);
	void removePreviousUserPassword(Integer userId);
	List<String> getLastFivePasswordByUserId(Integer userId);
	
 
}
