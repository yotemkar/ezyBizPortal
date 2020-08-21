package com.hssa.ezybiz.services.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.ContentDAO;
import com.hssa.ezybiz.dao.SalesRegionDAO;
import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.ContentImage;
import com.hssa.ezybiz.dto.Contentdetail;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.SalesRegion;
import com.hssa.ezybiz.dto.Salesoffice;
import com.hssa.ezybiz.services.CommonBO;
import com.hssa.ezybiz.services.ContentBO;
import com.hssa.ezybiz.services.ContentdetailBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.services.UserBO;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.SequenceConstants;


@Service
public class ContentBOImpl implements ContentBO {

	final static Logger LOG = LoggerFactory.getLogger(ContentBOImpl.class);

	@Autowired
	private ContentDAO contentDAO;

	@Autowired
	private SequencesBO sequencesBO;

	@Autowired
	private ContentdetailBO contentdetailBO;

	@Autowired
	private CommonBO commonBO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private SalesRegionDAO salesRegionDAO;

	@Value("${imagePath}")
	private String imagePath;

	public ContentBOImpl() {
		super();
	}

	@Override
	public List<Content> getAllContentList(Integer companyId) {
		List<Content> contents = contentDAO.getAll(companyId);
		// Map<String, String> resourceList =
		// resourcessBO.findResourcessMapByCompanyID(companyId);
		// Iterator<Entry<String, String>> itrator = resourceList.entrySet().iterator();
		// while(itrator.hasNext()) {
		// Entry<String, String> resource = itrator.next();
		// contents.add(new Content(resource.getKey(),resource.getValue(),companyId));
		//
		// }
		/*
		 * List<Igimage> imageList; Iterator<Content> iter1 = contents.iterator(); while
		 * (iter1.hasNext()) { Content content = iter1.next(); imageList = new
		 * ArrayList<Igimage>(); List<ContentImage> contentImageList =
		 * getContentImages(content.getId()); if(null != contentImageList &&
		 * contentImageList.size()>0){ Iterator<ContentImage> iter2 =
		 * contentImageList.iterator(); while (iter2.hasNext()) { ContentImage
		 * contentImage = iter2.next(); Igimage image =
		 * commonBO.findImageById(contentImage.getImageId()); try {
		 * image.setImageFile(IOUtils.toByteArray(new FileInputStream(new
		 * File(image.getImagePath())))); //imageList.add(IOUtils.toByteArray(new
		 * FileInputStream(new File(image.getImagePath())))); } catch (IOException e) {
		 * //LOG.error("File not found while getting the image"); }
		 * imageList.add(image); } } content.setImageList(imageList); }
		 */

		return contents;
	}

	public List<ContentImage> getContentImages(Integer id) {
		return contentDAO.findContentImagesById(id);
	}

