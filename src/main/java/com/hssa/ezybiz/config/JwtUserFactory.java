package com.hssa.ezybiz.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dto.Role;
import com.hssa.ezybiz.dto.RoleDto;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.RoleBO;
import com.hssa.ezybiz.services.UserPasswordBO;

@Service
public final class JwtUserFactory {

	@Autowired
	private   RoleBO roleBO;
	@Autowired
	private   UserPasswordBO userPasswordBO;
	
	@Autowired
	private   ContactBO contactBO;
	
    public JwtUserFactory() {
    }

    public  JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getLoginId(),
                user.getGivenName(),
                user.getMiddleName(),
                user.getFamilyName(),
                contactBO.getEmailIdByUserId(user.getUserId()),
                /*userPasswordBO.getUserPasswordCountByUserId(user.getUserId()) == 0 ? 
                		 null : userPasswordBO.getUserPasswordByUserId(user.getUserId()).getUserPassword(),*/
                mapToGrantedAuthorities(roleBO.getAllRolesByUserId(user.getUserId())),
                true,
                user.getLastPasswordResetDate(),
                user.getDateOfBirth(),
                user.getCompanyId(),
                null, user.getUserType()
        );
    }
    
    public  JwtUser createUserForSwitchUser(User user,String superUserId) {
        return new JwtUser(
                user.getUserId(),
                user.getLoginId(),
                user.getGivenName(),
                user.getMiddleName(),
                user.getFamilyName(),
                contactBO.getEmailIdByUserId(user.getUserId()),
                /*userPasswordBO.getUserPasswordCountByUserId(user.getUserId()) == 0 ? 
               		 null : userPasswordBO.getUserPasswordByUserId(user.getUserId()).getUserPassword(),*/
                mapToGrantedAuthorities(roleBO.getAllRolesByUserId(user.getUserId())),
                true,
                user.getLastPasswordResetDate(),
                user.getDateOfBirth(),
                user.getCompanyId(),
                Integer.parseInt(superUserId), user.getUserType()
        );
    }

    
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new RoleDto(role.getRoleName(),role.getRoleId()))
                .collect(Collectors.toList());
    }
}
