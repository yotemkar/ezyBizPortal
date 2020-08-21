package com.hssa.ezybiz.dao;

import com.hssa.ezybiz.dto.Contentdetail;

public interface ContentdetailDAO 
{
	int insertContentdetail(Contentdetail contentdetail);
	
	int updateContentdetail( Contentdetail newContentDetail);

	Contentdetail findContentDetailByID(Integer detailTextId);
}
