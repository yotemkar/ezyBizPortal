package com.hssa.ezybiz.services.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.dao.ContactDAO;
import com.hssa.ezybiz.dao.CustomerDAO;
import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.CustomerDetails;
import com.hssa.ezybiz.dto.CustomerUserDto;
import com.hssa.ezybiz.dto.Customeruser;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.dto.Location;
import com.hssa.ezybiz.dto.Quotation;
import com.hssa.ezybiz.dto.UserProfile;
import com.hssa.ezybiz.dto.UserRegistrationRequest;
import com.hssa.ezybiz.services.CommonBO;
import com.hssa.ezybiz.services.CompanyBO;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.ContentBO;
import com.hssa.ezybiz.services.CustomerBO;
import com.hssa.ezybiz.services.CustomeruserBO;
import com.hssa.ezybiz.services.LocationBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.utils.Credential;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.PasswordGeneratorUtil;


@Service
public class CustomerBOImpl implements CustomerBO {

	private static final Logger logger = Logger.getLogger("CustomerBOImpl");

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private CommonBO commonBO;
	@Autowired
	private LocationBO locationBO;
	//@Autowired
	//private AddressBO addressBO;
	@Autowired
	private ContactBO contactBO;
//	@Autowired
	//private SalesareaDAO salesareaDAO;

	//@Autowired
	//private CustomergroupBO customergroupBO;

	@Autowired
	private CompanyBO companyBO;

	//@Autowired
	//EmailServiceHelper emailServiceHelper;

	//@Autowired
	//UserRegistrationRequestBO userRegistrationBO;

	@Autowired
	CustomerBO customerBO;

	@Autowired
	CustomeruserBO customeruserBO;

	// Added by Ravindrak
	//@Autowired
//	OrderBo orderBo;

	//@Autowired
	//PricegliderFromService pricegliderService;
	
	//@Autowired
//	CustomerQuotationPI customerQuotationPI;

	private static final String customerSequence = CustomerPortalConstants.SEQUENCE_Customer;
	private static final String distributionChannelSequence = CustomerPortalConstants.SEQUENCE_DistributionChannel;
	private static final String salesOrgSequence = CustomerPortalConstants.SEQUENCE_SalesOrg;
	private static final String salesOfficeSequence = CustomerPortalConstants.SEQUENCE_SalesOffice;
	private static final String salesAreaSequence = CustomerPortalConstants.SEQUENCE_SalesArea;
	private static final String salesGroupSequence = CustomerPortalConstants.SEQUENCE_SalesGroup;
	@Autowired
	private SequencesBO sequencesBO;
	//@Autowired
	//private CustomerDetailsSAPProcessor customerDetailsSAPProcessor;

	@Autowired
	private ContentBO contentBO;

	/*@Value("${customerexcel.columnnumber}")
	private String customerExcelMaxNoColumn;

	@Value("${customerexcel.columns}")
	private String customerExcelColumns;

	@Value("${delcrederexcel.columns}")
	private String delcrederExcelColumns;

	@Value("${delcrederexcel.columnnumber}")
	private String delcrederExcelMaxNoColumn;

	@Value("${employeeexcel.columnseprator}")
	private String customerExcelColumnSeprator;

	@Value("${sap.customerDetailsFileName}")
	private String customerDetailsFileName;*/

	//@Autowired
	//DistributionChannelSAPProcessor distChannProcessor;

/*	@Value("${sap.distributionChannelFileName}")
	private String distChannelFile;*/

//	@Autowired
	//private DistributionchannelDAO distributionchannelDAO;
	//@Autowired
//	SalesOrgDetailsSAPProcessor salesOrgDetailsSAPProcessor;
	/*@Value("${sap.salesOrgDetailsFileName}")
	private String salesOrgDetailsFileName;
*/
	//@Autowired
	//SalesorganisationDAO salesorganisationDAO;

	//@Autowired
//	SalesOfficeDetailsSAPProcessor salesOfficeDetailsSAPProcessor;

	/*@Value("${sap.salesOfficeDetailsFileName}")
	private String salesOfficeDetailsFileName;
*/
	//@Autowired
	//SalesOfficeDAO salesOfficeDAO;
	//@Autowired
//	SalesGroupDetailsSAPProcessor salesGroupDetailsSAPProcessor;

	/*@Value("${sap.salesGroupDetailsFileName}")
	private String salesGroupDetailsFileName;*/
	//@Autowired
	//DivisionDetailsSAPProcessor divisionDetailsSAPProcessor;

	/*@Value("${sap.divisionFileName}")
	private String devisionFileName;
*/
	//@Autowired
	//DivisionBo divisionBo;

	//@Autowired
	//SMSServiceHelper sMSServiceHelper;

	// @Autowired
	// private UserDetailsService userDetailsService;

	/// Added by Ravindra on 27-02-2018
	//@Autowired
	//CustomerWBflagUpdateProcessor wbFlagUpdateProcessor;
	/*@Value("${sap.customerWBFlagFileName}")
	private String customerWBFlagFileName;*/

	public CustomerBOImpl() {
		super();
	}

	@Override
	public List<Customer> search(Customer customer) {
		return customerDAO.search(customer);
	}

	@Override
	public List<Customer> getAllCustomerList(Integer companyId) {
		return customerDAO.getAll(companyId);
	}

	@Override
	public Map<String, String> updateCustomer(Customer customer, Integer userId,boolean salesPromoterUpdateFlag) {

		Map<String, String> statusMap = new HashMap<String, String>();

		int contactUpdateStatus = 0;
		int customerUpdateStatus = 0;
		Customer oldCustomer = findCustomerByID(customer.getId());

		if (customer.getEmail() != null && !customer.getEmail().equals(oldCustomer.getEmail())) {
			if (contactBO.isEmailIdExist(customer.getEmail()) > 0) {
				logger.error("email is already exist");
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.CUSTOMER_Email_ERROR);
				return statusMap;
			}
		}
		customer.setModifier(Integer.toString(userId));
		customer.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		if (customer.getAccessPortal() == 0 && customer.getAccessPortal().equals(oldCustomer.getAccessPortal())) {
			customer.setDeactivationDate(new Timestamp((new java.util.Date()).getTime()));
		} else {
			customer.setDeactivationDate(oldCustomer.getDeactivationDate());

		}

		if (customer.getAccessPortal().equals(1)
				&& !(customer.getAccessPortal().equals(oldCustomer.getAccessPortal()))) {
			customer.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
		} else {
			customer.setActivationDate(oldCustomer.getActivationDate());

		}

		if (!customer.getAccessPortal().equals(oldCustomer.getAccessPortal())) {
			customer.setStatusUpdatedBy(Integer.toString(userId));
		} else {
			customer.setStatusUpdatedBy(oldCustomer.getStatusUpdatedBy());
		}
		Contact oldContact = contactDAO.findContactByID(customer.getMainContactId());

		Contact contact = new Contact();

		contact.copy(oldContact);
		if (customer.getEmail() != null && (!customer.getEmail().equals("")))
			contact.setEmail(customer.getEmail());
		if (customer.getMobileNumber() != null && (!customer.getMobileNumber().equals("")))
			contact.setMobile(customer.getMobileNumber());

