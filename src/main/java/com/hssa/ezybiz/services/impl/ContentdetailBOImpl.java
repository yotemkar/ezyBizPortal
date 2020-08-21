package com.hssa.ezybiz.services.impl;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.ContentdetailDAO;
import com.hssa.ezybiz.dto.Contentdetail;
import com.hssa.ezybiz.services.ContentdetailBO;
import com.hssa.ezybiz.services.SequencesBO;


@Service
public class ContentdetailBOImpl implements ContentdetailBO{
 
	private static final String ContentdetailSequence = "ContentdetailSequence";
	
	@Autowired
	private ContentdetailDAO contentdetailDAO;
 
	@Autowired
	private SequencesBO sequencesBO;

	public ContentdetailBOImpl(){
		super();
	}
 
	@Override
	public int insertContentdetail(Contentdetail contentDetail, Integer userId){
	
		contentDetail.setId(sequencesBO.getNextValue(ContentdetailSequence));
		contentDetail.setEntityUid(UUID.randomUUID().toString());
		contentDetail.setCreator(Integer.toString(userId));
		contentDetail.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
		contentDetail.setModifier(Integer.toString(userId));
		contentDetail.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
 
		return contentdetailDAO.insertContentdetail(contentDetail);
	}
	
	@Override
	public int updateContentdetail(Contentdetail contentDetail, Integer userId){
		contentDetail.setModifier(Integer.toString(userId));
		contentDetail.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		
		return contentdetailDAO.updateContentdetail(contentDetail);
	}

	@Override
	public Contentdetail findContentDetailByID(Integer detailTextId) {
		
		return contentdetailDAO.findContentDetailByID(detailTextId);
	}
}
