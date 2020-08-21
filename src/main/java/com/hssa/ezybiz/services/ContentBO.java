package com.hssa.ezybiz.services;
 
import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.ContentImage;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.SalesRegion;
import com.hssa.ezybiz.dto.Salesoffice;


public interface ContentBO {
	
	/**
	 * Method provides get all contents in table.
	 * @param companyId
	 * @return
	 */
	List<Content> getAllContentList(Integer companyId);
	
	/**
	 * Method to insert content in the table.
	 * @param content
	 * @param userId
	 * @param companyId
	 * @return
	 */
	Map<String, String> insertContent(Content content, Integer userId, Integer companyId);
	
	/**
	 * Method to update content.
	 * @param content
	 * @param userId
	 * @return
	 */
	Map<String, String> updateContent(Content content, Integer userId);
	
	/**
	 * Method returns content by id.
	 * @param id
	 * @return
	 */
	Content findContentById(Integer id);
	
	/**
	 * Method to insert record in content image table.
	 * @param contentImage
	 * @param userId
	 * @return
	 */
	int insertContentImage(ContentImage contentImage, Integer userId);
	
	/**
	 * Method returns list of images for page name.
	 * @param pageName
	 * @param companyId
	 * @return
	 */
	List<Igimage> getImageList(String pageName, Integer companyId);
	
	/**
	 * Method returns content text for page name.
	 * @param pageName
	 * @param companyId
	 * @return
	 */
	String getPageContent(String pageName, Integer companyId);
	
	/**
	 * Method returns list of content images for content id.
	 * @param id
	 * @return
	 */
	List<ContentImage> getImagesByContentId(Integer id);
	
	/**
	 * Method to delete image.
	 * @param contentId
	 * @param imageId
	 * @param imagePath
	 * @param userId
	 * @return
	 */
	Map<String, String> deleteImage(Integer contentId, Integer imageId, String imagePath, Integer userId);
	
	/**
	 * Method returns content object by template name.
	 * @param template
	 * @param companyId
	 * @return
	 */
	Content findContentByTemplate(String template, Integer companyId);
	
	/**
	 * Method returns list of sales regions in company.
	 * @param companyId
	 * @return
	 */
	List<SalesRegion> getAllSalesRegions(Integer companyId);
	
	/**
	 * Method returns list of sales offices in sales region.
	 * @param salesRegionId
	 * @param companyId
	 * @return
	 */
	List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId);
	
	/**
	 * Method returns content object for dashboard page.
	 * @param template
	 * @param companyId
	 * @param userId
	 * @param role
	 * @return
	 */
	Content getDashboardContent(String template, Integer companyId, Integer userId,String role);
	
	/**
	 * Method returns list of images for dashboard page.
	 * @param template
	 * @param companyId
	 * @param userId
	 * @param role
	 * @return
	 */
	List<Igimage> getDashboardImages(String template, Integer companyId, Integer userId, String role);

	Map<String, Content> findContentMapByCompanyID(int companyid);

	List<Map<String, Object>> getDistinctContentHeader(int companyId);

	Map<String, Content> getLoginPageContent();
	
}
