package com.hssa.ezybiz.services.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.UserLoginDao;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;
import com.hssa.ezybiz.services.UserLoginService;
import com.hssa.ezybiz.services.UserPasswordService;
import com.hssa.ezybiz.utils.JCOEPasswordEncoder;

@Service
public class UserLoginServiceImpl implements UserLoginService,Serializable{
	
	private static final long serialVersionUID = 1L;
	private transient UserLoginDao userLoginDAO;
	private transient UserPasswordService userPasswordBO;
	
	public UserPasswordService getUserPasswordBO() {
		return userPasswordBO;
	}

	public void setUserPasswordBO(UserPasswordService userPasswordBO) {
		this.userPasswordBO = userPasswordBO;
	}

	public UserLoginServiceImpl() {
		super();
	}
	
	public UserLoginDao getUserLoginDAO() {
		return userLoginDAO;
	}
 
	public void setUserLoginDAO(UserLoginDao userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}
 
	
	public User findUserByName(ServerSession serverSession, String userLoginName, String passsword){
			
		return userLoginDAO.findByUserName(serverSession, userLoginName, passsword);
			
	}
	
	public boolean changeUserPassword(ServerSession serverSession, String userName, String userNewPassword, String userOldPassword){
		return userLoginDAO.changeUserPassword(serverSession, userName, userNewPassword, userOldPassword);
	}

	
	public UserPassword findUserByID(int userId) {
		return userLoginDAO.findUserByID(userId);
	}

	public int getPasswordExpiredTime(String paramName, ServerSession serverSession) {
		return userLoginDAO.getPasswordExpiredTime(paramName, serverSession);
	}

	
	public void updateLastLoginTime(int userId, Date date, ServerSession serverSession) {
		userLoginDAO.updateLastLoginTime(userId,date, serverSession);		
		
	}

	
	public void blockStatus(User user, ServerSession serverSession) {
		userLoginDAO.blockStatus(user, serverSession);
		
	}
	public boolean validatePassword(UserPassword userVO, String userName, String password) {
		JCOEPasswordEncoder encoder = null;
		boolean passwordMatched = false;

		try {
			encoder = JCOEPasswordEncoder.getEncoder();
			passwordMatched = encoder.isPasswordValid(userVO.getUserPassword()
					.trim(), password + userName, userVO.getPasswordKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return passwordMatched;
	}

	@Override
	public User findUserByLoginName(String userLoginName) {
		return userLoginDAO.findUserByLoginName(userLoginName);
	}

	@Override
	public boolean validateUserByPassword(User user,String password) {
	//	boolean validUser=  false;
		if (user != null) {
			UserPassword userPass = findUserByID(user.getUserId());
			return validatePassword(userPass, user.getLoginId(),password);
		}
		return false;

	}

}
