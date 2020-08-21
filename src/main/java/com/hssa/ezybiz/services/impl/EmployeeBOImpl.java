package com.hssa.ezybiz.services.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.nio.Address;
import com.hssa.ezybiz.dao.EmployeeDAO;
import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Employee;
import com.hssa.ezybiz.dto.EmployeeUpload;
import com.hssa.ezybiz.dto.Location;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserPassword;
import com.hssa.ezybiz.dto.UserProfile;
import com.hssa.ezybiz.services.CommonBO;
import com.hssa.ezybiz.services.CompanyBO;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.EmployeeBO;
import com.hssa.ezybiz.services.LocationBO;
import com.hssa.ezybiz.services.UserBO;
import com.hssa.ezybiz.services.UserPasswordBO;
import com.hssa.ezybiz.utils.Credential;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.CustomerPortalConstants.EmployeeUploadHeader;
import com.hssa.ezybiz.utils.PasswordGeneratorUtil;

@Service
public class EmployeeBOImpl implements EmployeeBO {

	private static final Logger logger = Logger.getLogger("orderlogger");

	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private CompanyBO companyBO;
	@Autowired
	private ContactBO contactBO;
	@Autowired
	private CommonBO commonBO;
	//@Autowired
	//private RoleMasterBO roleMasterBO;
	//@Autowired
	//private LookupDetailsBO lookupDetailsBO;
	@Autowired
	private UserBO userBO;
	//@Autowired
	//private UserRoleBO userRoleBO;
	//@Autowired
	//EmailServiceHelper emailServiceHelper;
	@Autowired
	UserPasswordBO userPasswordBO;
	@Autowired
	private LocationBO locationBO;
	//@Autowired
	//private AddressBO addressBO;
	//@Autowired
	//private EmployeeSalesOfficeDAO empSalesOfficeDAO;
//	@Autowired

	/*@Value("${employeeexcel.columnnumber}")
	private String employeeExcelMaxNoColumn;
	@Value("${employeeexcel.columns}")
	private String employeeExcelColumns;
	@Value("${employeeexcel.columnseprator}")
	private String employeeExcelColumnSeprator;

	@Value("${wareHouseExcel.columnnumber}")
	private String wareHouseExcelMaxNoColumn;
	@Value("${wareHouseExcel.columns}")
	private String wareHouseExcelColumns;

	@Value("${employeeexcel.wareHouseRole}")
	private String wareHouseRole;
	@Value("${employeeexcel.employeeRole}")
	private String employeeRole;*/

	public EmployeeBOImpl() {
		super();
	}

	public List<Employee> search(List<Object> paramList) {
		return employeeDAO.search(paramList);
	}

	public List<Employee> getAllEmployeeList(Integer companyId) {
		return employeeDAO.getAll(companyId);
	}

	public int insertEmployee(Employee employee, int userId) {

		employee.setCreator(Integer.toString(userId));
		employee.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		employee.setModifier(Integer.toString(userId));
		employee.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		employee.setEntityUid(UUID.randomUUID().toString());

		return employeeDAO.insertEmployee(employee, employee.getId(), userId);
	}

	public int updateEmployee(Employee employee, int userId) {

		User user = userBO.findUserByID(Integer.parseInt(employee.getUserId()));
		user.setEmployeeLocked(employee.getLocked());
		user.setLoginId(employee.getLoginId());
		if (employee.getActiveStatus() == 0 && employee.getActiveStatus() != user.getUserStatus()) {
			user.setDeactivationDate(new Timestamp((new java.util.Date()).getTime()));
		}

		if (employee.getActiveStatus() == 1 && employee.getActiveStatus() != user.getUserStatus()) {
			user.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
		}

		if (employee.getActiveStatus() != user.getUserStatus()) {
			user.setUserStatus(employee.getActiveStatus());
			user.setStatusUpdatedBy(Integer.toString(userId));
		}

		if (!user.getUserEmailId().equals(employee.getEmailId())) {
			Contact contact = contactBO.findContactByID(user.getMainContactId());
			contact.setEmail(employee.getEmailId());
			contactBO.updateContact(contact, userId);
		}
		Employee oldEmployee = findEmployeeByID(employee.getId());
		employeeDAO.updateEmployee(oldEmployee, employee, employee.getId(), userId);

		return userBO.updateUser(user, userId);
	}

	public Employee findEmployeeByID(int id) {
		return employeeDAO.findEmployeeByID(id);
	}

