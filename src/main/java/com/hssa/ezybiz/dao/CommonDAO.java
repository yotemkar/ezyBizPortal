package com.hssa.ezybiz.dao;

import java.util.List;
import java.util.Map;

import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.CrmMonthlySales;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.LabelValueVO;
import com.hssa.ezybiz.dto.Plant;
import com.hssa.ezybiz.dto.SalesUnit;
import com.hssa.ezybiz.dto.Salesgroup;
import com.hssa.ezybiz.dto.Salesoffice;


public interface CommonDAO {
	
	public List<Plant>getAllPlants();
	
	public List<Contact>getAllContacts();
	
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
	List<CrmMonthlySales> getTargetVsActualSalesData(Integer customerId) throws Exception;
	/**
	 * get all Sales Document Type
	 * @param companyId 
	 * @return
	 * @throws Exception
	 */
	public List<LabelValueVO> getDocumentTypes(Integer companyId) throws Exception;
	
	public int insertIgImage(Igimage igImage) throws Exception;

	public int getSalesGroupIdByCode(String code) throws Exception;

	public int getSalesOrganizationIdByCode(String salesOrg) throws Exception;

	public int getDivisionIdByCode(String division);

	public int getDistributionChannelIdByCode(String distChannel);

	public int getSalesAreaId(int salesOrganizationId, int divisionId, int distrributionChannelId);
	
	public int insertSalesGroup(Salesgroup salesgroup, int id, int userId) throws Exception;

	public Igimage findImageById(Integer imageId);

	public int updateImage(Igimage image);

	public List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId);

	int updateCounter(String webSalesOrderInit, String document);
	
	CrmMonthlySales getTargetVsActualSalesByMonthAndYear(Integer customerId, String month, String year) throws Exception;
	
	List<CrmMonthlySales> getTargetVsActualSalesDataByMonths(Integer customerId, List<String> months) throws Exception;

	public List<SalesUnit> getSalesUnitIdByOfficeCode(String officeCode);

	int getSalesOfficeIdByCode(String code);

	public int deleteImageByImageId(int id);

	public int deleteContentImageByImageId(int id);

}
