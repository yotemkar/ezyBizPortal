package com.hssa.ezybiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.services.UserLoginService;
@Service
public class JWTUserAuthentication {

	@Autowired
	UserLoginService userLoginBO;
	
	@Autowired
	JwtUserFactory jwtUserFactory;
	
	public UserLoginService getFrmUserLoginBO() {
		return userLoginBO;
	}
	public void setFrmUserLoginBO(UserLoginService frmUserLoginBO) {
		this.userLoginBO = frmUserLoginBO;
	}
	
	
    
    public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException {
    	
    	User user = userLoginBO.findUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	//check for password
        	userLoginBO.validateUserByPassword(user,password);
            return jwtUserFactory.create(user);
        }
    }
}
