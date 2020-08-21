package com.hssa.ezybiz.dao.impl;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.hssa.ezybiz.dao.BaseDAO;
import com.hssa.ezybiz.dao.RoleDAO;
import com.hssa.ezybiz.dto.Role;


 
public class RoleDAOImpl extends BaseDAO implements RoleDAO {
 	
	@Override
	public List<Role> getAllRolesByUserId(int userId) {
		return getJdbcTemplate().query(getQueryById("SELECT_Role_FROM_User_Role_By_UserId"),new Object[]{userId}, BeanPropertyRowMapper.newInstance(Role.class));
	}

	@Override
	public List<String> getAllRoleNameByUserId(int userId) {
		return getJdbcTemplate().queryForList(getQueryById("GET_ALL_ROLE_NAME_BY_USER_ID"), String.class, userId);
	}
	
	@Override
	public List<String> getAllRoleTypeByUserId(int userId) {
		return getJdbcTemplate().queryForList(getQueryById("GET_ALL_ROLE_TYPE_BY_USER_ID"), String.class, userId);
	}
}
