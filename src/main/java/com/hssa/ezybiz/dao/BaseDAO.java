package com.hssa.ezybiz.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hssa.ezybiz.utils.LoadJPAFQueries;

public class BaseDAO extends JdbcDaoSupport {

	public static final String DATABASE_QUERY = "database-queries.xml";

	protected String getQueryById(String queryId) {
		return LoadJPAFQueries.getQueryById(queryId);
	}

	public String formatedDateTimeValue(long val){
		Date date=new Date(val);
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		return df.format(date);

	}

}
