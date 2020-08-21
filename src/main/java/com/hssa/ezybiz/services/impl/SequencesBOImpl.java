package com.hssa.ezybiz.services.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.dao.SequencesDAO;
import com.hssa.ezybiz.services.SequencesBO;


@Service
//@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SequencesBOImpl implements SequencesBO {
 
 
	private static final String SequencesSequence = "SequencesSequence";
	
	@Autowired
	private SequencesDAO sequencesDAO;
 
	
	public SequencesDAO getSequencesDAO() {
		return sequencesDAO;
	}

	public void setSequencesDAO(SequencesDAO sequencesDAO) {
		this.sequencesDAO = sequencesDAO;
	}

	public SequencesBOImpl(){
		super();
	}
 
	/*public List<Sequences> getAllSequencesList(){
		return sequencesDAO.findAll();
	}
	
	public List<Sequences> search( String sequenceName){
		return sequencesDAO.search(sequenceName);
	}
	
	public int insertSequences( Sequences sequences){
		return sequencesDAO.insert(sequences);
	}
	
	public int updateSequences(Sequences sequences){
		return sequencesDAO.update(sequences);
	}*/
	
//[-- Protected Block signature. Add your code here. 
	/*public int getCurrentValue( String sequenceName) {
		Sequences frmSequences = sequencesDAO.findBySequenceName(sequenceName);
		return frmSequences.getCurrentValue();
	}*/
	
	public int getNextValue(String sequenceName) {
		/*Sequences sequences = null;

		if(sequencesDAO.countByName(sequenceName) > 0){
			sequences = sequencesDAO.findBySequenceName(sequenceName);				
		}else{
			createSequence(sequenceName);
			sequences = sequencesDAO.findBySequenceName(sequenceName);
		}
		int nextCurrentValue = sequences.getIncrementBy() + sequences.getCurrentValue();
		if(nextCurrentValue>=sequences.getSequenceMinValue() && nextCurrentValue <= sequences.getSequenceMaxValue()) {
			sequences.setCurrentValue(nextCurrentValue);
			updateSequences(sequences);
			return nextCurrentValue;
		}
		else  {
			sequences.setCurrentValue(sequences.getSequenceMinValue());
			updateSequences(sequences);
			return sequences.getSequenceMinValue();
		}*/
		
		return sequencesDAO.getNextSequence(sequenceName);

		
		
	}
	
	
	/*public int getNextValue1(String sequenceName) {
		Sequences sequences = null;

		if(sequencesDAO.countByName(sequenceName) > 0){
			sequences = sequencesDAO.findBySequenceName(sequenceName);				
		}else{
			createSequence(sequenceName);
			sequences = sequencesDAO.findBySequenceName(sequenceName);
		}
		int nextCurrentValue = sequences.getIncrementBy() + sequences.getCurrentValue();
		if(nextCurrentValue>=sequences.getSequenceMinValue() && nextCurrentValue <= sequences.getSequenceMaxValue()) {
			sequences.setCurrentValue(nextCurrentValue);
			updateSequences(sequences);
			return nextCurrentValue;
		}
		else  {
			sequences.setCurrentValue(sequences.getSequenceMinValue());
			updateSequences(sequences);
			return sequences.getSequenceMinValue();
		}
		
		
	}
	
	@Override
	public void createSequence(String sequenceName) {
		Sequences sequences =new  Sequences();
		sequences.setSequenceName(sequenceName);
		sequences.setCurrentValue(1);
		sequences.setIncrementBy(1);
		sequences.setSequenceDescription(sequenceName);
		sequences.setSequenceMaxValue(99999);
		sequences.setSequenceMinValue(0);
		sequences.setStartwith(1);
		
		// to do - please check it if already exits
		insertSequences(sequences);
		
	}*/
//--]
 
}
