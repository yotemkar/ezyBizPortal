/**
 * 
 */
package com.hssa.ezybiz.dao;
import java.util.List;

import com.hssa.ezybiz.dto.Role;
 
public interface RoleDAO {
	 /**
	  * getAllRolesByUserId
	  * @param userId
	  * @return
	  */
	 public List<Role> getAllRolesByUserId(int userId);

	public List<String> getAllRoleNameByUserId(int userId); 
	
	List<String> getAllRoleTypeByUserId(int userId);
	
 
}
