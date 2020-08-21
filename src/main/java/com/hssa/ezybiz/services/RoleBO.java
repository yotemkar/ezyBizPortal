/**
 * 
 */
package com.hssa.ezybiz.services;
import java.util.List;

import com.hssa.ezybiz.dto.Role;
 
public interface RoleBO {
	 
	 /**
	  * get all role of user
	  * @param userId
	  * @return
	  */
	 List<Role> getAllRolesByUserId(int userId);

	List<String> getAllRoleNameByUserId(int userId);
	
	List<String> getAllRoleTypeByUserId(int userId);
}
