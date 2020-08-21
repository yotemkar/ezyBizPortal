package com.hssa.ezybiz.dao.impl;
 
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.ContentdetailDAO;
import com.hssa.ezybiz.dto.Contentdetail;
import com.hssa.ezybiz.utils.LoadJPAFQueries;

@Repository
public class ContentdetailDAOImpl extends JdbcDaoSupport implements ContentdetailDAO {
 		
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	
	public int insertContentdetail(Contentdetail contentdetail){
		
	    	return getJdbcTemplate().update( LoadJPAFQueries.getQueryById("INSERT_Contentdetail"), 
					new Object[] { 
					contentdetail.getId(),
					contentdetail.getCreateDate(),
					contentdetail.getCreator(),
					contentdetail.getEntityUid(),
					contentdetail.getModifier(),
					contentdetail.getUpdateDate(),
					contentdetail.getOptLock(),
					contentdetail.getHeader(),
					contentdetail.getContentText(),
					contentdetail.getIsDeleted(),
					contentdetail.getUrl()
			 });
	}
	
	public int updateContentdetail( Contentdetail contentdetail){
		
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Contentdetail_BY_ID"), new Object[] {
					contentdetail.getModifier(),
					contentdetail.getUpdateDate(),
					contentdetail.getOptLock(),
					contentdetail.getHeader(),
					contentdetail.getContentText(),
					contentdetail.getIsDeleted(),
					contentdetail.getUrl(),
					contentdetail.getId()
				});
	}


	@Override
	public Contentdetail findContentDetailByID(Integer detailTextId) {
		return (Contentdetail)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Contentdetail_BY_ID"), new Object[] {detailTextId}, new BeanPropertyRowMapper(Contentdetail.class));
	}
}
 