		contactUpdateStatus = contactDAO.updateContact(oldContact, contact, oldContact.getId(), userId);
		customerUpdateStatus = customerDAO.updateCustomer(oldCustomer, customer, customer.getId(), userId,salesPromoterUpdateFlag);
		if (contactUpdateStatus == 1 && customerUpdateStatus == 1) {
			statusMap.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.CUSTOMER_UPDATE_SUCCESS);
			logger.info(CustomerPortalConstants.CUSTOMER_UPDATE_SUCCESS + customer.getCode());
		} else {
			statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.CUSTOMER_UPDATE_ERROR);
			logger.error(CustomerPortalConstants.CUSTOMER_UPDATE_ERROR + customer.getCode());
		}
		return statusMap;
	}

	@Override
	public Customer findCustomerByID(Integer id) {
		return customerDAO.findCustomerByID(id);
	}

	@Override
	public Customer findCustomerByCustomerCodeAndAccessKey(String code, Integer companyId)
			throws EmptyResultDataAccessException {
		return customerDAO.findCustomerByCustomerCodeAndAccessKey(code, companyId);
	}

	@Override
	public List<Customer> getCustomerListforEmployee(int employeeId) {
		return customerDAO.getCustomerListforEmployee(employeeId);
	}

	@Override
	public Customer findCustomerByUserId(int userId) {
		return customerDAO.findCustomerByUserId(userId);
	}

	@Override
	public UserProfile getUserProfileDetail(int userId) throws Exception {
		return customerDAO.getUserProfileDetail(userId);
	}

	/*@Override
	public Map<String, Object> uploadCustomer(File file, int userId, Company company, boolean isSalespromotor)
			throws Exception {
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			FileInputStream fileIs = new FileInputStream(file);
			// Added by Ravindra on 27-02-2018
			List<String> customerCodeList = new ArrayList<String>();
			List<Integer> customerIdList = new ArrayList<Integer>();
			// Create Workbook instance holding reference to .xlsx file
			Workbook workbook = null;
			if (file.getName().endsWith(".xls") || file.getName().endsWith(".XLS")) {
				workbook = new HSSFWorkbook(fileIs);
			} else if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".XLSX")) {
				workbook = new XSSFWorkbook(fileIs);
			} else {
				returnMap.put(CustomerPortalConstants.ERROR, "Received file does not have a standard excel extension.");
				fileIs.close();
				return returnMap;
			}

			// Get first/desired sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);
			if (null == sheet) {
				returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
				workbook.close();
				fileIs.close();
				return returnMap;
			}
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
			Row headerRow = sheet.getRow(0);
			// int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();

			if (null == headerRow) {
				returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
				workbook.close();
				fileIs.close();
				return returnMap;
			}
			// int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();

			
			 * if (!customerExcelMaxNoColumn.equals(Integer.toString(columnNumber))) {
			 * returnMap.put(CustomerPortalConstants.ERROR,
			 * "Excel File is not as per Provided Template"); workbook.close(); return
			 * returnMap; }
			 

			List<String> columns = Arrays.asList(customerExcelColumns.split(customerExcelColumnSeprator));

			Iterator<Cell> headerCellIterator = headerRow.cellIterator();
			StringBuilder missingColumns = new StringBuilder();
			while (headerCellIterator.hasNext()) {
				Cell cell = headerCellIterator.next();
				if (null != cell.getStringCellValue() && !cell.getStringCellValue().trim().toUpperCase().isEmpty()) {
					if (!columns.contains(cell.getStringCellValue().toUpperCase())) {
						missingColumns.append(cell.getStringCellValue()).append(",");
					}
				}
			}

			if (missingColumns.length() > 0) {
				returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
				workbook.close();
				return returnMap;
			}
			int successCount = 0;
			int errorCount = 0;
			int rowCount = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if ((null == row.getCell(0) || row.getCell(0).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
						&& (null == row.getCell(1) || row.getCell(1).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
						&& (null == row.getCell(2)
								|| row.getCell(2).getCellTypeEnum().compareTo(CellType.BLANK) == 0)) {
					break;
				}
				if (rowCount <= 1) {
					rowCount++;
					continue;
				}
				int rows = sheet.getPhysicalNumberOfRows();
				if (rowCount >= rows) {
					break;
				}
				// For each row, iterate through all the columns
				// Iterator<Cell> cellIterator = row.cellIterator();
				int i = 0;
				StringBuilder errorMessage = new StringBuilder();
				Customer customer = new Customer();
				customer.setCompanyId(company.getId());
				for (int index = 0; index < Integer.parseInt(customerExcelMaxNoColumn); index++) {
					Cell cell = row.getCell(index);
					if (i >= Integer.parseInt(customerExcelMaxNoColumn)) {
						break;
					}
					String status = validateCell(headerRow.getCell(i++).getStringCellValue(), cell, customer);
					if (!status.equals(CustomerPortalConstants.SUCCESS)) {
						errorMessage.append(status).append(",");
					}
				}
				if (errorMessage.length() > 0) {
					if (errorMessage.toString().contains("Customer Code Already Exist")) {
						errorMessage = new StringBuilder();
						errorMessage.append(checkExistingUserAndRegister(customer.getCode(), company,
								customer.getMobileNumber(), customer.getEmail(), customer.getEmployeeEmail()));
						errorMessage.append(",");
					}
					ErrorInfo errorInfo = new ErrorInfo();
					errorInfo.setId(row.getRowNum() + 1);
					errorInfo.setMessage(errorMessage.substring(0, errorMessage.length() - 1));
					errorList.add(errorInfo);
					errorCount++;
				} else {
					int count = insertCustomerDetail(company, customer.getCode(), customer.getEmail(),
							customer.getSalesUnit(), customer.getMobileNumber(), userId, isSalespromotor,
							customer.getEmployeeEmail());
					if (count == CustomerPortalConstants.CUSTOMER_NOTFOUND) {
						ErrorInfo errorInfo = new ErrorInfo();
						errorInfo.setId(row.getRowNum() + 1);
						errorInfo.setMessage("Customer code is invalid.");
						errorList.add(errorInfo);
						errorCount++;
					} else {
						successCount++;
						customerCodeList.add(customer.getCode());
						customerIdList.add(customer.getId());

					}
				}
			}
			if (customerCodeList.size() > 0) {
				// wbFlagUpdateProcessor.updateWBFlag(customerWBFlagFileName, customerCodeList);
				// // Added by Ravindra on
				// 27-02-2018
				// this.updateShiptoCode(customerIdList);// Added by Ravindra on 16-03-18
			}

			workbook.close();
			fileIs.close();

			returnMap.put("successCount", successCount);
			returnMap.put("errorCount", errorCount);
			returnMap.put("errorMap", errorList);

			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	// Added by Ravindra on 16-03-18
	/*
	 * public void updateShiptoCode(List<Integer> customerIdList){ try{
	 * 
	 * for(Integer customerId: customerIdList){
	 * System.out.println("Refreshing .... ship to party....");
	 * orderBo.getMissingShipToParty(customerId); } }catch(Exception ex){
	 * ex.printStackTrace(); } }
	 */

	/*@Override
	public Map<String, Object> uploadDelcreder(File file, int userId, Company company) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FileInputStream fileIs = new FileInputStream(file);

		// Create Workbook instance holding reference to .xlsx file
		Workbook workbook = null;
		if (file.getName().endsWith(".xls") || file.getName().endsWith(".XLS")) {
			workbook = new HSSFWorkbook(fileIs);
		} else if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".XLSX")) {
			workbook = new XSSFWorkbook(fileIs);
		} else {
			returnMap.put(CustomerPortalConstants.ERROR, "Received file does not have a standard excel extension.");
			fileIs.close();
			return returnMap;
		}

		// Get first/desired sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);
		if (null == sheet) {
			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
			workbook.close();
			fileIs.close();
			return returnMap;
		}
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
		Row headerRow = sheet.getRow(0);
		// int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();

		if (null == headerRow) {
			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
			workbook.close();
			fileIs.close();
			return returnMap;
		}
		// int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();

		
		 * if (!customerExcelMaxNoColumn.equals(Integer.toString(columnNumber))) {
		 * returnMap.put(CustomerPortalConstants.ERROR,
		 * "Excel File is not as per Provided Template"); workbook.close(); return
		 * returnMap; }
		 

		List<String> columns = Arrays.asList(delcrederExcelColumns.split(customerExcelColumnSeprator));

		Iterator<Cell> headerCellIterator = headerRow.cellIterator();
		StringBuilder missingColumns = new StringBuilder();
		while (headerCellIterator.hasNext()) {
			Cell cell = headerCellIterator.next();
			if (null != cell.getStringCellValue() && !cell.getStringCellValue().trim().toUpperCase().isEmpty()) {
				if (!columns.contains(cell.getStringCellValue().toUpperCase())) {
					missingColumns.append(cell.getStringCellValue()).append(",");
				}
			}
		}

		if (missingColumns.length() > 0) {
			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
			workbook.close();
			return returnMap;
		}
		int successCount = 0;
		int errorCount = 0;
		int rowCount = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if ((null == row.getCell(0) || row.getCell(0).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(1) || row.getCell(1).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(2) || row.getCell(2).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(3) || row.getCell(3).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(4) || row.getCell(4).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(5) || row.getCell(5).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(6) || row.getCell(6).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(7) || row.getCell(7).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(8) || row.getCell(8).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
					&& (null == row.getCell(9) || row.getCell(9).getCellTypeEnum().compareTo(CellType.BLANK) == 0)) {
				break;
			}
			if (rowCount <= 0) {
				rowCount++;
				continue;
			}
			int rows = sheet.getPhysicalNumberOfRows();
			if (rowCount >= rows) {
				break;
			}
			// For each row, iterate through all the columns
			// Iterator<Cell> cellIterator = row.cellIterator();
			int i = 0;
			StringBuilder errorMessage = new StringBuilder();
			Customer customer = new Customer();
			customer.setCompanyId(company.getId());
			for (int index = 0; index < Integer.parseInt(delcrederExcelMaxNoColumn); index++) {
				Cell cell = row.getCell(index);
				if (i >= Integer.parseInt(delcrederExcelMaxNoColumn)) {
					break;
				}
				String status = validateCell(headerRow.getCell(i++).getStringCellValue(), cell, customer);
				if (!status.equals(CustomerPortalConstants.SUCCESS)) {
					errorMessage.append(status).append(", ");
				}
			}
			if (errorMessage.length() > 0) {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setId(row.getRowNum() + 1);
				errorInfo.setMessage(errorMessage.substring(0, errorMessage.length() - 1));
				errorList.add(errorInfo);
				errorCount++;
			} else {
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setCustomerCode(customer.getCode());
				int count = insertDelcrederDetail(company, userId, customer);
				if (count == CustomerPortalConstants.CUSTOMER_NOTFOUND) {
					ErrorInfo errorInfo = new ErrorInfo();
					errorInfo.setId(row.getRowNum() + 1);
					errorInfo.setMessage("Customer code is invalid.");
					errorList.add(errorInfo);
					errorCount++;
				} else {
					successCount++;
				}
			}
		}
		workbook.close();
		fileIs.close();

		returnMap.put("successCount", successCount);
		returnMap.put("errorCount", errorCount);
		returnMap.put("errorMap", errorList);

		return returnMap;
	}

	private String validateCell(String cellHeader, Cell cell, Customer customer) {
		String returnMessage = CustomerPortalConstants.SUCCESS;
		switch (CustomerUploadHeader.getEnumByValue(cellHeader)) {
		case CODE:
			try {
				Double customerCode = cell.getNumericCellValue();
				if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					customerCode = new Double(cell.getNumericCellValue());
				} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					customerCode = Double.parseDouble(cell.getStringCellValue());
				}
				if (customerCode > 0) {
					if (isCustomerCodeExist(Long.toString(customerCode.longValue()), customer.getCompanyId())) {
						returnMessage = "Customer Code Already Exist";
						customer.setCode(Long.toString(customerCode.longValue()));
						break;
					}
					customer.setCode(Long.toString(customerCode.longValue()));
				} else {
					returnMessage = "Invalid Customer Code";
					break;
				}
				break;
			} catch (Exception e) {
				returnMessage = "Invalid Customer Code";
				break;
			}
		case EMAIL:
			try {
				String email = cell.getStringCellValue();
				if (email != null && !email.equals("")) {
					if (!ValidatorUtil.validateEmail(email)) {
						// returnMessage = "Invalid Email";
						customer.setEmail("");
						break;
					}
					if (contactDAO.isEmailIdExist(email) > 0) { // modified by Ravindra
						returnMessage = "Email already exist in system";
						customer.setEmail(email);
						break;
					}
					if (contactBO.isEmailExistForUser(email, customer.getCompanyId())) { // modified by Ravindra
						returnMessage = "Email already exist in system";
						break;
					}
				}
				customer.setEmail(email);

			} catch (Exception e) {
				// returnMessage = "Invalid Email";
				customer.setEmail("");
				break;
			}
			break;
		case MOBILE:
			try {
				Double mobile = cell.getNumericCellValue();
				if (mobile > 0) {
					if (!ValidatorUtil.validateMobile(Long.toString(mobile.longValue()))) {
						returnMessage = "Invalid Mobile";
						break;
					}
					if (contactBO.isMobileExistForCustomer(Long.toString(mobile.longValue()),
							customer.getCompanyId())) {
						returnMessage = "Mobile Number Already Exist";
						customer.setMobileNumber(Long.toString(mobile.longValue()));
						break;
					}
					if (contactBO.isMobileExistForUser(Long.toString(mobile.longValue()), customer.getCompanyId())) {
						returnMessage = "Mobile Number Already Exist";
						customer.setMobileNumber(Long.toString(mobile.longValue()));
						break;
					}
					customer.setMobileNumber(Long.toString(mobile.longValue()));
				} else {
					returnMessage = "Invalid Mobile";
				}
			} catch (Exception e) {
				logger.error(e);
				returnMessage = "Invalid Mobile";
				break;
			}
			break;

		case SALESUNIT:
			try {
				String salesUnitCode = "";
				if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					salesUnitCode = Long.toString((new Double(cell.getNumericCellValue())).longValue());
				} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					salesUnitCode = cell.getStringCellValue();
				}
				if (salesUnitCode.isEmpty()) {
					returnMessage = "Invalid Sales Unit";
					break;
				}
				int salesUnitId = commonBO.getSalesUnitIdByCode(salesUnitCode, customer.getCompanyId());
				if (salesUnitId > 0) {
					customer.setSalesUnit(Integer.toString(salesUnitId));
				} else {
					returnMessage = "Invalid Sales Unit";
					break;
				}
			} catch (Exception e) {
				returnMessage = "Invalid Sales Unit";
				break;
			}
			break;
		case SALESOFFICE:
			try {
				String salesOfficeCode = "";
				if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					salesOfficeCode = Long.toString((new Double(cell.getNumericCellValue())).longValue());
				} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					salesOfficeCode = cell.getStringCellValue();
				}
				if (salesOfficeCode.isEmpty()) {
					returnMessage = "Invalid Sales Office";
					break;
				}
				int salesOfficeId = commonBO.getSalesOfficeIdByCode(salesOfficeCode, customer.getCompanyId());
				if (salesOfficeId > 0) {
					customer.setSalesOffice(salesOfficeCode);
				} else {
					returnMessage = "Invalid Sales Office";
					break;
				}
			} catch (Exception e) {
				returnMessage = "Invalid Sales Office";
				break;
			}
			break;
		case NAME:
			String name = cell.getStringCellValue();
			if (name == null || name.equals("")) {
				returnMessage = "Invalid Customer Name";
				break;
			}
			customer.setCustomerName(name);
			break;
		case STREETNAME:
			String streetName = cell.getStringCellValue();
			if (streetName == null || streetName.equals("")) {
				returnMessage = "Invalid Street Name";
				break;
			}
			customer.setStreetName(streetName);
			break;
		case STREETSIDE:
			String streetSide = cell.getStringCellValue();
			if (streetSide == null || streetSide.equals("")) {
				returnMessage = "Invalid Street Side";
				break;
			}
			customer.setStreetSide(streetSide);
			break;
		case CITY:
			String city = cell.getStringCellValue();
			if (city == null || city.equals("")) {
				returnMessage = "Invalid City";
				break;
			}
			customer.setCity(city);
			break;
		case POSTALCODE:
			String postalCode = "";
			if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
				postalCode = Long.toString((new Double(cell.getNumericCellValue())).longValue());
			} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				postalCode = cell.getStringCellValue();
			}
			if (postalCode == null || postalCode.equals("")) {
				returnMessage = "Invalid Postal Code";
				break;
			}
			customer.setPostalCode(postalCode);
			break;
		case STATE:
			String state = cell.getStringCellValue();
			if (state == null || state.equals("")) {
				returnMessage = "Invalid State";
				break;
			}
			customer.setState(state);
			break;
		case DCODE:
			try {
				Double customerCode = cell.getNumericCellValue();
				if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					customerCode = new Double(cell.getNumericCellValue());
				} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					customerCode = Double.parseDouble(cell.getStringCellValue());
				}
				if (customerCode > 0) {
					if (isDelcrederCodeExist(Long.toString(customerCode.longValue()), customer.getCompanyId())) {
						returnMessage = "Customer Code Already Exist";
						break;
					}
					customer.setCode(Long.toString(customerCode.longValue()));
				} else {
					returnMessage = "Invalid Customer Code";
					break;
				}
				break;
			} catch (Exception e) {
				returnMessage = "Invalid Customer Code";
				break;
			}
		case OFFICERID:
			try {
				Double officerId = 0d;
				if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					officerId = new Double(cell.getNumericCellValue());
				} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					// String value = cell.getStringCellValue();
					try {
						officerId = Double.parseDouble(cell.getStringCellValue());
					} catch (Exception e) {
						officerId = 0d;
					}
				}
				customer.setEmployeeId(officerId.intValue());
			} catch (Exception e) {
				returnMessage = "Invalid Officer Id";
			}
			break;
		case OFFICEREMAIL:
			try {
				String email = cell.getStringCellValue();
				if (email != null && !email.equals("")) {
					if (!ValidatorUtil.validateEmail(email)) {
						returnMessage = "Invalid Email";
						break;
					}
				}
				customer.setEmployeeEmail(email);

			} catch (Exception e) {
				returnMessage = "Invalid Email";
				break;
			}
			break;
		default:
			break;
		}
		return returnMessage;
	}*/

	private int insertDelcrederDetail(Company company, int userId, Customer customer) throws Exception {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCity1(customer.getCity());
		customerDetails.setPostalCode(customer.getPostalCode());
		customerDetails.setRegion(customer.getState());

		Location location = new Location();
		int insertCount = 0;
		//insertCount = insertLocation(customerDetails, userId, location);
		if (insertCount == 0) {
			return 0;
		}

		customerDetails.setCity2(customer.getStreetSide());
		customerDetails.setStreet(customer.getStreetName());
		insertCount = 0;
		/* insert address */
		//Address address = new Address();
		//insertCount = insertAddress(customerDetails, userId, location, address);
		if (insertCount == 0) {
			return 0;
		}
		insertCount = 0;
		/* insert contact */
		Contact contact = new Contact();
		contact.setEmail(customer.getEmail());
		contact.setMobile(customer.getMobileNumber());
		//insertCount = insertContact(customerDetails, userId, address, contact);
		if (insertCount == 0) {
			return 0;
		}
		insertCount = 0;
		customer.setIsDirectPosting(0);
		customer.setMainContactId(contact.getId());
		int salesOfficeId = commonBO.getSalesOfficeIdByCode(customer.getSalesOffice(), company.getId());
		customer.setSalesOfficeId(salesOfficeId);
		customer.setSalesGroupId(null);
		customer.setCustomerGroupId(null);
		customer.setSalesAreaId(null);
		customer.setSalesPromoter(0);
		final String pw = PasswordGeneratorUtil.newPasswordGenerator();
		final String encyptpw = Credential.SHA1.digest("" + System.currentTimeMillis(), pw);
		customer.setAccessKey(encyptpw);
		customer.setAccessPortal(1);
		customer.setSalesAreaId(9);
		customer.setCustomerGroupId(3);
		customer.setSalesGroupId(109);
		customer.setDelcreder(1);
		customer.setShowSalesData(1);
		//insertCount = insertCustomer(customer, userId);

		// Registration Code

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setCity(customerDetails.getCity1());
		registrationRequest.setPostalCode(customerDetails.getPostalCode());
		registrationRequest.setState(customerDetails.getRegion());
		registrationRequest.setCountry("");

		registrationRequest.setHouseNumber("");
		registrationRequest.setFloor("");
		registrationRequest.setStreetSide("");
		registrationRequest.setStreetName("");

		registrationRequest.setEmail(customer.getEmail());
		registrationRequest.setPhone("");
		registrationRequest.setMobile(customer.getMobileNumber());

		registrationRequest.setLoginId(customer.getCode());
		registrationRequest.setEmail(customer.getEmail());
		registrationRequest.setGender("100");
		registrationRequest.setGivenName(customer.getCustomerName());
		registrationRequest.setFamilyName(customerDetails.getName2());
		registrationRequest.setDateOfBirth(new Timestamp(new Date().getTime()));
		registrationRequest.setCompanyId(company.getId());
		registrationRequest.setPhone(customer.getMobileNumber());
		registrationRequest.setMaritalStatus("110");

		registrationRequest.setPosition("103");
		registrationRequest.setUserFunction("107");

		//userRegistrationBO.registerUser(registrationRequest, customer, company);
		//emailServiceHelper.sendCustomerUpload(customer, company, registrationRequest.getPassword());
		// emailServiceHelper.setMailToOfficerForCustomerUpload(customer, company,
		// employeeEmail, registrationRequest.getPassword());

		// send sms to customer

		StringBuffer sb = new StringBuffer();
		sb.append("Dear Customer " + customer.getCustomerName());
		sb.append(System.getProperty("line.separator"));
		sb.append("Welcome to" + (company.getId() == 3 ? " Ambuja Parivar" : " ACC Parivar"));
		sb.append(System.getProperty("line.separator"));
		sb.append("Use your Dealer code as Loginid and Password as " + registrationRequest.getPassword());
		sb.append(System.getProperty("line.separator"));
		sb.append("for Login to WebSales portal.");
		//sMSServiceHelper.sendSMS(customer.getMobileNumber(), sb.toString(), company.getId());

		/*
		 * if(insertCount > 0){ emailServiceHelper.sendCustomerAccessKey(pw, customer,
		 * company); }
		 */
		return insertCount;
	}

	private String checkExistingUserAndRegister(String customerCode, Company company, String mobile, String email,
			String employeeEmail) {
		String message = "Customer Code Alredy Exist, ";
		try {
			// check email at user level
			if (contactBO.isEmailExistForUser(email, company.getId())) { // Modified by Ravindra to add compay id
				message += "Email Already Exist, ";
			}

			// check mobile at user level
			if (contactBO.isMobileExistForUser(mobile, company.getId())) {
				message += "Mobile Already Exist, ";
			}

			if (message.equals("Customer Code Alredy Exist, ")) {
				Customer customer = customerBO.findCustomerByCustomerCodeAndAccessKey(customerCode, company.getId());

				Contact oldContact = contactDAO.findContactByID(customer.getMainContactId());

				Contact contact = new Contact();

				contact.copy(oldContact);

				if (email != null && !email.equals("")) {
					contact.setEmail(email);
				}
				contact.setMobile(mobile);

				contactDAO.updateContact(oldContact, contact, oldContact.getId(), 0);
				customer.setDelcreder(0);
				List<Customeruser> users = customeruserBO.findCustomeruserByFKCustomerid(customer.getId());
				if (users.size() == 0) {
					if (registerCustomer(mobile, email, company, customer, employeeEmail)) {
						message = "Customer Registered";
					} else
						message = "Some Error Occured";
				} else
					message = "Customer Code Alredy Exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Some Error Occurred";
		}
		return message;
	}

	private boolean registerCustomer(String mobileNumber, String email, Company company, Customer customer,
			String employeeEmail) {
		try {
			//CustomerDetails customerDetails = customerDetailsSAPProcessor
				//	.getCustomerDetailsFromSAP(customerDetailsFileName, customer.getCode());
			UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
			//registrationRequest.setCity(customerDetails.getCity1());
			//registrationRequest.setPostalCode(customerDetails.getPostalCode());
			//registrationRequest.setState(customerDetails.getRegion());
			registrationRequest.setCountry("");

			registrationRequest.setHouseNumber("");
			registrationRequest.setFloor("");
			registrationRequest.setStreetSide("");
			registrationRequest.setStreetName("");

			registrationRequest.setEmail(email);
			registrationRequest.setPhone("");
			registrationRequest.setMobile(mobileNumber);

			registrationRequest.setLoginId(customer.getCode());
			registrationRequest.setEmail(email);
			registrationRequest.setGender("100");
			//registrationRequest.setGivenName(customerDetails.getCustomerName());
			//registrationRequest.setFamilyName(customerDetails.getName2());
			registrationRequest.setDateOfBirth(new Timestamp(new Date().getTime()));
			registrationRequest.setCompanyId(company.getId());
			registrationRequest.setPhone(mobileNumber);
			registrationRequest.setMaritalStatus("110");

			registrationRequest.setPosition("103");
			registrationRequest.setUserFunction("107");

			//userRegistrationBO.registerUser(registrationRequest, customer, company);
			//emailServiceHelper.sendCustomerUpload(customer, company, registrationRequest.getPassword());
			//emailServiceHelper.setMailToOfficerForCustomerUpload(customer, company, employeeEmail,
				//	registrationRequest.getPassword());

			// send sms to customer

			StringBuffer sb = new StringBuffer();
			sb.append("Dear Customer " + customer.getCustomerName());
			sb.append(System.getProperty("line.separator"));
			sb.append("Welcome to" + (company.getId() == 3 ? " Ambuja Parivar" : " ACC Parivar"));
			sb.append(System.getProperty("line.separator"));
			sb.append("Use your Dealer code as Loginid and Password as " + registrationRequest.getPassword());
			sb.append(System.getProperty("line.separator"));
			sb.append("for Login to WebSales portal.");
			System.out.println(sb);
			//sMSServiceHelper.sendSMS(mobileNumber, sb.toString(), company.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*private int insertCustomerDetail(Company company, String customerCode, String email, String salesUnitId,
			String mobileNumber, int userId, boolean isSalespromotor, String employeeEmail) throws Exception {
	//	CustomerDetails customerDetails = customerDetailsSAPProcessor.getCustomerDetailsFromSAP(customerDetailsFileName,
				customerCode);

		salesUnitId = commonBO.getSalesUnitIdByCode(customerDetails.getSalesUnit(), company.getId()) + "";
		if (customerDetails.getCustomerName() == null || customerDetails.getCustomerName().isEmpty()) {
			return CustomerPortalConstants.CUSTOMER_NOTFOUND;
		}

		int id = companyBO.validateCompanyById(customerDetails.getCompanyCode());
		if (id != company.getId()) {
			return CustomerPortalConstants.CUSTOMER_NOTFOUND;
		}

		// CustomerDetails customerDetails = new CustomerDetails();
		 insert location 
		Location location = new Location();
		int insertCount = 0;
		//insertCount = insertLocation(customerDetails, userId, location);
		if (insertCount == 0) {
			return 0;
		}
		insertCount = 0;
		 insert address 
		//Address address = new Address();
		//insertCount = insertAddress(customerDetails, userId, location, address);
		if (insertCount == 0) {
			return 0;
		}
		insertCount = 0;
		 insert contact 
		Contact contact = new Contact();
		contact.setEmail(email);

		contact.setMobile(mobileNumber);
		// changed later
		// contact.setMobile(customerDetails.getMobile());
		//insertCount = insertContact(customerDetails, userId, address, contact);
		if (insertCount == 0) {
			return 0;
		}
		insertCount = 0;
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setIsDirectPosting(0);
		if (isSalespromotor) {
			customer.setSalesPromoter(1);
		} else {
			customer.setSalesPromoter(0);
		}
		customer.setMainContactId(contact.getId());
		customer.setDelcreder(0);

		//int salesOfficeId = commonBO.getSalesOfficeIdByCode(customerDetails.getSalesOffice(), company.getId());
		//if (salesOfficeId > 0) {
		//	customer.setSalesOfficeId(salesOfficeId);
		//} else {
			Salesoffice salesOffice = new Salesoffice();
			salesOffice.setSalesunitid(Integer.parseInt(salesUnitId));
			//salesOffice.setCode(customerDetails.getSalesOffice());
			insertSalesOffice(salesOffice, userId);
			customer.setSalesOfficeId(salesOffice.getId());
	//	}

		//int salesGroupId = commonBO.getSalesGroupIdByCode(customerDetails.getSalesGroup());

		if (salesGroupId > 0) {
			customer.setSalesGroupId(salesGroupId);
		} else {
			Salesgroup salesGroup = new Salesgroup();
			//salesGroup.setCode(customerDetails.getSalesGroup());
			logger.info("salesGroup " + salesGroup.getCode());
			insertSalesGroup(salesGroup, userId);
			customer.setSalesGroupId(salesGroup.getId());
		}

		//int salesOrganizationId = commonBO.getSalesOrganizationIdByCode(customerDetails.getSalesOrg());

		SalesArea salesArea = new SalesArea();
		if (salesOrganizationId > 0) {
			salesArea.setSalesOrgId(salesOrganizationId);
		} else {
			Salesorganisation salesorganisation = new Salesorganisation();
			salesorganisation.setCode(customerDetails.getSalesOrg());
			insertSalesOrganization(salesorganisation, userId);
			salesArea.setSalesOrgId(salesorganisation.getId());
		}

		int divisionId = commonBO.getDevisionIdByCode(customerDetails.getDivision());
		if (divisionId > 0) {
			salesArea.setDivisionId(divisionId);
		} else {
			Division division = new Division();
			division.setCode(customerDetails.getDivision());
			insertDivision(division, userId);
			salesArea.setDivisionId(division.getId());
		}

		Distributionchannel distributionchannel = new Distributionchannel();
		int distrributionChannelId = commonBO.getDistributionChannelIdByCode(customerDetails.getDistChannel());
		if (distrributionChannelId > 0) {
			distributionchannel = distributionchannelDAO.findDistributionchannelByID(distrributionChannelId);
			salesArea.setDistChannelId(distrributionChannelId);
		} else {
			distributionchannel.setCode(customerDetails.getDistChannel());
			insertDistributionchannel(distributionchannel, userId);
			salesArea.setDistChannelId(distributionchannel.getId());
		}
		Customergroup customerGroup = customergroupBO.getCustomergroupByCode(customerDetails.getDistChannel(),
				company.getId());
		if (customerGroup != null) {
			customer.setCustomerGroupId(customerGroup.getId());
		} else {
			customerGroup = new Customergroup();
			customerGroup.setCode(distributionchannel.getCode());
			customerGroup.setName(distributionchannel.getName());
			customergroupBO.insertCustomergroup(customerGroup, userId, company.getId());
			customer.setCustomerGroupId(customerGroup.getId());
		}

		int salesAreaId = commonBO.getSalesAreaId(salesOrganizationId, divisionId, distrributionChannelId);
		if (salesAreaId > 0) {
			customer.setSalesAreaId(salesAreaId);
		} else {
			insertSalesArea(salesArea, userId);
			customer.setSalesAreaId(salesArea.getId());
		}

		final String pw = PasswordGeneratorUtil.passwordGenerator();
		final String encyptpw = Credential.SHA1.digest("" + System.currentTimeMillis(), pw);
		customer.setAccessKey(encyptpw);
		customer.setCode(customerCode);
		customer.setCustomerName(customerDetails.getCustomerName());
		if (customerDetails.getBlockStatus() != null && customerDetails.getBlockStatus().equalsIgnoreCase("Y")) {
			customer.setAccessPortal(0);
			customer.setDeactivationDate(new Timestamp((new java.util.Date()).getTime()));
			customer.setStatusUpdatedBy(Integer.toString(userId));
		} else {
			customer.setAccessPortal(1);
			customer.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
			customer.setStatusUpdatedBy(Integer.toString(userId));
		}
		customer.setShowSalesData(1);
		insertCount = insertCustomer(customer, userId);
		// orderBo.getMissingShipToParty(customerDetails);// Added by Ravindra to get
		// Ship to Party
		if (insertCount > 0) {
			// register user and notify them
			registerCustomer(mobileNumber, email, company, customer, employeeEmail);
		}
		return insertCount;
	}

	public int insertCustomer(Customer customer, int userId) {
		try {
			int id = sequencesBO.getNextValue(customerSequence);
			customer.setId(id);
			customer.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
			customer.setCreator(Integer.toString(userId));
			customer.setModifier(Integer.toString(userId));
			customer.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
			customer.setEntityUid(UUID.randomUUID().toString());
			customer.setOptLock(0);
			customer.setSecondarySalesAccess(true);
			if (customerDAO.insertCustomer(customer, id, userId) > 0) {
				return id;
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private int insertSalesArea(SalesArea salesArea, int userId) {
		int id = sequencesBO.getNextValue(salesAreaSequence);
		salesArea.setId(id);
		salesArea.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
		salesArea.setCreator(Integer.toString(userId));
		salesArea.setModifier(Integer.toString(userId));
		salesArea.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		salesArea.setEntityUid(UUID.randomUUID().toString());
		salesArea.setOptLock(0);
		return salesareaDAO.insertSalesarea(salesArea, id, userId);
	}

	private int insertDistributionchannel(Distributionchannel distributionchannel, int userId) throws Exception {

		Distributionchannel disChannelBean = distChannProcessor.getDistributionChannelFromSAP(distChannelFile,
				distributionchannel.getCode());
		// Name should insert to Master
		int id = sequencesBO.getNextValue(distributionChannelSequence);
		distributionchannel.setId(id);
		distributionchannel.setName(disChannelBean.getName());
		distributionchannel.setCreateDate(new Timestamp((new java.util.Date()).getTime()));
		distributionchannel.setCreator(Integer.toString(userId));
		distributionchannel.setModifier(Integer.toString(userId));
		distributionchannel.setUpdateDate(new Timestamp((new java.util.Date()).getTime()));
		distributionchannel.setEntityUid(UUID.randomUUID().toString());
		distributionchannel.setOptLock(0);

		return distributionchannelDAO.insertDistributionchannel(distributionchannel, id, userId);
	}

	private int insertDivision(Division division, int userId) {
		Division sapProcesseddivision = divisionDetailsSAPProcessor.getDivisionDetailsFromSAP(devisionFileName,
				division.getCode());
		division.setName(sapProcesseddivision.getName());
		return divisionBo.insert(division, userId);
	}

	private int insertSalesOrganization(Salesorganisation salesorganisation, int userId) {
		Salesorganisation salesorganisationBean = salesOrgDetailsSAPProcessor
				.getSalesOrgDetailsFromSAP(salesOrgDetailsFileName, salesorganisation.getCode());
		int id = sequencesBO.getNextValue(salesOrgSequence);
		salesorganisation.setName(salesorganisationBean.getName());
		salesorganisation.setId(id);
		salesorganisation.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		salesorganisation.setCreator(Integer.toString(userId));
		salesorganisation.setModifier(Integer.toString(userId));
		salesorganisation.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		salesorganisation.setEntityUid(UUID.randomUUID().toString());
		salesorganisation.setOptlock(0);
		salesorganisation.setIsdeleted(0);
		return salesorganisationDAO.insertSalesorganisation(salesorganisation, id, userId);

	}

	private int insertSalesOffice(Salesoffice salesOffice, int userId) throws Exception {
		Salesoffice salesofficeBean = salesOfficeDetailsSAPProcessor
				.getSalesOfficeDetailsFromSAP(salesOfficeDetailsFileName, salesOffice.getCode());
		int id = sequencesBO.getNextValue(salesOfficeSequence);
		salesOffice.setName(salesofficeBean.getName());
		salesOffice.setId(id);
		salesOffice.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		salesOffice.setCreator(Integer.toString(userId));
		salesOffice.setModifier(Integer.toString(userId));
		salesOffice.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		salesOffice.setEntityUid(UUID.randomUUID().toString());
		salesOffice.setOptlock(0);
		return salesOfficeDAO.insertSalesOffice(salesOffice, id, userId);
	}

	private int insertSalesGroup(Salesgroup salesGroup, int userId) throws Exception {

		Salesgroup salesgroupBean = salesGroupDetailsSAPProcessor.getSalesGroupDetailsFromSAP(salesGroupDetailsFileName,
				salesGroup.getCode());
		salesGroup.setName(salesgroupBean.getName());
		int id = sequencesBO.getNextValue(salesGroupSequence);
		salesGroup.setId(id);
		salesGroup.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		salesGroup.setCreator(Integer.toString(userId));
		salesGroup.setModifier(Integer.toString(userId));
		salesGroup.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		salesGroup.setEntityUid(UUID.randomUUID().toString());
		salesGroup.setOptlock(0);
		return commonBO.insertSalesGroup(salesGroup, id, userId);
	}

	@Override
	public boolean isCustomerCodeExist(String code, Integer companyId) throws Exception {
		return customerDAO.isCustomerCodeExist(code, companyId);
	}

	@Override
	public boolean isDelcrederCodeExist(String code, Integer companyId) throws Exception {
		return customerDAO.isDelcrederCodeExist(code, companyId);
	}

	*//**
	 * Private method to insert contact of user
	 * 
	 * @param userRegistrationRequest
	 * @param sequenceForUserId
	 * @param address
	 * @param contact
	 * @return 1 if success else 0
	 *//*
	private int insertContact(CustomerDetails customerDetail, int sequenceForUserId, Address address, Contact contact) {
		int insertCount;
		contact.setAddressid(address.getId());
		contact.setCreator(Integer.toString(sequenceForUserId));
		contact.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		contact.setModifier(Integer.toString(sequenceForUserId));
		contact.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		insertCount = contactBO.insertContact(contact, sequenceForUserId);
		return insertCount;
	}

	*//**
	 * Private method to insert Address for user
	 * 
	 * @param userRegistrationRequest
	 * @param sequenceForUserId
	 * @param location
	 * @param address
	 * @return 1 if success else 0
	 *//*
	private int insertAddress(CustomerDetails customerDetail, int sequenceForUserId, Location location,
			Address address) {
		int insertCount;
		address.setStreetSide(customerDetail.getCity2());
		address.setStreetName(customerDetail.getStreet());
		address.setLocationId(location.getId());
		address.setEntityUid(UUID.randomUUID().toString());
		address.setCreator(Integer.toString(sequenceForUserId));
		address.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		address.setModifier(Integer.toString(sequenceForUserId));
		address.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));

		insertCount = addressBO.insertAddress(address, sequenceForUserId);
		return insertCount;
	}

	*//**
	 * Private method to insert Location for User
	 * 
	 * @param userRegistrationRequest
	 * @param sequenceForUserId
	 * @param location
	 * @return 1 if success else 0
	 *//*
	private int insertLocation(CustomerDetails customerdetail, int sequenceForUserId, Location location) {
		int insertCount;
		location.setCity(customerdetail.getCity1());
		location.setPostalCode(customerdetail.getPostalCode());
		location.setStateProv(customerdetail.getRegion());
		location.setCreator(Integer.toString(sequenceForUserId));
		location.setEntityUid(UUID.randomUUID().toString());
		location.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		location.setModifier(Integer.toString(sequenceForUserId));
		location.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		location.setIsDeleted(0);
		location.setDistrict(customerdetail.getSalesDiscrict()); // Added By Ravindra on 01-11-17
		insertCount = locationBO.insertLocation(location, sequenceForUserId);
		return insertCount;
	}

	@Override
	public List<Igimage> getPageImages(String pageName, Integer companyId) {
		return contentBO.getImageList(pageName, companyId);
	}

	@Override
	public Map<String, Content> getLoginPageContent() {
		return contentBO.getLoginPageContent();
	}

	@Override
	public String getPageContent(String pageName, Integer companyId) {
		return contentBO.getPageContent(pageName, companyId);
	}

	@Override
	public List<Customer> getAllFilteredCustomerByEmoployeeId(Customer customer) {
		return customerDAO.getAllFilteredCustomerByEmoployeeId(customer);
	}

	@Override
	public List<Customer> getAllFilteredCustomerByDelcrederId(Customer customer) {
		return customerDAO.getAllFilteredCustomerByDelcrederId(customer);
	}

	@Override
	public int sendAccesskey(Customer customer, Integer userId, Company company) {
		final String pw = PasswordGeneratorUtil.passwordGenerator();
		final String encyptpw = Credential.SHA1.digest("" + System.currentTimeMillis(), pw);
		customer.setAccessKey(encyptpw);
		int insertCount = updateCustomerAccessKey(customer, userId);
		if (insertCount > 0) {
			emailServiceHelper.sendCustomerAccessKey(pw, customer, company);
		}

		*//**
		 * SMS text
		 *//*

		*//**
		 * 
		 * text changed
		 *//*
		StringBuffer sb = new StringBuffer();
		
		 * sb.
		 * append("Please use following access key for registration into WebSales portal"
		 * ); sb.append(System.getProperty("line.separator"));
		 * sb.append("Name: "+customer.getCustomerName());
		 * sb.append(System.getProperty("line.separator")); sb.append("Account code: "+
		 * customer.getCode()); sb.append(System.getProperty("line.separator"));
		 * sb.append("Access key: "+ customer.getAccessKey());
		 * sb.append(System.getProperty("line.separator")); sb.append("URL: " +
		 * company.getPortalUrl());
		 
		sb.append("Dear Customer " + customer.getCustomerName() + " - " + customer.getCode());
		sb.append(System.getProperty("line.separator"));
		sb.append("Welcome to" + (company.getId() == 3 ? " Ambuja Parivar" : " ACC Parivar"));
		sb.append(System.getProperty("line.separator"));
		sb.append("Your access key to proceed with Web Sales Portal registration is " + pw);

		sMSServiceHelper.sendSMS(customer.getMobileNumber(), sb.toString(), company.getId());

		return insertCount;
	}

	private int updateCustomerAccessKey(Customer customer, Integer userId) {
		Customer oldCustomer = findCustomerByID(customer.getId());
		return customerDAO.updateCustomerAccessKey(oldCustomer, customer, customer.getId(), userId);
	}

	@Override
	public List<CustomerUserDto> getAllCustomerUsers(Integer userId) {
		return customerDAO.getAllCustomerUsers(userId);
	}

	@Override
	public List<Map<String, Object>> getCustomerDetailsByUserId(int userId) {
		return customerDAO.getCustomerDetailsByUserId(userId);
	}

	@Override
	public Map<String, String> getActiveInactiveCustomersCount(Integer companyId) {
		Map<String, String> count = new HashMap<String, String>();
		count.put("active", customerDAO.getActiveCustomersCount(companyId));
		count.put("inActive", customerDAO.getInactiveCustomersCount(companyId));
		return count;
	}

	@Override
	public Map<String, String> getActiveInactiveCustomersCountByLoginDuration(Integer companyId) {
		Map<String, String> count = new HashMap<String, String>();
		count.put("active", customerDAO.getActiveCustomerCountByLoginDuration(companyId));
		count.put("inActive", customerDAO.getInActiveCustomerCountByLoginDuration(companyId));
		return count;
	}

	@Override
	public Map<String, String> getRegisteredUsersCount(Integer companyId) {
		Map<String, String> count = new HashMap<String, String>();
		Long registeredCount = customerDAO.getRegisteredCustomersCount(companyId);
		Long totalCustomers = customerDAO.getCustomersCount(companyId);
		count.put("totalCustomers", totalCustomers + "");
		count.put("registered", registeredCount + "");
		// count.put("notRegistered", ( totalCustomers - registeredCount) + "");
		return count;
	}

	@Override
	public Long getCustomersCurrentDayLoginCount(Integer companyId) {
		LocalDate date = LocalDate.now();
		return customerDAO.getCustomersCurrentDayLoginCount(companyId, date);
	}

	@Override
	public List<Customer> getCustomersByCurrentDayLogin(Integer companyId) {
		LocalDate date = LocalDate.now();
		return customerDAO.getCustomersByCurrentDayLogin(companyId, date);
	}

	@Override
	public String getCustomerLockedCount(Integer companyId) {
		return customerDAO.getCustomerLockedCount(companyId).toString();
	}

	@Override
	public List<Customer> getActiveCustomersByDuration(Integer companyId) {
		return customerDAO.getActiveCustomersByDuration(companyId);
	}

	@Override
	public List<Customer> getInActiveCustomersByDuration(Integer companyId) {
		return customerDAO.getInActiveCustomersByDuration(companyId);
	}

	@Override
	public List<Customer> getActiveInactiveCustomersListForDashboard(Integer companyId, Integer activeFlag) {
		return customerDAO.getActiveInactiveCustomersListForDashboard(companyId, activeFlag);
	}

	@Override
	public List<Customer> getAllCustomersListForDashboard(Integer companyId) {
		return customerDAO.getAllCustomersListForDashboard(companyId);
	}

	@Override
	public List<Customer> getLockedCustomersList(Integer companyId) {
		return customerDAO.getLockedCustomersList(companyId);
	}

	@Override
	public List<Customer> getRegisteredCustomersList(Integer companyId) {
		return customerDAO.getRegisteredCustomersList(companyId);
	}

	@Override
	public List<Customer> getNonRegisteredCustomersList(Integer companyId) {
		return customerDAO.getNonRegisteredCustomersList(companyId);
	}

	@Override
	public int updateCustomerDistrict(CustomerDetails customer) {

		return customerDAO.updateCustomerDistrict(customer);
	}

	@Override
	public Customer findCustomerByCode(String customerCode) {
		return customerDAO.findCustomerByCode(customerCode);
	}

	// for updating tables from staging tables

	@Transactional
	public void updateCustomer(Map<String, Object> obj) throws Exception {
		int userId = 1;
		Company company = null;
		boolean isExist = false;
		try {
			Customer customer = new Customer();
			Customer cst = null;
			// customer.setCompanyId(company.getId());
			if (obj.get("SOLD_TO_SAP_CODE") != null) {
				customer.setCode(obj.get("SOLD_TO_SAP_CODE").toString());
				cst = customerDAO.findCustomerByCode(customer.getCode());
				if (cst != null) {
					customer.setId(cst.getId());
					customer.setMainContactId(cst.getMainContactId());
					customer.setCompanyId(cst.getCompanyId());
					customer.setSalesGroupId(cst.getSalesGroupId());
					customer.setSalesOfficeId(cst.getSalesOfficeId());
					isExist = true;
				} else if (cst == null && obj.get("CUSTOMER_TYPE") != null
						&& obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("0001")
						&& (obj.get("CREDIT_REP_GROUP_CODE") == null
								|| obj.get("CREDIT_REP_GROUP_CODE").toString().equalsIgnoreCase("#N/A"))) {
					this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
							"Error in CREDIT_REP_GROUP_CODE");
					return;
				}
			} else {
				logger.info("skiping customer creation as its not have valid CREDIT_REP_GROUP_CODE, customer code="
						+ obj.get("SOLD_TO_SAP_CODE"));
				this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
						"Error in SOLD_TO_SAP_CODE");
				return;
			}

			if (obj.get("CITY") != null)
				customer.setCity(obj.get("CITY").toString());
			else if (isExist)
				customer.setCity(cst.getCity());
			if (obj.get("STREET") != null)
				customer.setStreetName(obj.get("STREET").toString());
			else if (isExist)
				customer.setStreetName(cst.getStreetName());
			if (obj.get("NAME_1") != null)
				customer.setCustomerName(obj.get("NAME_1").toString());
			else if (isExist)
				customer.setCustomerName(cst.getCustomerName());
			if (obj.get("SOLD_TO_EMAIL") != null)
				customer.setEmail(obj.get("SOLD_TO_EMAIL").toString());
			else if (isExist)
				customer.setEmail(cst.getEmail());
			if (obj.get("TELOPHONE_NUMBER") != null)
				customer.setMobileNumber(obj.get("TELOPHONE_NUMBER").toString().replaceAll("[^0-9]", ""));
			else if (isExist)
				customer.setMobileNumber(cst.getMobileNumber());
			// customer.setStreetSide("");
			if (obj.get("POSTEL_CODE") != null)
				customer.setPostalCode(obj.get("POSTEL_CODE").toString());
			else if (isExist)
				customer.setPostalCode(cst.getPostalCode());
//			if (obj.get("IS_BLOCKD") != null && !obj.get("IS_BLOCKD").toString().equalsIgnoreCase("0")) {
//				customer.setAccessPortal(1);
//			} else
				if (
//					(obj.get("IS_BLOCKD") != null && !obj.get("IS_BLOCKD").toString().equalsIgnoreCase("0"))||
					 (obj.get("RISK_CATEGORY") != null && (obj.get("RISK_CATEGORY").toString().equalsIgnoreCase("07")
							|| obj.get("RISK_CATEGORY").toString().equalsIgnoreCase("08")))
					|| (obj.get("CUSTOMER_TYPE") != null && (obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("CPD")
							|| obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("Z002")))) {
				customer.setAccessPortal(0);
			} else if (isExist && obj.get("RISK_CATEGORY") == null) {
				customer.setAccessPortal(cst.getAccessPortal());
			} else
				customer.setAccessPortal(1);
			if (obj.get("SALES_ORG_CODE") != null)
				customer.setSalesOrgCode(obj.get("SALES_ORG_CODE").toString());
			else if (isExist)
				customer.setSalesOrgCode(cst.getSalesOrgCode());
			// for further devlopment
			if (obj.get("CUSTOMER_TYPE") != null) {
				// if (obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("CPD")
				// || obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("Z002"))
				// customer.setAccessPortal(0);
				// else if (isExist)
				// customer.setAccessPortal(cst.getAccessPortal());
				// else
				// customer.setAccessPortal(1);

				// for creating salesPromoter
				if (obj.get("CUSTOMER_TYPE").toString().equalsIgnoreCase("Z004")) {
					customer.setSalesPromoter(1);
					customer.setSalesOrgCode("5000");
				} else
					customer.setSalesPromoter(0);
			} else if (isExist) {
				customer.setAccessPortal(cst.getAccessPortal());
				customer.setSalesPromoter(cst.getSalesPromoter());

			} else {
				logger.info("skiping customer creation as its not have valid CUSTOMER_TYPE, customer code="
						+ obj.get("SOLD_TO_SAP_CODE"));
				this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3, "Error in CUSTOMER_TYPE");
				return;
			}
			customer.setIsDirectPosting(0);

			customer.setDelcreder(0);

			customer.setDistChannel("01");
			if (!isExist) {
				
				 * List<Map<String, Object>> custSalesGroupStgList = customerDAO
				 * .getCustomerSalesGroupStg(obj.get("SOLD_TO_SAP_CODE").toString());
				 * Map<String, Object> salesGroupPlantStg = null; if (custSalesGroupStgList ==
				 * null || custSalesGroupStgList.size() == 0) {
				  // setting default customer,s salesOffice,SAlesOrg,Division,company ect to
					// create customer
				customer.setSalesOfficeCode("5000");
				customer.setSalesGroup("001");
				customer.setDivisionCode("99");
				customer.setSalesOfficeId(199);
				if (obj.get("CUSTOMER_TYPE") != null) {
					if (obj.get("CUSTOMER_TYPE").toString().trim().equalsIgnoreCase("Z004")) {
						company = companyBO.findCompanyByID(1);

					} else if (obj.get("CREDIT_REP_GROUP_CODE") != null
							&& !obj.get("CREDIT_REP_GROUP_CODE").toString().equalsIgnoreCase("#N/A")
							&& !obj.get("CREDIT_REP_GROUP_CODE").toString().equalsIgnoreCase("0")) {
						company = companyBO
								.findCompanyByCreditRepGroupCode(obj.get("CREDIT_REP_GROUP_CODE").toString());
						// if(customer.getSalesOrgCode()==null)
						customer.setSalesOrgCode(
								(salesorganisationDAO.findSalesOrganisationByCompanyId(company.getId())).getCode());
					} // company = companyBO.findCompanyBySalesOrgCode(customer.getSalesOrgCode());
					if (company != null)
						customer.setCompanyId(company.getId());
					else {
						logger.info(
								"skiping customer creation as its not have valid CREDIT_REP_GROUP_CODE, customer code="
										+ obj.get("SOLD_TO_SAP_CODE"));
						this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
								"Error in CREDIT_REP_GROUP_CODE");
						return;
					}
				} else {
					logger.info(
							"skiping customer creation as its not have valid CUSTOMER_TYPE/CREDIT_REP_GROUP_CODE, customer code="
									+ obj.get("SOLD_TO_SAP_CODE"));
					this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
							"Error in CUSTOMER_TYPE");
					return;
				}
				
				 * } else { for (Map<String, Object> CustSalesGrp : custSalesGroupStgList) { if
				 * (CustSalesGrp != null) { if (CustSalesGrp.get("SALES_OFFICE_CODE") != null) {
				 * customer.setSalesOfficeCode(CustSalesGrp.get("SALES_OFFICE_CODE").toString())
				 * ; } if (CustSalesGrp.get("SALES_ORG_CODE") != null) {
				 * customer.setSalesOrgCode(CustSalesGrp.get("SALES_ORG_CODE").toString()); } if
				 * (CustSalesGrp.get("SALES_GROUP_CODE") != null) {
				 * customer.setSalesGroup(CustSalesGrp.get("SALES_GROUP_CODE").toString()); }
				 * List<Map<String, Object>> salesGroupPlantStgList = customerDAO
				 * .getSalesGroupPlantStgForGrpOrgOfc(customer.getSalesOfficeCode(),
				 * customer.getSalesOrgCode(), customer.getSalesGroup()); if
				 * (salesGroupPlantStgList == null || salesGroupPlantStgList.isEmpty()) {
				 * continue; } else { salesGroupPlantStg = salesGroupPlantStgList == null ||
				 * salesGroupPlantStgList.size() <= 0 ? null : salesGroupPlantStgList.get(0); if
				 * (salesGroupPlantStg != null && salesGroupPlantStg.get("DIVISION_CODE") !=
				 * null)
				 * customer.setDivisionCode(salesGroupPlantStg.get("DIVISION_CODE").toString());
				 * 
				 * if (customer.getSalesOfficeCode() != null) { customer.setSalesOfficeId(
				 * commonBO.getSalesOfficeIdByCode(customer.getSalesOfficeCode())); } if
				 * (CustSalesGrp.get("SALES_ORG_CODE") != null) { company = companyBO
				 * .findCompanyBySalesOrgCode(CustSalesGrp.get("SALES_ORG_CODE").toString()); }
				 * 
				 * if (company != null) customer.setCompanyId(company.getId()); break; } } else
				 * { logger.
				 * info("customer sales Office mapping is not available for customer code" +
				 * customer.getCode()); continue; } } }
				  if (customer.getDivisionCode() == null) {
					// throw new Exception("unable to finde Division COde for customer");
					logger.info("skiping customer creation as unable to finde Division COde for customer"
							+ obj.get("SOLD_TO_SAP_CODE"));
					this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
							"unable to finde Division COde for customer");
					return;
				}
				SalesUnit salesUnit = null;
				try {
					salesUnit = commonBO.getSalesUnitIdByOfficeCode(customer.getSalesOfficeCode()).get(0);
					if (salesUnit != null) {
						customer.setSalesUnit(salesUnit.getId().toString());
						customer.setSalesUnitCode(salesUnit.getCode());
					} else {
						logger.info("salesUnit not found for SalesOfficeCode" + customer.getSalesOfficeCode());
						this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
								"salesUnit not found for SalesOfficeCode " + customer.getSalesOfficeCode());
						return;
					}
				} catch (Exception e) {
					logger.error("salesUnit query exception for SalesOfficeCode" + customer.getSalesOfficeCode());
					// throw e;
					this.updatecustomerMasterSTG(Integer.parseInt(obj.get("ID").toString()), 3,
							"salesUnit query exception for SalesOfficeCode " + customer.getSalesOfficeCode());
					return;
				}
				// List<Map<String, Object>> salesGroupPlantList = null;

				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setCustomerCode(customer.getCode());
				customerDetails.setCustomerName(customer.getCustomerName());
				customerDetails.setCity1(customer.getCity());
				customerDetails.setMailId(customer.getEmail());
				customerDetails.setMobile(customer.getMobileNumber());
				customerDetails.setBlockStatus(customer.getAccessPortal() == 0 ? "Y" : "N");
				customerDetails.setCompanyCode(customer.getCompanyCode());
				customerDetails.setCompanyId(customer.getCompanyId().toString());
				customerDetails.setDivision(customer.getDivisionCode());
				customerDetails.setDistChannel(customer.getDistChannel());
				customerDetails.setPostalCode(customer.getPostalCode());
				customerDetails.setStreet(customer.getStreetName());
				customerDetails.setSalesOrg(customer.getSalesOrgCode());
				customerDetails.setSalesGroup(customer.getSalesGroup());
				customerDetails.setSalesOffice(customer.getSalesOfficeCode());
				customerDetails.setSalesUnit(customer.getSalesUnitCode());
				customerDetails.setDistChannel(customer.getDistChannel());
				// inserting location of customer
				int insertCount = 0;
				Location location = new Location();
				insertCount = insertLocation(customerDetails, userId, location);
				// inserting Addredd of customer
				Address address = new Address();
				insertCount = insertAddress(customerDetails, userId, location, address);
				 insert contact 
				Contact contact = new Contact();
				contact.setEmail(customer.getEmail());

				contact.setMobile(customer.getMobileNumber());
				// changed later
				// contact.setMobile(customerDetails.getMobile());
				insertCount = insertContact(customerDetails, userId, address, contact);
				customer.setMainContactId(contact.getId());
				customer.setSalesOfficeId(commonBO.getSalesOfficeIdByCode(customer.getSalesOfficeCode()));

				customer.setSalesGroupId(commonBO.getSalesGroupIdByCode(customer.getSalesGroup()));

				Customergroup customerGroup = customergroupBO.getCustomergroupByCode(customer.getDistChannel(),
						company.getId());
				if (customerGroup != null)
					customer.setCustomerGroupId(customerGroup.getId());
				else
					customer.setCustomerGroupId(512);

				int salesAreaId = commonBO.getSalesAreaId(
						commonBO.getSalesOrganizationIdByCode(customer.getSalesOrgCode()),
						commonBO.getDevisionIdByCode(customer.getDivisionCode()), 10);

				customer.setSalesAreaId(salesAreaId);

				final String pw = customer.getCode();// PasswordGeneratorUtil.passwordGenerator();

				final String encyptpw = Credential.SHA1.digest("" + System.currentTimeMillis(), pw);
				customer.setAccessKey(encyptpw);
				if (customerDetails.getBlockStatus() != null
						&& customerDetails.getBlockStatus().equalsIgnoreCase("Y")) {
					customer.setAccessPortal(0);
					customer.setDeactivationDate(new Timestamp((new java.util.Date()).getTime()));
					customer.setStatusUpdatedBy(Integer.toString(userId));
				} else {
					customer.setAccessPortal(1);
					customer.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
					customer.setStatusUpdatedBy(Integer.toString(userId));
				}
				customer.setShowSalesData(1);
				insertCount = insertCustomer(customer, userId);
				if (insertCount > 0) {
					
					 * if (custSalesGroupStgList != null) for (Map<String, Object>
					 * customerSalesGroupStg : custSalesGroupStgList) { if
					 * (customerSalesGroupStg.get("SALES_OFFICE_CODE") != null &&
					 * customerSalesGroupStg.get("SALES_ORG_CODE") != null &&
					 * customerSalesGroupStg.get("SALES_GROUP_CODE") != null) { salesGroupPlantList
					 * = customerDAO.getSalesGroupPlantStgForGrpOrgOfc(
					 * customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_GROUP_CODE").toString()); int count = 0; for
					 * (Map<String, Object> salesGroupPlant : salesGroupPlantList) { if
					 * (salesGroupPlant != null && salesGroupPlant.get("PLANT_CODE") != null) {
					 * List<Map<String, Object>> list =
					 * customerDAO.getCustomerSalesOfficePlantMapping( customer.getCode(),
					 * customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_GROUP_CODE").toString(),
					 * salesGroupPlant.get("PLANT_CODE").toString(), 0); if (!list.isEmpty()) { //
					 * record already exit do nothing
					 * 
					 * } else { count = customerDAO.insertCustomerSalesOfficePlantMapping(
					 * customer.getCode(),
					 * customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
					 * customerSalesGroupStg.get("SALES_GROUP_CODE").toString(),
					 * salesGroupPlant.get("PLANT_CODE").toString()); }
					 * 
					 * } } if (count > 0) customerDAO.updateCustomerSalesGrpStgIsProcessed(
					 * (Integer) customerSalesGroupStg.get("ID"), 1); } }
					 
					if (obj.get("CREDIT_REP_GROUP_CODE") != null && obj.get("CUSTOMER_CREDIT_GROUP_CODE") != null) {
						if (customer.getSalesPromoter() == 1) {
							customerDAO.insertCustomerRepGroupMapping(insertCount, 4, 4);

						} else {
							customerDAO.insertCustomerRepGroupMapping(insertCount,
									customerDAO.getCreditRepGroupIdByCode(obj.get("CREDIT_REP_GROUP_CODE").toString()),
									customerDAO.getCustomerCreditGroupIdByCode(
											obj.get("CUSTOMER_CREDIT_GROUP_CODE").toString()));
						}
					}
					if (customer.getSalesPromoter() == 1)
						customerDAO.insertCustomerRepGroupMapping(insertCount, 4, 4);

					// orderBo.getMissingShipToParty(customerDetails);// Added by Ravindra to get
					// Ship to Party
					// register user and notify them
					registerCustomerFromStg(company, customer);
					// update customer_master table IS_PROCESSED to 1
					customerDAO.updateCustomerMasterIsProcessed((Integer) obj.get("ID"), 1,
							"Successfully Processed with new Customer Creation");

				}

			} else

			{

				updateCustomer(customer, userId,false);
				if (obj.get("CREDIT_REP_GROUP_CODE") != null && obj.get("CUSTOMER_CREDIT_GROUP_CODE") != null
						&& !obj.get("CREDIT_REP_GROUP_CODE").toString().equalsIgnoreCase("#N/A")
						&& !obj.get("CUSTOMER_CREDIT_GROUP_CODE").toString().equalsIgnoreCase("#N/A")) {
					if (customerDAO.getCustomerRepGroupMapping(customer.getId()).isEmpty()) {

						customerDAO.insertCustomerRepGroupMapping(customer.getId(),
								customerDAO.getCreditRepGroupIdByCode(obj.get("CREDIT_REP_GROUP_CODE").toString()),
								customerDAO.getCustomerCreditGroupIdByCode(
										obj.get("CUSTOMER_CREDIT_GROUP_CODE").toString()));

					} else {
						customerDAO.updateCustomerRepGroupMapping(customer.getId(),
								customerDAO.getCreditRepGroupIdByCode(obj.get("CREDIT_REP_GROUP_CODE").toString()),
								customerDAO.getCustomerCreditGroupIdByCode(
										obj.get("CUSTOMER_CREDIT_GROUP_CODE").toString()));

					}
				}
				List<Map<String, Object>> custUserMpg = customerDAO.getCustomerUserMapping(customer.getId());
				if (custUserMpg == null || custUserMpg.isEmpty()) {
					company = companyBO.findCompanyBySalesOfficeId(customer.getSalesOfficeId());

					registerCustomerFromStg(company, customer);

				}
				customerDAO.updateCustomerMasterIsProcessed((Integer) obj.get("ID"), 1,
						"Successfully Processed with Updation of Customer");

			}

		} catch (

		Exception e) {
			logger.error("error while creating/updating the customer"
					+ Long.parseLong((obj.get("SOLD_TO_SAP_CODE").toString())));
			throw e;

		}

	}

	private boolean registerCustomerFromStg(Company company, Customer customer) {

		try {
			UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
			registrationRequest.setCity(customer.getCity());
			registrationRequest.setPostalCode(customer.getPostalCode());
			registrationRequest.setState(customer.getState());
			registrationRequest.setCountry("");

			registrationRequest.setHouseNumber("");
			registrationRequest.setFloor("");
			registrationRequest.setStreetSide("");
			registrationRequest.setStreetName("");
			if (customer.getEmail() != null)
				registrationRequest.setEmail(customer.getEmail());
			else
				registrationRequest.setEmail("");

			registrationRequest.setPhone("");
			registrationRequest.setMobile(customer.getMobileNumber());

			registrationRequest.setLoginId(customer.getCode());
			registrationRequest.setGender("100");
			registrationRequest.setGivenName(customer.getCustomerName());
			registrationRequest.setFamilyName("");
			registrationRequest.setDateOfBirth(new Timestamp(new Date().getTime()));
			registrationRequest.setCompanyId(company.getId());
			registrationRequest.setMaritalStatus("110");

			registrationRequest.setPosition("103");
			registrationRequest.setUserFunction("107");

			userRegistrationBO.registerUser(registrationRequest, customer, company);
			// send email to customer
			// emailServiceHelper.sendCustomerUpload(customer, company,
			// registrationRequest.getPassword());
			// send email to employee
			// emailServiceHelper.setMailToOfficerForCustomerUpload(customer, company,
			// customer.getEmployeeEmail(),registrationRequest.getPassword());

			// send sms to customer

			StringBuffer sb = new StringBuffer();
			sb.append("Dear Customer " + customer.getCustomerName());
			sb.append(System.getProperty("line.separator"));
			// sb.append("Welcome to" + (company.getId() == 3 ? "Holcim" : " ACC Parivar"));
			sb.append("Welcome to Holcim");
			sb.append(System.getProperty("line.separator"));
			sb.append("Use your Dealer code as Loginid and Password as " + registrationRequest.getPassword());
			sb.append(System.getProperty("line.separator"));
			sb.append("for Login to WebSales portal.");
			System.out.println(sb);
			// sMSServiceHelper.sendSMS(customer.getMobileNumber(), sb.toString(),
			// company.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void updateCustomerSalesOfficeMappingList() {
		List<Map<String, Object>> custSalesGroupStgList = customerDAO.getAllCustomerSalesGroupStg();
		List<Map<String, Object>> salesGroupPlantList = null;
		// String codeApend = "0000000000";
		if (custSalesGroupStgList != null) {
			for (Map<String, Object> customerSalesGroupStg : custSalesGroupStgList) {
				try {
					if (customerSalesGroupStg.get("SALES_OFFICE_CODE") != null
							&& customerSalesGroupStg.get("SALES_ORG_CODE") != null
							&& customerSalesGroupStg.get("SALES_GROUP_CODE") != null) {
						salesGroupPlantList = customerDAO.getSalesGroupPlantStgForGrpOrgOfc(
								customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
								customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
								customerSalesGroupStg.get("SALES_GROUP_CODE").toString());
						String code = customerSalesGroupStg.get("SOLD_TO_SAP_CODE").toString();
						int i = 0;
						// code = codeApend.substring(0, 10 - code.length()) + code;
						for (Map<String, Object> salesGroupPlant : salesGroupPlantList) {

							int customerID = customerDAO.getCustomerIdByCode(code);
							List<Map<String, Object>> mappingCount = null;
							if (salesGroupPlant != null)
								mappingCount = customerDAO.getAllCustomerSalesOfficePlantMapping(code,
										customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
										customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
										customerSalesGroupStg.get("SALES_GROUP_CODE").toString(),
										salesGroupPlant.get("PLANT_CODE").toString());

							if (salesGroupPlant != null && salesGroupPlant.get("PLANT_CODE") != null
									&& customerID > 0) {
								if (mappingCount != null && !mappingCount.isEmpty()) {
									i = customerDAO.updateCustomerSalesOfficePlantMapping(code,
											customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
											customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
											customerSalesGroupStg.get("SALES_GROUP_CODE").toString(),
											salesGroupPlant.get("PLANT_CODE").toString(),
											new SimpleDateFormat("dd.MM.yyyy")
													.parse(customerSalesGroupStg.get("TO_DATE").toString())
													.before(new Date()) ? 1 : 0);

								} else {
									i = customerDAO.insertCustomerSalesOfficePlantMapping(code,
											customerSalesGroupStg.get("SALES_OFFICE_CODE").toString(),
											customerSalesGroupStg.get("SALES_ORG_CODE").toString(),
											customerSalesGroupStg.get("SALES_GROUP_CODE").toString(),
											salesGroupPlant.get("PLANT_CODE").toString());

								}

							}
						}
						if (i > 0)
							customerDAO.updateCustomerSalesGrpStgIsProcessed((Integer) customerSalesGroupStg.get("ID"),
									1);
					}

				} catch (Exception e) {

				}
			}
		}

	}

	@Override
	public List<Customer> searchForRegisteration(String customerCode) {
		return customerDAO.searchForRegisteration(customerCode);
	}

	@Override
	public List<Quotation> getQuotationListForCustomer(String customerCode,String salesOrg) {
//		return pricegliderService.getAllQuotationForCustomerFromPG(customerCode);
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		String ValidityDate="";//formatter.format(new Date());
		String ItemCategory="ZAGC";
		String CustomerGroup="S3";
				System.out.println("getQuotationListFromSAP");
		return customerQuotationPI.getQuotationListFromSAP(customerCode, ValidityDate, salesOrg, CustomerGroup, ItemCategory);

	}*/

	@Override
	@Transactional
	public void updatecustomerMasterSTG(int id, int value, String comment) {
		customerDAO.updateCustomerMasterIsProcessed((Integer) id, value, comment);

	}

		@Override
		public boolean isCustomerCodeExist(String code, Integer companyId) throws Exception {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isDelcrederCodeExist(String code, Integer companyId) throws Exception {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Map<String, Object> uploadCustomer(File file, int userId, Company company, boolean isSalespromotor)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Object> uploadDelcreder(File file, int userId, Company company) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Igimage> getPageImages(String pageName, Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPageContent(String pageName, Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getAllFilteredCustomerByEmoployeeId(Customer customer) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getAllFilteredCustomerByDelcrederId(Customer customer) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int sendAccesskey(Customer customer, Integer userId, Company company) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<CustomerUserDto> getAllCustomerUsers(Integer userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Map<String, Object>> getCustomerDetailsByUserId(int userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String> getActiveInactiveCustomersCount(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String> getActiveInactiveCustomersCountByLoginDuration(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String> getRegisteredUsersCount(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCustomerLockedCount(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getCustomersCurrentDayLoginCount(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getActiveInactiveCustomersListForDashboard(Integer companyId, Integer activeFlag) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getActiveCustomersByDuration(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getInActiveCustomersByDuration(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getAllCustomersListForDashboard(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getLockedCustomersList(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getRegisteredCustomersList(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getNonRegisteredCustomersList(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Customer> getCustomersByCurrentDayLogin(Integer companyId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int updateCustomerDistrict(CustomerDetails customer) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Customer findCustomerByCode(String customerCode) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void updateCustomerSalesOfficeMappingList() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCustomer(Map<String, Object> obj) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Customer> searchForRegisteration(String customerCode) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Content> getLoginPageContent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Quotation> getQuotationListForCustomer(String customerCode, String salesOrg) {
			// TODO Auto-generated method stub
			return null;
		}


}
