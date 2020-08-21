package com.hssa.ezybiz.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.ApplicationTokenDAO;
import com.hssa.ezybiz.dto.ApplicationToken;
import com.hssa.ezybiz.services.ApplicationTokenBO;

@Service
public class ApplicationTokenBOImpl implements ApplicationTokenBO {
	
	@Autowired
	ApplicationTokenDAO applicationTokenDAO;

	/**
	 * It will validate token and services associated with the token. It will return greater than zero if token is valid and is allowed to access the requested service.
	 *  
	 */
	@Override
	public int validateApplicationTokenAndURL(ApplicationToken tokenDetails) {
		return applicationTokenDAO.validateApplicationTokenAndURL(tokenDetails);
	}

}
