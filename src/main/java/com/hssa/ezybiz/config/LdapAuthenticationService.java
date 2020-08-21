package com.hssa.ezybiz.config;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * DO NOT CHANGE ANY CODE OR DO NOT PRESS Ctrl+Shift+O BEFORE PRODUCTION 
 * 
 * 
 * @author KPIT
 * 
 * LDAP authentication for internal user.
 * 
 * Authenticate the employee with NT credentials from LDAP.
 *
 */
@Service
public class LdapAuthenticationService {
	
	final static Logger LOG = LoggerFactory.getLogger(LdapAuthenticationService.class);

	private static final String CONTEXT_FACTORY_NAME = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String AUTH_TYPE = "simple";
	
	@Value("${ldapURL}")
	private String ldapURL;
	
	@Value("${domain}")
	private String domain;
	
	/**
	 * This block holds the logic for LDAP authentication.
	 * @param userName
	 * @param password
	 * @return
	 */
	public Boolean authenticateUserFromLDAP(String userName,String password) {
		//return true;
		
		/**
		 * 
		 * UNCOMMENT BELOW CODE BEFORE PROUCTION
		 */
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);

		env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY_NAME);
		env.put(Context.PROVIDER_URL, ldapURL);
		env.put("com.sun.jndi.ldap.read.timeout", "1000");
		env.put(Context.SECURITY_AUTHENTICATION, AUTH_TYPE);
		env.put(Context.SECURITY_PRINCIPAL, userName + domain);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext ctx = new InitialDirContext(env);
			ctx.close();
			return true;
		} catch (NamingException e) {
			return false;
//			return true;
		}
		
	}
}
