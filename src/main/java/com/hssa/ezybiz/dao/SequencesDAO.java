package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.Sequences;

 
public interface SequencesDAO 
{
//[-- Protected Block signature. Add your code here. 
//--]
	List<Sequences> search(String sequenceName);
	List<Sequences> findAll();
	int insert(Sequences frmSequences);
	int update(Sequences frmSequences);
	
	int countByName(String sequenceName);
	
	
//[-- Protected Block signature. Add your code here. 
	 Sequences findBySequenceName(String sequenceName);
//--]
	int getNextSequence(String sequenceName);
 
}
