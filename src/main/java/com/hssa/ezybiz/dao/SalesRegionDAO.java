package com.hssa.ezybiz.dao;
 
import java.util.List;

import com.hssa.ezybiz.dto.SalesRegion;
import com.hssa.ezybiz.dto.ServerSession;


public interface SalesRegionDAO 
{
	List<SalesRegion> search(ServerSession serverSession, List<Object> paramList);
	List<SalesRegion> getAll(Integer companyId);
	SalesRegion findSalesregionByID(ServerSession serverSession, int id);
 
	public List<SalesRegion> findSalesregionByFKEnterpriseid(ServerSession serverSession, int enterpriseid);
 
}
