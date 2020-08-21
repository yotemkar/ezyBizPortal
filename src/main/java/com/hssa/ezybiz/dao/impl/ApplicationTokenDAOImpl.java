package com.hssa.ezybiz.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.ApplicationTokenDAO;
import com.hssa.ezybiz.dto.ApplicationToken;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class ApplicationTokenDAOImpl extends JdbcDaoSupport implements ApplicationTokenDAO {
	
	@Autowired 
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/**
	 * It will validate token and services associated with the token. It will return greater than zero if token is valid and is allowed to access the requested service.
	 *  
	 */
	@Override
	public int validateApplicationTokenAndURL(ApplicationToken tokenDetails) {
		// VALIDATE_TOKEN
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("VALIDATE_TOKEN"), new Object[] {
				tokenDetails.getApplicationName(),
				tokenDetails.getServiceUrl(),
				tokenDetails.getApplicationToken()
		}, Integer.class);		
	}

}
