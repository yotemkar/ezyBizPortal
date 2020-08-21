package com.hssa.ezybiz.services;
 
import java.util.List;

 
 
public interface SequencesBO {
/*	List<Sequences> getAllSequencesList();
	List<Sequences> search(String sequenceName);	
	int insertSequences( Sequences sequences);
	int updateSequences(Sequences sequences);
	
	 int getCurrentValue(String sequenceName);*/
	 int getNextValue(String sequenceName);
	/* int getNextValue1(String sequenceName);
	 void createSequence(String sequenceName);*/
}
