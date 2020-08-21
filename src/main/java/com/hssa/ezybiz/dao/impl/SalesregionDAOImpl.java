package com.hssa.ezybiz.dao.impl;
 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.SalesRegionDAO;
import com.hssa.ezybiz.dto.SalesRegion;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.utils.DaoHelper;
import com.hssa.ezybiz.utils.LoadJPAFQueries;


@Repository
public class SalesregionDAOImpl extends JdbcDaoSupport implements SalesRegionDAO {
 
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
 
	public List<SalesRegion> search(ServerSession serverSession, List<Object> paramList) {
 
		Object[] objArrSearchableTokens = DaoHelper.getSearchableTokens(paramList);
 
		return (List<SalesRegion>) getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Salesregion"), objArrSearchableTokens, BeanPropertyRowMapper.newInstance(SalesRegion.class)); 
		
	}
	public List<SalesRegion> getAll(Integer companyId) {
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Salesregion"),new Object[] {companyId}, BeanPropertyRowMapper.newInstance(SalesRegion.class)); 
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SalesRegion findSalesregionByID(ServerSession serverSession, int id){
		return (SalesRegion)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Salesregion_BY_ID"), new Object[] {id}, new BeanPropertyRowMapper(SalesRegion.class));
	}
 
	public List<SalesRegion> findSalesregionByFKEnterpriseid(ServerSession serverSession, int enterpriseid){
		return (List<SalesRegion>)getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Salesregion_By_FKEnterpriseid"), new Object[] {
			enterpriseid }, BeanPropertyRowMapper.newInstance(SalesRegion.class)); 
	}
	
}
 
