package com.hssa.ezybiz.config;

import java.io.Serializable;

/**
 * 
 * @author KPIT
 *
 */
public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
    private String email;
    private String googleIdToken;
    private String code;
    private Boolean isGoogleAuth;

    public JwtAuthenticationRequest() {
        super();
    }

    

    public JwtAuthenticationRequest(String username, String password, String email, String googleIdToken,
    		String code,Boolean isGoogleAuth) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.googleIdToken = googleIdToken;
		this.code=code;
		this.isGoogleAuth = isGoogleAuth;
	}



	public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGoogleIdToken() {
		return googleIdToken;
	}



	public void setGoogleIdToken(String googleIdToken) {
		this.googleIdToken = googleIdToken;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public Boolean getIsGoogleAuth() {
		return isGoogleAuth;
	}



	public void setIsGoogleAuth(Boolean isGoogleAuth) {
		this.isGoogleAuth = isGoogleAuth;
	}
    
}
