package com.hssa.ezybiz.services;

import com.hssa.ezybiz.dto.Contentdetail;

public interface ContentdetailBO {
	
	int insertContentdetail(Contentdetail contentdetail, Integer userId);
	
	int updateContentdetail(Contentdetail contentdetail, Integer userId);

	Contentdetail findContentDetailByID(Integer detailTextId);
}
