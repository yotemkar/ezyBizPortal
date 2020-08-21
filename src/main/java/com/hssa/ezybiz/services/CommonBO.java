package com.hssa.ezybiz.services;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.CrmMonthlySales;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.LabelValueVO;
import com.hssa.ezybiz.dto.Plant;
import com.hssa.ezybiz.dto.SalesUnit;
import com.hssa.ezybiz.dto.Salesgroup;
import com.hssa.ezybiz.dto.Salesoffice;


public interface CommonBO {
	
	
	
	/*public Vendor getAllVendorDetailByVendorId(Integer vendorId);*/
	
	/**
	 * Returns list of plants.
	 */
	
	public List<Plant> getAllPlantIdList();
	
	
	/**
	 * Returns list of contacts.
	 */
	
	public List<Contact> getAllMainContactIdList();
	
	/**
	 *  get All Sales Office
	 * @return
	 */
	public List<LabelValueVO> getAllSalesOffice();
	/**
	 * get All Sales Unit
	 * @return
	 */
	public List<LabelValueVO> getAllSaleUnit();
	/**
	 * get Sales Office ID from Code
	 * @param code
	 * @param companyId 
	 * @return
	 */
	public int getSalesOfficeIdByCode(String code, int companyId);
	/**
	 *  get Sales Unit ID from Code
	 * @param code
	 * @return
	 */
	public int getSalesUnitIdByCode(String code, int companyId);
	/**
	 *  to get target vs actual sales Data
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<CrmMonthlySales> getTargetVsActualSalesData(Integer customerId, Integer totalMonths) throws Exception;;
	/**
	 * get All Sales Document Types
	 * @return
	 */
	public List<LabelValueVO> getDocumentTypes(Integer companyId) throws Exception;
	/**
	 * insert IgImage
	 * @param igImage
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int insertIgImage(Igimage igImage, int userId) throws Exception;

	/**
	 * get SalesGroupId by salesGroupCode
	 * @param salesGroup
	 * @return
	 */
	public int getSalesGroupIdByCode(String code)  throws Exception;

	/**
	 * get Sales Organization id by code
	 * @param salesOrg
	 * @return
	 */
	public int getSalesOrganizationIdByCode(String salesOrg)  throws Exception;

	/**
	 * get Division id by code
	 * @param division
	 * @param userId
	 * @return
	 */
	public int getDevisionIdByCode(String division)  throws Exception;

	/**
	 * get distributionChannelId by Code
	 * @param distChannel
	 * @param userId
	 * @return
	 */
	public int getDistributionChannelIdByCode(String distChannel)  throws Exception;

	/**
	 * get SalesArea
	 * @param salesOrganizationId
	 * @param divisionId
	 * @param distrributionChannelId
	 * @return
	 */
	public int getSalesAreaId(int salesOrganizationId, int divisionId, int distrributionChannelId);
	
	/**
	 * get IgImage
	 * @param imageId
	 * @return
	 */
	public Igimage findImageById(Integer imageId);

	/**
	 * update IgImage
	 * @param imageId
	 * @return
	 */
	public int updateImage(Igimage image, Integer userId);
	/**
	 * get insert salesGroup
	 * @param salesgroup
	 * @param id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int insertSalesGroup(Salesgroup salesgroup, int id, int userId) throws Exception;
	
	public Integer getCompanyId(String requestUrl);


	public List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId);
	
	int updateCounter();
	
	List<CrmMonthlySales> getTargetVsActualSalesTwoMonths(Integer customerId) throws Exception;


	public List<SalesUnit> getSalesUnitIdByOfficeCode(String officeCode);


	int getSalesOfficeIdByCode(String code);


	public int deleteImageByImageId(int  id);
}
