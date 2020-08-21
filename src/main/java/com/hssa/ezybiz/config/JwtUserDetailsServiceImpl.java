package com.hssa.ezybiz.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.services.UserBO;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired(required=true)
     UserBO userBo;
	
	@Autowired
	JwtUserFactory jwtUserFactory;
	

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
    	ServerSession serverSession = null;
    	List<String> splittedUserName = Arrays.asList(username.split(","));
    	String finalUserName = splittedUserName.get(0);
        User user = userBo.getUserByName(serverSession,finalUserName);
        /**
         * check if the user is employee or customer. And invoke LDAP service here.
         */
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	return splittedUserName.size()==2 ? jwtUserFactory.createUserForSwitchUser(user,splittedUserName.get(splittedUserName.size()-1)) : jwtUserFactory.create(user);
        }
    }
}
