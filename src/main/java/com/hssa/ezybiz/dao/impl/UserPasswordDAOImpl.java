package com.hssa.ezybiz.dao.impl;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.hssa.ezybiz.dao.BaseDAO;
import com.hssa.ezybiz.dao.UserPasswordDAO;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;

public class UserPasswordDAOImpl extends BaseDAO implements UserPasswordDAO {

	@Override
	public void insert(UserPassword userPasswordVO, int id, int userId) {
		getJdbcTemplate().update(getQueryById("INSERT_user_password"), new Object[] {userPasswordVO.getUserPassswordId(),
			userPasswordVO.getUserId(),
			userPasswordVO.getUserPassword(),
			userPasswordVO.getPasswordKey(),
			userPasswordVO.getCreatedBy(),
			userPasswordVO.getCreatedTime(),
			userPasswordVO.getUpdatedBy(),
			userPasswordVO.getUpdatedTime()});

	}

	@Override
	public List<UserPassword> findAll(ServerSession serverSession) {
		return getJdbcTemplate().query(getQueryById("SELECT_user_password"),BeanPropertyRowMapper.newInstance(UserPassword.class));

	}

	@Override
	public UserPassword findUserPasswordByUserId(int userId) {
		return getJdbcTemplate().queryForObject(getQueryById("SELECT_user_password_by_user_id"), new Object[] {userId}, BeanPropertyRowMapper.newInstance(UserPassword.class));

	}

	@Override
	public void delete(ServerSession serverSession,UserPassword userPasswordVO) {
		getJdbcTemplate().update(getQueryById("DELETE_user_password"), new Object[] { userPasswordVO.getUserPassswordId()});

	}

	@Override
	public void update(ServerSession serverSession,UserPassword userPasswordVO) {
		getJdbcTemplate().update(getQueryById("UPDATE_user_password"), new Object[] { userPasswordVO.getUserId(),
			userPasswordVO.getUserPassword(),
			userPasswordVO.getCreatedBy(),
			userPasswordVO.getCreatedTime(),
			userPasswordVO.getUpdatedBy(),
			userPasswordVO.getUpdatedTime(),
			userPasswordVO.getUserPassswordId()});
	}


	@Override
	public int getMaxId(ServerSession serverSession) {
		//return getJdbcTemplate().queryForInt(getQueryById("SELECT_user_password_max_id"));
		return getJdbcTemplate().queryForObject(getQueryById("SELECT_user_password_max_id"), Integer.class);
	}

	@Override
	public boolean changeUserPassword(UserPassword userPass) {
		boolean changePassword=false;
		int k=getJdbcTemplate().update(getQueryById("UPDATE_user_password_to_change_password"), new Object[] {userPass.getEncodedPassword(),userPass.getPasswordKey(),
			userPass.getUpdatedTime(),userPass.getUserId()});
		if(k==1){
			changePassword=true;
		}
		return changePassword;
	}

	@Override
	public void insertNewPassword(ServerSession serverSession,
			UserPassword userPass) {
		getJdbcTemplate().update(getQueryById("insert_new_user_password"), new Object[] { userPass.getUserId(),
			userPass.getUserPassword(),
			userPass.getUpdatedBy(),
			userPass.getCreatedBy(),
			userPass.getCreatedTime(),
			userPass.getUpdatedTime()});
	}

	@Override
	public int getPasswordAttempts(String param) {
		//return getJdbcTemplate().queryForInt(getQueryById("SELECT_password_blocked_attempts"),param);
		return getJdbcTemplate().queryForObject(getQueryById("SELECT_password_blocked_attempts"), new Object[]{param}, Integer.class);
	}

	@Override
	public void updateAttempts(User oldUser, User user, int id, int userId) {
		getJdbcTemplate().update(getQueryById("UPDATE_password_attempts"), new Object[] {user.getUserId()});
	}

	@Override
	public void updateAttemptsToZero(User oldUser, User user, int id, int userId) {
		getJdbcTemplate().update(getQueryById("UPDATE_password_attempts_to_zero"), new Object[] {user.getUserId()});
	}

	@Override
	public void deletePassByUserId(ServerSession serverSession, int userId) {
		getJdbcTemplate().update(getQueryById("DELETE_user_password_by_user_id"), new Object[] {userId});

	}

	@Override
	public int getUserPasswordCountByUserId(int userId) {
		return getJdbcTemplate().queryForObject(getQueryById("SELECT_USER_PASSWORD_COUNT_BY_USER_ID"), new Object[] {userId},Integer.class);
	}

	@Override
	public void removePreviousUserPassword(Integer userId) {
		getJdbcTemplate().update(getQueryById("REMOVE_PREVIOUS_USER_PASSWORD"), new Object[] {userId});
		
	}

	@Override
	public List<String> getLastFivePasswordByUserId(Integer userId) {
		return getJdbcTemplate().queryForList(getQueryById("GET_LAST_FIVE_PASSWORD_BY_USER_ID"), String.class, userId);
		/*return getJdbcTemplate().query(getQueryById("GET_LAST_FIVE_PASSWORD_BY_USER_ID"), new RowMapper<String>() {
		      public String mapRow(ResultSet resultSet, int i) throws SQLException {
		          return resultSet.getString(1);
		        }
		      },userId);*/
	}
}
