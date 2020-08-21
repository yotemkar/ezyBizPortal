package com.hssa.ezybiz.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.UserlogintrackerDAO;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.Userlogintracker;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.services.UserlogintrackerBO;
 
@Service
public class UserlogintrackerBOImpl implements UserlogintrackerBO{
 
	private static final String UserlogintrackerSequence = "UserlogintrackerSequence";
	
	@Autowired
	private UserlogintrackerDAO userlogintrackerDAO;
	
	@Autowired
	private SequencesBO sequencesBO;
	
		 
		
	public List<Userlogintracker> search(List<Object> paramList){
		return userlogintrackerDAO.search(paramList);
	}
	public List<Userlogintracker> getAllUserlogintrackerList(ServerSession serverSession){
		return userlogintrackerDAO.getAll(serverSession);
	}
	public int insertUserlogintracker(Userlogintracker userlogintracker){
	
		userlogintracker.setId(sequencesBO.getNextValue(UserlogintrackerSequence));
		return userlogintrackerDAO.insertUserlogintracker(userlogintracker,userlogintracker.getId(),userlogintracker.getUserid());
	}
	public int deleteUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker){
		return userlogintrackerDAO.deleteUserlogintracker(serverSession, userlogintracker);
	}
	public int updateUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker, int userId){
		/*userlogintracker.setUpdatedBy(userId);
		userlogintracker.setUpdatedTime(new Timestamp((new java.util.Date()).getTime()));*/
		
		return userlogintrackerDAO.updateUserlogintracker(serverSession, userlogintracker);
	}
	public Userlogintracker findUserlogintrackerByID(ServerSession serverSession, int id){
		return userlogintrackerDAO.findUserlogintrackerByID(serverSession, id);
	}
 
	public List<Userlogintracker> findUserlogintrackerByFKUserid(ServerSession serverSession, int userid){
		return userlogintrackerDAO.findUserlogintrackerByFKUserid(serverSession, userid);
	}
 
 
 
}
