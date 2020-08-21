package com.hssa.ezybiz.services.impl;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.CommonDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.CrmMonthlySales;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.LabelValueVO;
import com.hssa.ezybiz.dto.Plant;
import com.hssa.ezybiz.dto.SalesUnit;
import com.hssa.ezybiz.dto.Salesgroup;
import com.hssa.ezybiz.dto.Salesoffice;
import com.hssa.ezybiz.services.CommonBO;
import com.hssa.ezybiz.services.CompanyBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.utils.CustomerPortalConstants;


@Service
public class CommonBOImpl implements CommonBO {
	
	private static final String igImageSequence = CustomerPortalConstants.SEQUENCE_IgImage;
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("orderlogger");
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private SequencesBO sequencesBO;
	
	@Autowired
	private CompanyBO companyBo;
	

	public CommonBOImpl(){
		super();
	}
	
	/*@Override
	public Vendor getAllVendorDetailByVendorId(Integer vendorId){
		return commonDAO.getAllVendorDetailVendorId(vendorId);
	}*/
	
	@Override
	public List<Plant> getAllPlantIdList() {		
		return commonDAO.getAllPlants();
	}
	
	@Override
	public List<Contact> getAllMainContactIdList() {		
		return commonDAO.getAllContacts();
	}
	

	@Override
	public List<LabelValueVO> getAllSalesOffice() {
		return commonDAO.getAllSalesOffice();
	}

	@Override
	public List<LabelValueVO> getAllSaleUnit() {
		return commonDAO.getAllSaleUnit();
	}

	@Override
	public int getSalesOfficeIdByCode(String code, int companyId) {
		return commonDAO.getSalesOfficeIdByCode(code, companyId);
	}
	
	@Override
	public int getSalesOfficeIdByCode(String code) {
		return commonDAO.getSalesOfficeIdByCode(code);
	}

	@Override
	public int getSalesUnitIdByCode(String code, int companyId) {
		return commonDAO.getSalesUnitIdByCode(code, companyId);
	}

	@Override
	public List<CrmMonthlySales> getTargetVsActualSalesData(Integer customerId, Integer totalMonths) throws Exception{
		List<String> months = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		months.add(month+","+cal.get(Calendar.YEAR));
		for(int i = 1 ; i < totalMonths ; i++){
			cal.add(Calendar.MONTH, -1) ;
			month = cal.get(Calendar.MONTH) + 1;
			months.add(month+","+cal.get(Calendar.YEAR));
		}
		return commonDAO.getTargetVsActualSalesDataByMonths(customerId, months);
	}

	@Override
	public List<CrmMonthlySales> getTargetVsActualSalesTwoMonths(Integer customerId) throws Exception {
		List<CrmMonthlySales> data = new ArrayList<CrmMonthlySales>();		
	    Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH);
	    int year = cal.get(Calendar.YEAR);	    
	    data.add(commonDAO.getTargetVsActualSalesByMonthAndYear(customerId, (month+1)+"", year+""));
	    cal.add(Calendar.MONTH, -1);
	    month = cal.get(Calendar.MONTH);
	    year = cal.get(Calendar.YEAR);
	    data.add(commonDAO.getTargetVsActualSalesByMonthAndYear(customerId, (month+1)+"", year+""));
		return data;
	}

	@Override
	public List<LabelValueVO> getDocumentTypes(Integer companyId) throws Exception{
		return commonDAO.getDocumentTypes(companyId);
	}

	@Override
	public int insertIgImage(Igimage igImage, int userId) throws Exception {
		igImage.setId(sequencesBO.getNextValue(igImageSequence));
		igImage.setCreator(Integer.toString(userId));
		igImage.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		igImage.setModifier(Integer.toString(userId));
		igImage.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		igImage.setEntityUid(UUID.randomUUID().toString());
		return commonDAO.insertIgImage(igImage);
	}

	@Override
	public int getSalesGroupIdByCode(String code)  throws Exception{
		return commonDAO.getSalesGroupIdByCode(code);
	}

	@Override
	public int getSalesOrganizationIdByCode(String salesOrg) throws Exception{
		return commonDAO.getSalesOrganizationIdByCode(salesOrg);
	}

	@Override
	public int getDevisionIdByCode(String division) throws Exception{
		return commonDAO.getDivisionIdByCode(division);
	}

	@Override
	public int getDistributionChannelIdByCode(String distChannel) throws Exception{
		return commonDAO.getDistributionChannelIdByCode(distChannel);
	}

	@Override
	public int getSalesAreaId(int salesOrganizationId, int divisionId, int distrributionChannelId) {
		return commonDAO.getSalesAreaId(salesOrganizationId, divisionId, distrributionChannelId);
	}

	@Override
	public Igimage findImageById(Integer imageId) {
		return commonDAO.findImageById(imageId);
	}

	@Override
	public int updateImage(Igimage image, Integer userId) {
		//Igimage oldIgimage = findImageById(image.getId());
		image.setModifier(Integer.toString(userId));
		image.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		return commonDAO.updateImage(image);
	}

	@Override
	public int insertSalesGroup(Salesgroup salesgroup, int id, int userId) throws Exception {
		return commonDAO.insertSalesGroup(salesgroup, id, userId);
	}

	@Override
	public Integer getCompanyId(String requestUrl) {
		// TODO Auto-generated method stub
		URL aURL;
		Integer companyId = null;
		try {
			if(requestUrl!=null){
				aURL = new URL(requestUrl);
				if(aURL.getAuthority().equals("localhost:3000"))
					return 1;
				logger.info("host name in getCompanyId ==> "+ aURL.getHost());
				companyId = companyBo.getCompanyIdByURL(aURL.getAuthority());//change to aURL.getHost() in prod
				//companyId = companyBo.getCompanyIdByURL("localhost:3000");//change to aURL.getHost() in prod
			}
		}catch(Exception e ){
			logger.error("Error occured while getting company id in getCompanyId ==> "+ e.getMessage(),e);
		}
		return companyId;
	}

	@Override
	public List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId) {
		return commonDAO.getSalesOfficeByRegionId(salesRegionId, companyId);
	}

	@Override
	public int updateCounter() {
		commonDAO.updateCounter("W1%", "acc.order.document.number");
		commonDAO.updateCounter("W2%", "acl.order.document.number");
			
		return 1;
	}

	@Override
	public List<SalesUnit> getSalesUnitIdByOfficeCode(String officeCode) {
		// get sales Unit ID by office code
		return commonDAO.getSalesUnitIdByOfficeCode(officeCode); 
	}

	@Override
	public int deleteImageByImageId(int id) {
		if(commonDAO.deleteContentImageByImageId(id)>0)
			return commonDAO.deleteImageByImageId(id);
		else return 0;
		
	}

	
}
