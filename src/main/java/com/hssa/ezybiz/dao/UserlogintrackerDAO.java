package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.Userlogintracker;

public interface UserlogintrackerDAO 
{
	List<Userlogintracker> search(List<Object> paramList);
	List<Userlogintracker> getAll(ServerSession serverSession);
	int insertUserlogintracker(Userlogintracker userlogintracker, int trackerId, int userId);
	int deleteUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker);
	int updateUserlogintracker(ServerSession serverSession, Userlogintracker userlogintracker);
	Userlogintracker findUserlogintrackerByID(ServerSession serverSession, int id);
 
public List<Userlogintracker> findUserlogintrackerByFKUserid(ServerSession serverSession, int userid);
 
 
}
