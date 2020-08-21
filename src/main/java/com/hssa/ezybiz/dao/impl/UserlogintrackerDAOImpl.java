package com.hssa.ezybiz.dao.impl;
 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.UserlogintrackerDAO;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.Userlogintracker;
import com.hssa.ezybiz.utils.DaoHelper;
import com.hssa.ezybiz.utils.LoadJPAFQueries;
 
@Repository
public class UserlogintrackerDAOImpl extends JdbcDaoSupport implements UserlogintrackerDAO {
 
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	
		public List<Userlogintracker> search(List<Object> paramList) {
 
		Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
 
		return (List) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Userlogintracker"), objArrSearchableTokens, BeanPropertyRowMapper.newInstance(Userlogintracker.class)); 
		
	}
	public List<Userlogintracker> getAll(ServerSession serverSession) {
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Userlogintracker"), BeanPropertyRowMapper.newInstance(Userlogintracker.class)); 
		
	}
	public int insertUserlogintracker(Userlogintracker userlogintracker,int trackerId,int userId){
		
	    	return getJdbcTemplate().update( LoadJPAFQueries.getQueryById("INSERT_Userlogintracker"), 
					new Object[] { 
					userlogintracker.getId(),
					userlogintracker.getUserid(),
					userlogintracker.getLogintime(),
					userlogintracker.getIsMobile()
							 });
	}
	public int deleteUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker) {
		
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_Userlogintracker_BY_ID"),new Object[] { userlogintracker.getId() });
	}
	public int updateUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker){
		
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Userlogintracker_BY_ID"), new Object[] {
					userlogintracker.getUserid(),
					userlogintracker.getLogintime(),
					userlogintracker.getId()
												});
	}
	public Userlogintracker findUserlogintrackerByID(ServerSession serverSession, int id){
		return (Userlogintracker)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Userlogintracker_BY_ID"), new Object[] {id}, new BeanPropertyRowMapper(Userlogintracker.class));
	}
 
	public List<Userlogintracker> findUserlogintrackerByFKUserid(ServerSession serverSession, int userid){
		return (List<Userlogintracker>)getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Userlogintracker_By_FKUserid"), new Object[] {
			userid }, BeanPropertyRowMapper.newInstance(Userlogintracker.class)); 
	}
	
 
}
 
