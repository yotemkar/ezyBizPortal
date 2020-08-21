package com.hssa.ezybiz.config;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dto.UserPassword;
import com.hssa.ezybiz.services.UserLoginService;
import com.hssa.ezybiz.utils.Credential;

@Service
public class CustomePasswordEncoder implements PasswordEncoder {

	
	@Autowired
	UserLoginService userLoginBO;
	
	@Override
	public String encode(CharSequence rawPassword) {
		byte[] passwordBytes = rawPassword.toString().getBytes(StandardCharsets.UTF_8);
		return Base64.encodeBase64String(passwordBytes);
	}
	
	private static final String ROLE_EMPLOYEE = "Employee";
	private static final String SWITCH_USER = "switchUser";
	
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		String userIdWithPassword = rawPassword.toString();
		String []credentialArray = userIdWithPassword.split("\\,");
		String userId = credentialArray[0];
		String userName = credentialArray[1];
		String enteredPassword =  userIdWithPassword.substring(StringUtils.ordinalIndexOf(userIdWithPassword, ",", 3)+1);
		String role = credentialArray[2];
		//check if role is employee and ldap authentication is true(do not check the password)
		if(role!=null && (role.equals(ROLE_EMPLOYEE) || role.equals(SWITCH_USER))){
			return true;
		}
		
		
		UserPassword userPassword = userLoginBO.findUserByID(Integer.parseInt(userId));
/*		JCOEPasswordEncoder encoder = null;
		boolean passwordMatched = false;

		try {
			encoder = JCOEPasswordEncoder.getEncoder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		passwordMatched = encoder.isPasswordValid(userPassword.getUserPassword()
				.trim(), enteredPassword + userName, userPassword.getPasswordKey());*/
		final Credential credential_ = Credential.getCredential(userPassword.getUserPassword());
		return credential_.check(enteredPassword);
		
		
	}

}
