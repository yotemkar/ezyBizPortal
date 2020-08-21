/**
 * 
 */
package com.hssa.ezybiz.services.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.RoleDAO;
import com.hssa.ezybiz.dto.Role;
import com.hssa.ezybiz.services.RoleBO;

 @Service
public class RoleBoImpl implements RoleBO {
 
	private RoleDAO roleDAO;
	 
	/**
	 * @return the roleDAO
	 */
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
 
	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
 
	@Override
	public List<Role> getAllRolesByUserId(int userId) {
		return (roleDAO.getAllRolesByUserId(userId));
	}
 
	@Override
	public List<String> getAllRoleNameByUserId(int userId) {
		return (roleDAO.getAllRoleNameByUserId(userId));
	}

	@Override
	public List<String> getAllRoleTypeByUserId(int userId) {
		return roleDAO.getAllRoleTypeByUserId(userId);
	}
 
}