	public List<Employee> findEmployeeByFKId(int id) {
		return employeeDAO.findEmployeeByFKId(id);
	}

	public List<Employee> findEmployeeByFKSalesofficeid(int salesofficeid) {
		return employeeDAO.findEmployeeByFKSalesofficeid(salesofficeid);
	}

	@Override
	public List<Employee> findBookingOfficerByFKSalesofficeid(int salesofficeid) {
		return employeeDAO.findBookingOfficerByFKSalesofficeid(salesofficeid);
	}

	public List<Employee> findSupervisorByFKSalesofficeid(int salesofficeid) {
		return employeeDAO.findSupervisorByFKSalesofficeid(salesofficeid);
	}

//	@Override
//	public Map<String, Object> uploadWHOperator(File file, int userId, Company company) throws Exception {
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//
//		FileInputStream fileIs = new FileInputStream(file);
//
//		// Create Workbook instance holding reference to .xlsx file
//		Workbook workbook = null;
//		if (file.getName().endsWith(".xls") || file.getName().endsWith(".XLS")) {
//			workbook = new HSSFWorkbook(fileIs);
//		} else if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".XLSX")) {
//			workbook = new XSSFWorkbook(fileIs);
//		} else {
//			returnMap.put(CustomerPortalConstants.ERROR, "Received file does not have a standard excel extension.");
//			fileIs.close();
//			return returnMap;
//		}
//
//		// Get first/desired sheet from the workbook
//		Sheet sheet = workbook.getSheetAt(0);
//
//		// Iterate through each rows one by one
//		Iterator<Row> rowIterator = sheet.iterator();
//		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
//		Row headerRow = sheet.getRow(0);
//		int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
//
//		if (Integer.parseInt(wareHouseExcelMaxNoColumn) > columnNumber) {
//			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
//			workbook.close();
//			fileIs.close();
//			return returnMap;
//		}
//
//		//List<String> columns = Arrays.asList(wareHouseExcelColumns.split(employeeExcelColumnSeprator));
//
//		Iterator<Cell> headerCellIterator = headerRow.cellIterator();
//		StringBuilder missingColumns = new StringBuilder();
//		int index = 0;
//		while (headerCellIterator.hasNext()) {
//			if (index++ >= Integer.parseInt(wareHouseExcelMaxNoColumn)) {
//				break;
//			}
//			Cell cell = headerCellIterator.next();
//			if (!columns.contains(cell.getStringCellValue().toUpperCase())) {
//				missingColumns.append(cell.getStringCellValue()).append(",");
//			}
//		}
//
//		if (missingColumns.length() > 0) {
//			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
//			workbook.close();
//			fileIs.close();
//			return returnMap;
//		}
//		int successCount = 0;
//		int errorCount = 0;
//		int rowCount = 0;
//		while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			if (rowCount == 0) {
//				rowCount++;
//				continue;
//			}
//			if ((null == row.getCell(0) || row.getCell(0).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(1) || row.getCell(1).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(2) || row.getCell(2).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(3) || row.getCell(3).getCellTypeEnum().compareTo(CellType.BLANK) == 0)) {
//				break;
//			}
//			int rows = sheet.getPhysicalNumberOfRows();
//			if (rowCount >= rows) {
//				break;
//			}
//			// For each row, iterate through all the columns
//			// Iterator<Cell> cellIterator = row.cellIterator();
//			int i = 0;
//			EmployeeUpload employeeUpload = new EmployeeUpload();
//			employeeUpload.setIsWareHouseOperator(true);
//			StringBuilder errorMessage = new StringBuilder();
//			for (int colIndex = 0; colIndex < Integer.parseInt(wareHouseExcelMaxNoColumn); colIndex++) {
//				Cell cell = row.getCell(colIndex);
//				if (i >= Integer.parseInt(wareHouseExcelMaxNoColumn)) {
//					break;
//				}
//				String status = validateCell(headerRow.getCell(i++).getStringCellValue(), cell, employeeUpload,
//						company.getId());
//				if (!status.equals(CustomerPortalConstants.SUCCESS)) {
//					errorMessage.append(status).append(",");
//				}
//			}
//			if (errorMessage.length() > 0) {
//				ErrorInfo errorInfo = new ErrorInfo();
//				errorInfo.setId(row.getRowNum());
//				errorInfo.setMessage(errorMessage.substring(0, errorMessage.length() - 1));
//				errorList.add(errorInfo);
//				errorCount++;
//			} else {
//				insertEmployeeDetail(employeeUpload, userId, company, true);
//				successCount++;
//			}
//		}
//		workbook.close();
//		fileIs.close();
//
//		returnMap.put("successCount", successCount);
//		returnMap.put("errorCount", errorCount);
//		returnMap.put("errorMap", errorList);
//
//		return returnMap;
//	}

//	@Override
//	public Map<String, Object> uploadEmployee(File file, int userId, Company company) throws Exception {
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//
//		FileInputStream fileIs = new FileInputStream(file);
//
//		// Create Workbook instance holding reference to .xlsx file
//		Workbook workbook = null;
//		if (file.getName().endsWith(".xls") || file.getName().endsWith(".XLS")) {
//			workbook = new HSSFWorkbook(fileIs);
//		} else if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".XLSX")) {
//			workbook = new XSSFWorkbook(fileIs);
//		} else {
//			returnMap.put(CustomerPortalConstants.ERROR, "Received file does not have a standard excel extension.");
//			fileIs.close();
//			return returnMap;
//		}
//
//		// Get first/desired sheet from the workbook
//		Sheet sheet = workbook.getSheetAt(0);
//
//		// Iterate through each rows one by one
//		Iterator<Row> rowIterator = sheet.iterator();
//		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
//		Row headerRow = sheet.getRow(0);
//		int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
//
//		if (Integer.parseInt(employeeExcelMaxNoColumn) > columnNumber) {
//			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
//			workbook.close();
//			fileIs.close();
//			return returnMap;
//		}
//
//		List<String> columns = Arrays.asList(employeeExcelColumns.split(employeeExcelColumnSeprator));
//
//		Iterator<Cell> headerCellIterator = headerRow.cellIterator();
//		StringBuilder missingColumns = new StringBuilder();
//		int index = 0;
//		while (headerCellIterator.hasNext()) {
//			if (index++ >= Integer.parseInt(employeeExcelMaxNoColumn)) {
//				break;
//			}
//			Cell cell = headerCellIterator.next();
//			if (!columns.contains(cell.getStringCellValue().toUpperCase())) {
//				missingColumns.append(cell.getStringCellValue()).append(",");
//			}
//		}
//
//		if (missingColumns.length() > 0) {
//			returnMap.put(CustomerPortalConstants.ERROR, "Excel File is not as per Provided Template");
//			workbook.close();
//			fileIs.close();
//			return returnMap;
//		}
//		int successCount = 0;
//		int errorCount = 0;
//		int rowCount = 0;
//		while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			if (rowCount == 0) {
//				rowCount++;
//				continue;
//			}
//			if ((null == row.getCell(0) || row.getCell(0).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(1) || row.getCell(1).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(2) || row.getCell(2).getCellTypeEnum().compareTo(CellType.BLANK) == 0)
//					&& (null == row.getCell(3) || row.getCell(3).getCellTypeEnum().compareTo(CellType.BLANK) == 0)) {
//				break;
//			}
//			int rows = sheet.getPhysicalNumberOfRows();
//			if (rowCount >= rows) {
//				break;
//			}
//			// For each row, iterate through all the columns
//			// Iterator<Cell> cellIterator = row.cellIterator();
//			int i = 0;
//			EmployeeUpload employeeUpload = new EmployeeUpload();
//			employeeUpload.setIsWareHouseOperator(false);
//			StringBuilder errorMessage = new StringBuilder();
//			for (int colIndex = 0; colIndex <= Integer.parseInt(employeeExcelMaxNoColumn); colIndex++) {
//				Cell cell = row.getCell(colIndex);
//				if (i > Integer.parseInt(employeeExcelMaxNoColumn)) {
//					break;
//				}
//				String status = validateCell(headerRow.getCell(i++).getStringCellValue(), cell, employeeUpload,
//						company.getId());
//				if (!status.equals(CustomerPortalConstants.SUCCESS)) {
//					errorMessage.append(status).append(",");
//				}
//			}
//			if (errorMessage.length() > 0) {
//				ErrorInfo errorInfo = new ErrorInfo();
//				errorInfo.setId(row.getRowNum());
//				errorInfo.setMessage(errorMessage.substring(0, errorMessage.length() - 1));
//				errorList.add(errorInfo);
//				errorCount++;
//			} else {
//				insertEmployeeDetail(employeeUpload, userId, company, false);
//				successCount++;
//			}
//		}
//		workbook.close();
//		fileIs.close();
//
//		returnMap.put("successCount", successCount);
//		returnMap.put("errorCount", errorCount);
//		returnMap.put("errorMap", errorList);
//
//		return returnMap;
//	}

