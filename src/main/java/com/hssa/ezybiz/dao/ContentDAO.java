package com.hssa.ezybiz.dao;

import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.ContentImage;
import com.hssa.ezybiz.dto.Igimage;

public interface ContentDAO {
	/**
	 * Method provides get all contents in table.
	 * 
	 * @param companyId
	 * @return
	 */
	List<Content> getAll(Integer companyId);

	/**
	 * Method to insert content in the table.
	 * 
	 * @param content
	 * @param id
	 * @param userId
	 * @return
	 */
	int insertContent(Content content);

	/**
	 * Method to update content.
	 * 
	 * @param oldContent
	 * @param newContent
	 * @param integer
	 * @param userId
	 * @return
	 */
	int updateContent(Content newContent);

	/**
	 * Method returns content object by content id.
	 * 
	 * @param id
	 * @return
	 */
	Content findContentById(Integer id);

	/**
	 * Method to insert content image record in table.
	 * 
	 * @param contentImage
	 * @param id
	 * @param userId
	 * @return
	 */
	int insertContentImage(ContentImage contentImage);

	/**
	 * Method returns content image table records by content id.
	 * 
	 * @param id
	 * @return
	 */
	List<ContentImage> getImagesByContentId(Integer id);

	/**
	 * Method returns images for page by pagename.
	 * 
	 * @param pageName
	 * @param companyId
	 * @return
	 */
	List<Igimage> getImageList(String pageName, Integer companyId);

	/**
	 * Method returns list of content images by content id.
	 * 
	 * @param id
	 * @return
	 */
	public List<ContentImage> findContentImagesById(Integer id);

	/**
	 * Method returns content text for page by pagename.
	 * 
	 * @param pageName
	 * @param companyId
	 * @return
	 */
	String getPageContent(String pageName, Integer companyId);

	/**
	 * Method to delete image by id.
	 * 
	 * @param imageId
	 * @return
	 */
	int deleteImageById(Integer imageId);

	/**
	 * Method to delete content image record.
	 * 
	 * @param contentId
	 * @param imageId
	 * @return
	 */
	int deleteContentImage(Integer contentId, Integer imageId);

	/**
	 * Method returns content by template.
	 * 
	 * @param template
	 * @param companyId
	 * @return
	 */
	Content findContentByTemplate(String template, Integer companyId);

	/**
	 * Method returns content for sales region and/or sales office.
	 * 
	 * @param template
	 * @param salesRegionId
	 * @param salesOfficeId
	 * @param companyId
	 * @return
	 */
	Content findContentBySalesRegion(String template, Integer salesRegionId, Integer salesOfficeId, Integer companyId);

	/**
	 * Method returns list of images for dashboard page.
	 * 
	 * @param template
	 * @param salesRegionId
	 * @param salesOfficeId
	 * @param companyId
	 * @return
	 */
	List<Igimage> getDashboardImages(String template, Integer salesRegionId, Integer salesOfficeId, Integer companyId);

	Map<String, Content> findContentByCompanyID(int companyid);

	List<Map<String, Object>> getDistinctContentHeader(int companyId);

	Map<String, Content> findContentForLoginPage();
}