	@Override
	public Map<String, String> insertContent(Content content, Integer userId, Integer companyId) {
		int insertContentCount = 0;
		int insertContentDetailCount = 0;
		Map<String, String> map = new HashMap<String, String>();
		Content tempContent = null;

		tempContent = findContentByTemplate(content.getTemplate(), companyId);
		if (null == tempContent) {

			content.setId(sequencesBO.getNextValue(SequenceConstants.CONTENT_SEQUENCE));

			Contentdetail contentDetail = new Contentdetail();
			contentDetail.setHeader(content.getHeader());
			contentDetail.setUrl(content.getUrl());

			contentDetail.setContentText(content.getContentText());

			// ContentImage contentImage = new ContentImage();
			// contentImage.setImageId(content.getId());
			// contentImage.setContentId(content.getId());
			// insertContentImageCount = insertContentImage(contentImage, userId);

			insertContentDetailCount = contentdetailBO.insertContentdetail(contentDetail, userId);

			content.setDetailTextId(contentDetail.getId());
			content.setShortTextId(contentDetail.getId());
			content.setEntityUid(UUID.randomUUID().toString());
			content.setCreator(Integer.toString(userId));
			content.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
			content.setModifier(Integer.toString(userId));
			content.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
			content.setCompanyId(companyId);
			insertContentCount = contentDAO.insertContent(content);

			if (insertContentCount == 1 && insertContentDetailCount == 1) {
				map.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.CONTENT_ADD_SUCCESS);
				map.put("id", content.getId().toString());
			} else {
				map.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.CONTENT_ADD_ERROR);
			}
		} else {
			map.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.CONTENT_NAME_EXISTS_ERROR);
		}
		return map;
	}

	private Content findContentBySalesRegion(String template, Integer salesRegionId, Integer salesOfficeId,
			Integer companyId) {
		return contentDAO.findContentBySalesRegion(template, salesRegionId, salesOfficeId, companyId);
	}

	@Override
	public Map<String, String> updateContent(Content content, Integer userId) {
		Map<String, String> map = new HashMap<String, String>();
		int updateContentCount = 0;
		int updateContentDetailCount = 0;
		Contentdetail contentDetail = new Contentdetail();
		contentDetail.setId(content.getDetailTextId());
		contentDetail.setContentText(content.getContentText());
		contentDetail.setHeader(content.getHeader());
		contentDetail.setUrl(content.getUrl());

		updateContentDetailCount = contentdetailBO.updateContentdetail(contentDetail, userId);

		content.setModifier(Integer.toString(userId));
		content.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		updateContentCount = contentDAO.updateContent(content);

		if (updateContentCount == 1 && updateContentDetailCount == 1) {
			map.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.CONTENT_UPDATE_SUCCESS);
		} else {
			map.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.CONTENT_UPDATE_ERROR);
		}

		return map;
	}

	@Override
	public Content findContentById(Integer id) {
		return contentDAO.findContentById(id);
	}

	@Override
	public Content findContentByTemplate(String template, Integer companyId) {
		return contentDAO.findContentByTemplate(template, companyId);
	}

	@Override
	public int insertContentImage(ContentImage contentImage, Integer userId) {
		contentImage.setId(sequencesBO.getNextValue(SequenceConstants.CONTENT_IMAGE_SEQUENCE));
		contentImage.setEntityUid(UUID.randomUUID().toString());
		contentImage.setCreator(Integer.toString(userId));
		contentImage.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
		contentImage.setModifier(Integer.toString(userId));
		contentImage.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		return contentDAO.insertContentImage(contentImage);
	}

	@Override
	public List<Igimage> getImageList(String pageName, Integer companyId) {
		return contentDAO.getImageList(pageName, companyId);
	}

	@Override
	public Map<String, Content> getLoginPageContent() {
		Map<String, Content> list = null;

		try {
			list = contentDAO.findContentForLoginPage();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String getPageContent(String pageName, Integer companyId) {
		return contentDAO.getPageContent(pageName, companyId);
	}

	@Override
	public List<ContentImage> getImagesByContentId(Integer id) {
		return contentDAO.getImagesByContentId(id);
	}

	@Override
	public Map<String, String> deleteImage(Integer contentId, Integer imageId, String imgPath, Integer userId) {

		Map<String, String> map = new HashMap<String, String>();
		if (contentDAO.deleteContentImage(contentId, imageId) == 1) {
			if (contentDAO.deleteImageById(imageId) == 1) {
				File tempFile = new File(imgPath);
				try {
					if (tempFile.exists()) {
						boolean deleteFlag = tempFile.delete();
						if (!deleteFlag) {
							File dir = new File(imagePath + "deleted\\");
							if (!dir.exists()) {
								dir.mkdirs();
							}
							String path = imagePath + "deleted/"
									+ imgPath.substring(imgPath.lastIndexOf("/"), imgPath.length());
							tempFile.renameTo(new File(path));
						}

					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				map.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.IMAGE_DELETE_SUCCESS);
			} else {
				map.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.IMAGE_DELETE_ERROR);
			}
		} else {
			map.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.IMAGE_DELETE_ERROR);
		}
		return map;
	}

	@Override
	public List<SalesRegion> getAllSalesRegions(Integer companyId) {
		return salesRegionDAO.getAll(companyId);
	}

	@Override
	public List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId) {

		return commonBO.getSalesOfficeByRegionId(salesRegionId, companyId);
	}

	@Override
	public Content getDashboardContent(String template, Integer companyId, Integer userId, String role) {
		List<Map<String, Object>> map = new ArrayList<>();
		if (role.equalsIgnoreCase("Customer") || role.equalsIgnoreCase("SalesPromoter")) {
			map = userBO.findUserSalesRegionAndSalesOffice(userId);
		} /*
			 * else if(role.equalsIgnoreCase("Employee") &&
			 * !role.equalsIgnoreCase("Admin")){ map =
			 * userBO.findEmployeeSalesRegionAndSalesOffice(userId); }else{
			 * 
			 * }
			 */
		Content content = null;
		if (map.size() > 0) {
			content = contentDAO.findContentBySalesRegion(template, (Integer) map.get(0).get("salesRegionId"),
					(Integer) map.get(0).get("salesOfficeId"), companyId);
		}

		if (content == null) {
			content = contentDAO.findContentByTemplate(template, companyId);
		}
		return content;

	}

	@Override
	public List<Igimage> getDashboardImages(String template, Integer companyId, Integer userId, String role) {
		List<Map<String, Object>> map = new ArrayList<>();
		List<Igimage> imageList = new ArrayList<>();
		if (role.equalsIgnoreCase("Customer") || role.equalsIgnoreCase("SalesPromoter")) {
			map = userBO.findUserSalesRegionAndSalesOffice(userId);
		}

		if (map.size() > 0) {
			imageList = contentDAO.getDashboardImages(template, (Integer) map.get(0).get("salesRegionId"),
					(Integer) map.get(0).get("salesOfficeId"), companyId);
		}

		if (imageList.isEmpty()) {
			imageList = contentDAO.getImageList(template, companyId);
		}
		return imageList;
	}

	@Override
	public Map<String, Content> findContentMapByCompanyID(int companyid) {
		Map<String, Content> list = null;

		try {
			list = contentDAO.findContentByCompanyID(companyid);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getDistinctContentHeader(int companyId) {
		// TODO Auto-generated method stub
		return contentDAO.getDistinctContentHeader(companyId);
	}

}
