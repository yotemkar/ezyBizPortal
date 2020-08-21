package com.hssa.ezybiz.dao.impl;

import java.util.Date;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.hssa.ezybiz.dao.BaseDAO;
import com.hssa.ezybiz.dao.UserLoginDao;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;

public class UserLoginDaoImpl extends BaseDAO implements UserLoginDao{
	 
		public User findByUserName(ServerSession serverSession, String userLoginName, String userPassword){
			User user = null;
//			UserPassword userPasswordObj = null;
			try{
				user = (User) getJdbcTemplate().queryForObject(getQueryById("SELECT_user_BY_NAME"), new Object[] {userLoginName}, new BeanPropertyRowMapper(User.class)); //BeanPropertyRowMapper.newInstance(FrmUser.class));
		//		userPasswordObj = getJdbcTemplate().queryForObject(getQueryById("SELECT_USER_PASSWORD_BY_USER_ID"), new Object[] {user.getUserId()}, BeanPropertyRowMapper.newInstance(UserPassword.class)); 
			}catch(IncorrectResultSizeDataAccessException e){
				user = null;
				return user;
			}
			return user;	
		}
	 
		public boolean changeUserPassword(ServerSession serverSession, String userName, String userNewPassword, String userOldPassword){
	 
			boolean passwordChanged = false;
			try{
				int i = getJdbcTemplate().update(getQueryById("UPDATE_USER_PASSWORD"), new Object[] {userNewPassword, userName, userOldPassword});
				if(i == 1){
					passwordChanged = true;
				}
				return passwordChanged;
			}catch(IncorrectResultSizeDataAccessException e){
			}
			return passwordChanged;
		}


		public UserPassword findUserByID(int userId) {
			return getJdbcTemplate().queryForObject(getQueryById("SELECT_password_by_user_id"), new Object[] {userId}, BeanPropertyRowMapper.newInstance(UserPassword.class));
		}

		
		public int getPasswordExpiredTime(String paramName, ServerSession serverSession) {
			int cnt = 0;

			try {
				//cnt=getJdbcTemplate().queryForInt(getQueryById("Select_Password_Expired_Time"), new Object[] {paramName});
				cnt=getJdbcTemplate().queryForObject(getQueryById("Select_Password_Expired_Time"), new Object[] {paramName}, Integer.class);
			} catch (Exception e) {
			}

			return cnt;
		}

		
		public void updateLastLoginTime(int userId,Date currentDate, ServerSession serverSession) {
			try{
				getJdbcTemplate().update(getQueryById("UPDATE_last_login"), new Object[] {currentDate,userId});

			}catch(IncorrectResultSizeDataAccessException e){
			}
		}


		public void blockStatus(User frmUser, ServerSession serverSession) {
			try{
				getJdbcTemplate().update(getQueryById("UPDATE_block_status"), new Object[] {frmUser.getUserStatus(),frmUser.getUserId()});

			}catch(IncorrectResultSizeDataAccessException e){
			}
			
		}
		@Override
		public User findUserByLoginName(String userLoginName) {
			User user = null;
			try{
				user = (User) getJdbcTemplate().queryForObject(getQueryById("SELECT_user_BY_NAME"), new Object[] {userLoginName}, new BeanPropertyRowMapper(User.class)); //BeanPropertyRowMapper.newInstance(FrmUser.class));
			}catch(IncorrectResultSizeDataAccessException e){
				user = null;
				return user;
			}
			return user;	
		}
}
