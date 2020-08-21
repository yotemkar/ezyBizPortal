package com.hssa.ezybiz.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.CommonDAO;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.CrmMonthlySales;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.LabelValueVO;
import com.hssa.ezybiz.dto.Plant;
import com.hssa.ezybiz.dto.SalesUnit;
import com.hssa.ezybiz.dto.Salesgroup;
import com.hssa.ezybiz.dto.Salesoffice;
import com.hssa.ezybiz.utils.LoadJPAFQueries;


@Repository
public class CommonDAOImpl extends JdbcDaoSupport implements CommonDAO{

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public List<Contact> getAllContacts(){
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Contacts"), BeanPropertyRowMapper.newInstance(Contact.class));		
	}
	
	@Override
	public List<Plant> getAllPlants(){
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Plants"), BeanPropertyRowMapper.newInstance(Plant.class));		
	}
	
	
	
	@Override
	public List<LabelValueVO> getAllSalesOffice() {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_SalesOffice"), BeanPropertyRowMapper.newInstance(LabelValueVO.class));
	}

	@Override
	public List<LabelValueVO> getAllSaleUnit() {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_SalesUnit"), BeanPropertyRowMapper.newInstance(LabelValueVO.class));
	}

	@Override
	public int getSalesOfficeIdByCode(String code, int companyId) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesOfficeId_By_Code"), new Object[]{code}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesOfficeId_By_Code_Company"), new Object[]{code,companyId}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getSalesOfficeIdByCode(String code) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesOfficeId_By_Code"), new Object[]{code}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesOfficeId_By_Code"), new Object[]{code}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getSalesUnitIdByCode(String code, int companyId) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesUnitId_By_Code"), new Object[]{code, companyId}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesUnitId_By_Code"), new Object[]{code, companyId}, Integer.class);
		}
		return 0;
	}

	@Override
	public List<CrmMonthlySales> getTargetVsActualSalesData(Integer customerId) throws Exception {		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_CMS_ActualVsTargetData"), 
				new Object[]{customerId} ,BeanPropertyRowMapper.newInstance(CrmMonthlySales.class));
	}	

	@Override
	public List<CrmMonthlySales> getTargetVsActualSalesDataByMonths(Integer customerId, List<String> months)
			throws Exception {
		//String query = LoadJPAFQueries.getQueryById("SELECT_CMS_ActualVsTargetData_Dynamic");		
		String query = LoadJPAFQueries.getQueryById("SELECT_CMS_ActualVsTargetData_APP_Dynamic");
		if(months.size() > 0){
			int i = 0 ;
			query = query + " and ( ";
			for(String month : months){
				if(month != null){
					if( i != 0)
						query = query + " or ";
					String[] y = month.split(",");
					String x = "(year=" + y[1] + " and mothInt=" + y[0] + ")";
					query = query + x ;
					i++;
				}
			}
			query = query + ")";
		}
		query = query + " order by year, mothInt ";
		//query = query + " GROUP BY CUSTOMER_CODE, [Year],[Month] Order by CONVERT ( datetime , CONCAT([Year],'-', [Month],'-','1'), 102 )";
		System.out.println(query);
		return getJdbcTemplate().query(query, 
				new Object[]{customerId} ,BeanPropertyRowMapper.newInstance(CrmMonthlySales.class));		
	}

	@Override
	public List<LabelValueVO> getDocumentTypes(Integer companyId) throws Exception {
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_SalesDocumentType"), 
				new Object[]{companyId} ,BeanPropertyRowMapper.newInstance(LabelValueVO.class));
	}

	@Override
	public int insertIgImage(Igimage igImage) throws Exception {
		 return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("Inesrt_IGImage"), 
				new Object[]{
						igImage.getId(), igImage.getCreatedate(), 
						igImage.getCreator(), igImage.getEntityUid(),
						igImage.getModifier(), igImage.getUpdatedate(), 
						igImage.getOptlock(), igImage.getImagePath(),
						igImage.getDescription(),igImage.getUrl()
	 			});
	}

	@Override
	public int getSalesGroupIdByCode(String code) throws Exception {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesGroupId_By_Code"), new Object[]{code}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesGroupId_By_Code"), new Object[]{code}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getSalesOrganizationIdByCode(String salesOrg) throws Exception {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesOrgId_By_Code"), new Object[]{salesOrg}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesOrgId_By_Code"), new Object[]{salesOrg}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getDivisionIdByCode(String division) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_DivisionId_By_Code"), new Object[]{division}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_DivisionId_By_Code"), new Object[]{division}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getDistributionChannelIdByCode(String distChannel) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_DistChannelId_By_Code"), new Object[]{distChannel}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_DistChannelId_By_Code"), new Object[]{distChannel}, Integer.class);
		}
		return 0;
	}

	@Override
	public int getSalesAreaId(int salesOrganizationId, int divisionId, int distrributionChannelId) {
		int count = getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("COUNT_SalesAreaId_By_Code"), new Object[]{salesOrganizationId, divisionId, distrributionChannelId}, Integer.class);
		if(count > 0){
			return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_SalesAreaId_By_Code"),new Object[]{salesOrganizationId, divisionId, distrributionChannelId}, Integer.class);
		}
		return 0;
	}

	@Override
	public Igimage findImageById(Integer imageId) {
		return (Igimage)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("Get_Igimage_By_ImageId"), new Object[] {imageId}, new BeanPropertyRowMapper<Igimage>(Igimage.class));
	}

	@Override
	public int updateImage( Igimage image) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_IgImage_BY_ID"), new Object[] {
				image.getModifier(),
				image.getUpdatedate(),
				image.getDescription(),
				image.getUrl(),
				image.getId()
			});
	}

	@Override
	public int insertSalesGroup(Salesgroup salesgroup, int id, int userId) throws Exception {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_SALESGROUP"), 
				new Object[]{
						salesgroup.getId(),
						salesgroup.getCreatedate(),
						salesgroup.getCreator(),
						salesgroup.getEntityUid(),
						salesgroup.getModifier(),
						salesgroup.getUpdatedate(),
						salesgroup.getOptlock(),
						salesgroup.getCode(),
						salesgroup.getName()
	 			});
	}

	@Override
	public List<Salesoffice> getSalesOfficeByRegionId(Integer salesRegionId, Integer companyId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_SALES_OFFICE_BY_REGIONID"), new Object[]{salesRegionId,companyId} ,BeanPropertyRowMapper.newInstance(Salesoffice.class));
	}
	
	@Override
	public int updateCounter(String webSalesOrderInit, String document) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_counter_By_Job"), new Object[] {
				webSalesOrderInit, document
			});
	}

	@Override
	public CrmMonthlySales getTargetVsActualSalesByMonthAndYear(Integer customerId, String month, String year)
			throws Exception {
		try{
			return (CrmMonthlySales)getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_CMS_ActualVsTargetData_byMonth_andYear"), 
				new Object[] {customerId, year, month}, new BeanPropertyRowMapper<CrmMonthlySales>(CrmMonthlySales.class));
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<SalesUnit>getSalesUnitIdByOfficeCode(String officeCode) {
		
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_SALES_UNIT_BY_OFFICE_CODE"), new Object[]{officeCode},BeanPropertyRowMapper.newInstance(SalesUnit.class));
	}

	@Override
	public int deleteImageByImageId(int id) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_IGIMAGE_BY_ID"), new Object[]{id});

	}

	@Override
	public int deleteContentImageByImageId(int id) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_CONTENTIMAGE_BY_IGIMAGEID"), new Object[]{id});

	}
}