	private String validateCell(String cellHeader, Cell cell, EmployeeUpload employeeUpload, int userCompanyId)
			throws Exception {
		String returnMessage = CustomerPortalConstants.SUCCESS;
		try {
			switch (EmployeeUploadHeader.getEnumByValue(cellHeader)) {
			case COMPANYID:
				try {
					String companyId = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						companyId = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						companyId = cell.getStringCellValue();
					}
					if (!companyId.isEmpty()) {
						int id = companyBO.validateCompanyById(companyId);
						// if (id != userCompanyId) {
						if (id == 0) {
							returnMessage = "Invalid CompanyCode";
							break;
						}
						employeeUpload.setCompanyId(id);
					} else {
						returnMessage = "Invalid CompanyID";
						break;
					}
					break;
				} catch (Exception e) {
					returnMessage = "Invalid CompanyID";
					break;
				}
			case EMPCODE:
				try {
					String empCode = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						empCode = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						empCode = cell.getStringCellValue();
					}

					if (empCode.isEmpty()) {
						returnMessage = "Invalid EmployeeCode";
						break;
					}
					if (isEmployeeCodeExists(empCode)) {
						returnMessage = "EmployeeCode already exists in system";
						break;
					}
					employeeUpload.setEmployeeCode(empCode);
				} catch (Exception e) {
					returnMessage = "Invalid EmployeeCode";
				}
				break;
			case LOGINID:
				try {
					String loginId = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						loginId = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						loginId = cell.getStringCellValue();
					}
					if (loginId.isEmpty()) {
						returnMessage = "Invalid LoginId";
						break;
					}
					employeeUpload.setLoginId(loginId);
					if (userBO.getUserByName(null, loginId) != null) {
						returnMessage = "LoginId already exist in system";
						break;
					}
				} catch (Exception e1) {
					returnMessage = "Invalid LoginId";
				}
				break;

