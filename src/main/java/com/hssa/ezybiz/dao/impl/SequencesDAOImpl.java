package com.hssa.ezybiz.dao.impl;
 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.SequencesDAO;
import com.hssa.ezybiz.dto.Sequences;
import com.hssa.ezybiz.utils.LoadJPAFQueries;


//[-- Protected Block signature. Add your code here. 
//--]
 @Repository
//@Scope("prototype")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SequencesDAOImpl extends JdbcDaoSupport implements SequencesDAO {
 
	 @Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
 
	//[-- Protected Block signature. Add your code here. 
	//--]
	
	
	public List<Sequences> search(String sequenceName) {
		String tmpSequenceName = sequenceName + "";
		if("".equals(tmpSequenceName) || "null".equals(tmpSequenceName)){
			tmpSequenceName = "%";
		}else{ // change for not string fields
			tmpSequenceName = "%"+tmpSequenceName+"%";
			}
			//for number fields
			//if("".equals([#Field_Name#]) || "null".equals([#Field_Name#]) || [#Field_Name#] <= 0){
		           //tmp[#Field_Name#] = "%";
			//}
			//for Date fields
			//		}else{ // remove for not string fields
			//			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			//			tmp[#Field_Name#] = dt1.format([#Field_Name#]);
			//}
			
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SEARCH_Sequences"), new Object[] {
			tmpSequenceName 
					},BeanPropertyRowMapper.newInstance(Sequences.class)); 
		
	}
	public List<Sequences> findAll(){
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Sequences"), BeanPropertyRowMapper.newInstance(Sequences.class)); 
		
	}
	public int insert(Sequences sequences){
		
	    	return getJdbcTemplate().update( LoadJPAFQueries.getQueryById("INSERT_Sequences"), 
					new Object[] { 
					//sequences.getSequenceId(),
					sequences.getSequenceName(),
					sequences.getSequenceDescription(),
					sequences.getStartwith(),
					sequences.getIncrementBy(),
					sequences.getSequenceMinValue(),
					sequences.getSequenceMaxValue(),
					sequences.getCurrentValue()
							 });
	}
	
	public int update(Sequences sequences){
		
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Sequences_BY_ID"), new Object[] {
					sequences.getSequenceName(),
					sequences.getSequenceDescription(),
					sequences.getStartwith(),
					sequences.getIncrementBy(),
					sequences.getSequenceMinValue(),
					sequences.getSequenceMaxValue(),
					sequences.getCurrentValue(),					
					sequences.getSequenceName()
												});
	}
	
	public Sequences findByID(int sequenceId){
		return (Sequences)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Sequences_BY_ID"), new Object[] {sequenceId}, new BeanPropertyRowMapper(Sequences.class));
	}
 
	
//[-- Protected Block signature. Add your code here. 
	public Sequences findBySequenceName(String sequenceName) {
		return (Sequences)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Sequences_BY_NAME"), new Object[] {sequenceName}, new BeanPropertyRowMapper<Sequences>(Sequences.class));
	}
//--]

	@Override
	public int countByName(String sequenceName) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_Sequences_BY_Name"), new Object[] {sequenceName}, Integer.class);
	}
	@Override
	public int getNextSequence(String sequenceName){
		int id =  getJdbcTemplate().queryForObject("SELECT NEXT VALUE FOR "+sequenceName, null, Integer.class); 
		return id;
	}

 
}
