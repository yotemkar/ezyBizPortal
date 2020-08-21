package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Content implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private java.sql.Timestamp createDate;
	private String creator;
	private String entityUid;
	private String modifier;
	private java.sql.Timestamp updateDate;
	private Integer optLock;
	private Integer position;
	private Integer status;
	private String template;
	private Integer detailTextId;
	private Integer shortTextId;
	private int isDeleted;

	private String contentText;
	private String header;
	private String url;

	private Integer companyId;
	private Integer salesOfficeId;
	private Integer salesRegionId;

	private String salesOfficeName;
	private String salesRegionName;

	private List<Integer> uploadedImageIds;

	private List<Igimage> imageList = new ArrayList<Igimage>();

	public Content() {
	}

	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOptLock() {
		return optLock;
	}

	public void setOptLock(Integer optLock) {
		this.optLock = optLock;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getDetailTextId() {
		return detailTextId;
	}

	public void setDetailTextId(Integer detailTextId) {
		this.detailTextId = detailTextId;
	}

	public Integer getShortTextId() {
		return shortTextId;
	}

	public void setShortTextId(Integer shortTextId) {
		this.shortTextId = shortTextId;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public List<Integer> getUploadedImageIds() {
		return uploadedImageIds;
	}

	public void setUploadedImageIds(List<Integer> uploadedImageIds) {
		this.uploadedImageIds = uploadedImageIds;
	}

	public List<Igimage> getImageList() {
		return imageList;
	}

	public void setImageList(List<Igimage> imageList) {
		this.imageList = imageList;
	}

	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the salesOfficeId
	 */
	public Integer getSalesOfficeId() {
		return salesOfficeId;
	}

	/**
	 * @param salesOfficeId
	 *            the salesOfficeId to set
	 */
	public void setSalesOfficeId(Integer salesOfficeId) {
		this.salesOfficeId = salesOfficeId;
	}

	public Integer getSalesRegionId() {
		return salesRegionId;
	}

	public void setSalesRegionId(Integer salesRegionId) {
		this.salesRegionId = salesRegionId;
	}

	public String getSalesOfficeName() {
		return salesOfficeName;
	}

	public void setSalesOfficeName(String salesOfficeName) {
		this.salesOfficeName = salesOfficeName;
	}

	public String getSalesRegionName() {
		return salesRegionName;
	}

	public void setSalesRegionName(String salesRegionName) {
		this.salesRegionName = salesRegionName;
	}

}