			case FIRSTNAME:
				try {
					String firstName = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						firstName = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						firstName = cell.getStringCellValue();
					}
					if (firstName.isEmpty()) {
						returnMessage = "Invalid First Name";
						break;
					}
					employeeUpload.setFirstName(firstName);
				} catch (Exception e1) {
					returnMessage = "Invalid First Name";
				}
				break;
			case MIDDLENAME:
				String middleName = "";
				if (cell != null) {
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						middleName = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						middleName = cell.getStringCellValue();
					}
					if (!middleName.isEmpty()) {
						employeeUpload.setMiddleName(middleName);
					}
				}
				break;
			case LASTNAME:
				if (cell != null) {
					String lastName = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						lastName = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						lastName = cell.getStringCellValue();
					}
					if (!lastName.isEmpty()) {
						employeeUpload.setLastName(lastName);
					}
				}
				break;
			case EMAIL:
				try {
					String email = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						email = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						email = cell.getStringCellValue();
					}
				//	if (!ValidatorUtil.validateEmail(email)) {
					//	returnMessage = "Invalid Email";
				//		break;
				//	}
					if (contactBO.isEmailIdExist(email) > 0) {
						returnMessage = "Email already exist in system";
						break;
					}
					employeeUpload.setEmail(email);
				} catch (Exception e1) {
					returnMessage = "Invalid Email";
				}
				break;
			case DATEOFBIRTH:
				try {
					DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
					Date dateOfBirth = null;
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						dateOfBirth = formatter
								.parse(Long.toString((new Double(cell.getNumericCellValue())).longValue()));
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						dateOfBirth = formatter.parse(cell.getStringCellValue());
					}
					employeeUpload.setDateOfBirth(dateOfBirth);
				} catch (Exception e) {
					returnMessage = "Invalid Date of Birth";
				}
				break;
			case GENDER:
				String gender = "";
				if (cell != null) {
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						gender = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						gender = cell.getStringCellValue();
					}
					if (!gender.trim().isEmpty()) {
						if (gender.equalsIgnoreCase("M")) {
							gender = CustomerPortalConstants.GENDER_MALE;
						} else if (gender.equalsIgnoreCase("F")) {
							gender = CustomerPortalConstants.GENDER_FEMALE;
						} else if (gender.equalsIgnoreCase("O")) {
							gender = CustomerPortalConstants.GENDER_OTHER;
						} else {
							returnMessage = "Invalid Gender";
							break;
						}
						int genderId = Integer.parseInt(gender);
						if (genderId > 0) {
							employeeUpload.setGender(genderId);
						} else {
							returnMessage = "Invalid Gender";
						}
						break;
					}
				}
				break;
			case MOBILE:
				try {
					Double mobile = null;
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						mobile = new Double(cell.getNumericCellValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						mobile = Double.parseDouble(cell.getStringCellValue());
					}
//					if (null != mobile && mobile > 0) {
//						if (!ValidatorUtil.validateMobile(Long.toString(mobile.longValue()))) {
//							returnMessage = "Invalid Mobile";
//							break;
//						}
//						employeeUpload.setMobile(Long.toString(mobile.longValue()));
//					}
				} catch (Exception e) {
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
					int salesOfficeId = commonBO.getSalesOfficeIdByCode(salesOfficeCode, employeeUpload.getCompanyId());
					if (salesOfficeId > 0) {
						employeeUpload.setSalesOfficeId(salesOfficeId);
					} else {
						returnMessage = "Invalid Sales Office";
						break;
					}
				} catch (Exception e) {
					returnMessage = "Invalid Sales Office";
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
					int salesUnitId = commonBO.getSalesUnitIdByCode(salesUnitCode, employeeUpload.getCompanyId());
					if (salesUnitId > 0) {
						employeeUpload.setSalesUnitId(salesUnitId);
					} else {
						returnMessage = "Invalid Sales Unit";
						break;
					}
				} catch (Exception e) {
					returnMessage = "Invalid Sales Unit";
				}
				break;
			case Role:
				try {
					String role = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						role = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						role = cell.getStringCellValue();
					}
					if (role.isEmpty()) {
						returnMessage = "Invalid Role";
						break;
					}
					// int roleId = roleMasterBO.getRoleIdByName(role, userCompanyId);
					//RoleMaster rm = roleMasterBO.getRoleByNameForEmployee(role);
					//if (rm != null && (!employeeUpload.getIsWareHouseOperator()
						//	? rm.getRoleName().equalsIgnoreCase(employeeRole)
						//	: rm.getRoleName().equalsIgnoreCase(wareHouseRole))) {
						//employeeUpload.setRoleId(rm.getRoleId());
					//	break;
					//} else {
					//	returnMessage = "Invalid Role";
					//	break;
					//}
				} catch (Exception e) {
					returnMessage = "Invalid Role";
					break;
				}
			case District:
				if (cell != null) {
					String district = "";
					if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						district = Long.toString((new Double(cell.getNumericCellValue())).longValue());
					} else if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						district = cell.getStringCellValue();
					}
					if (!district.isEmpty()) {
						employeeUpload.setDistrict(district);
					} else {
						returnMessage = "Invalid District";
					}
				} else {
					returnMessage = "Invalid District";
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return returnMessage;
	}

	@Override
	public int insertEmployeeDetail(EmployeeUpload employeeUpload, int userId, Company company,
			boolean isWarehouseOperator) throws Exception {

		Location location = new Location();
		location.setDistrict(employeeUpload.getDistrict());
		int count = insertLocation(userId, location);
		if (count <= 0) {
			return count;
		}
		count = 0;
		Address address = new Address();
		//count = insertAddress(userId, location, address);
		if (count <= 0) {
			return count;
		}
		count = 0;
		Contact contact = new Contact();
		contact.setEmail(employeeUpload.getEmail());
		contact.setMobile(employeeUpload.getMobile());
		contact.setAddressid(address.getId());
		count = contactBO.insertContact(contact, userId);

		if (count <= 0) {
			return count;
		}
		count = 0;
		User user = new User();
		user.setCompanyId(employeeUpload.getCompanyId());
		user.setLoginId(employeeUpload.getLoginId());
		user.setGivenName(employeeUpload.getFirstName());
		user.setMiddleName(employeeUpload.getMiddleName());
		user.setFamilyName(employeeUpload.getLastName());
		user.setMainContactId(contact.getId());
		if (employeeUpload.getDateOfBirth() != null)
			user.setDateOfBirth(new Timestamp(employeeUpload.getDateOfBirth().getTime()));
		user.setUserEmailId(employeeUpload.getEmail());
		if (employeeUpload.getMobile() != null)
			user.setContactNo(employeeUpload.getMobile());
		else
			user.setContactNo("");

		user.setAddress("");
		count = userBO.insertUserForEmployee(user, userId);

		if (count <= 0) {
			return count;
		}
		count = 0;
		//UserRole userRole = new UserRole();
		//userRole.setUserId(user.getUserId());
		//userRole.setRoleId(employeeUpload.getRoleId());
		//count = userRoleBO.insert(userRole, userId);

		if (count <= 0) {
			return count;
		}

		Employee employee = new Employee();
		employee.setId(user.getUserId());
		employee.setEmployeecode(employeeUpload.getEmployeeCode());
		employee.setSalesofficeid(199/* employeeUpload.getSalesOfficeId() */);
		employee.setIsWareHouseOperator(isWarehouseOperator);

		int i = insertEmployee(employee, userId);

		/**
		 * Added by Ravindra on 09-01-17 for inserting data to emp_salesoffice
		 */
		// EmpSalesoffice empSalesOffice = new EmpSalesoffice();
		// empSalesOffice.setEmpId(employee.getId());
		// empSalesOffice.setSalesOfficeId(employee.getSalesofficeid());
		// empSalesOfficeDAO.insert(empSalesOffice, employeeUpload.getSalesOfficeId(),
		// userId);
		// empSalesOffice = null;
		// End - Added by Ravindra

		/**
		 * newly added for user password.
		 * 
		 */
		if (isWarehouseOperator && i > 0) {
			String password = generatePasswordForWareHouseOperator(user);
			if (password == null) {
				return -1;
			}
			user.setPassword(password);
			//emailServiceHelper.sendWarehouseUploadMail(employee, company, user);

		}

		if (i > 0 && !isWarehouseOperator) {
			//emailServiceHelper.sendEmployeeUploadMail(employee, company, user);
		}
		return i;
	}

	private String generatePasswordForWareHouseOperator(User user) {
		String sysGeneratedPass = null;
		try {
			sysGeneratedPass = PasswordGeneratorUtil.passwordGenerator();
			logger.info("New password for ware-house-operator " + sysGeneratedPass);
			UserPassword userPassword = new UserPassword();
			userPassword.setUserId(user.getUserId());
			userPassword.setUserName(user.getLoginId());
			userPassword.setStatus(true);
			String encyptpw = Credential.SHA1.digest(Long.toString(System.currentTimeMillis()), sysGeneratedPass);
			userPassword.setUserPassword(encyptpw);
			userPasswordBO.insert(userPassword, user.getUserId());
		} catch (Exception ex) {
			logger.error("Exception in generatePasswordForWareHouseOperator " + ex);
		}
		return sysGeneratedPass;

	}

	@Override
	public boolean isEmployeeCodeExists(String EmployeeCode) throws Exception {
		return employeeDAO.isEmployeeCodeExists(EmployeeCode);
	}

	@Override
	public UserProfile getUserProfileDetail(int userId) throws Exception {
		return employeeDAO.getUserProfileDetail(userId);
	}

	@Override
	public Boolean checkForWareHouseOperator(Integer userId) {
		return employeeDAO.checkForWareHouseOperator(userId);
	}

	/*private int insertAddress(int sequenceForUserId, Location location, Address address) {
		int insertCount;
		address.setStreetSide("");
		address.setStreetName("");
		address.setLocationId(location.getId());
		address.setEntityUid(UUID.randomUUID().toString());
		address.setCreator(Integer.toString(sequenceForUserId));
		address.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		address.setModifier(Integer.toString(sequenceForUserId));
		address.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));

		insertCount = addressBO.insertAddress(address, sequenceForUserId);
		return insertCount;
	}*/

	/**
	 * Private method to insert Location for User
	 * 
	 * @param userRegistrationRequest
	 * @param sequenceForUserId
	 * @param location
	 * @return 1 if success else 0
	 */
	private int insertLocation(int sequenceForUserId, Location location) {
		int insertCount;
		location.setCity("");
		location.setPostalCode("");
		location.setStateProv("");
		location.setCreator(Integer.toString(sequenceForUserId));
		location.setEntityUid(UUID.randomUUID().toString());
		location.setCreatedate(new Timestamp((new java.util.Date()).getTime()));
		location.setModifier(Integer.toString(sequenceForUserId));
		location.setUpdatedate(new Timestamp((new java.util.Date()).getTime()));
		location.setIsDeleted(0);
		insertCount = locationBO.insertLocation(location, sequenceForUserId);
		return insertCount;
	}

	@Override
	public Employee findEmployeeByCode(String employeeCode) {
		
		return employeeDAO.findEmployeeByCode(employeeCode);
	}

	@Override
	public Map<String, Object> uploadEmployee(File file, int userId, Company company) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> uploadWHOperator(File file, int userId, Company company) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
