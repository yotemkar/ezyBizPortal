/**
 * 
 */
package com.hssa.ezybiz.utils;

public class CustomerPortalConstants {

	public static final String CUSTOMER_ROLE = "Customer";
	public static final String Employee_ROLE = "Employee";
	public static final String Admin_ROLE = "Admin";
	public static final String Salespromotor_ROLE = "MultiAccountAdmin";
	public static final String DELCREDER_ROLE = "Delcreder";
	public static final String Associate_ROLE="Associate";
	public static final String Associate_Salespromotor_ROLE = "AssociateMultiAccount";


	public static final int LA_ACCOUNT_BLOCK = 1;
	public static final int LA_LOGIN_PAGE = 2;
	public static final int LA_FORCE_LOGIN_FORM = 3;
	public static final int LA_FORCE_CHANG_PASSWORD = 4;
	public static final int LA_DEFAULT = 5;

	public static final int CUSTOMER_NOTFOUND = -5;

	public static final int NEW_USER = 1;
	public static final int BLOCKED_USER = 4;
	public static final String SEQUENCE_USERID = "UserIDSequence";
	public static final int PASSWORD_CHANGED_UNSUCCESSFULLY = 3;
	public static final int PASSWORD_MAIL_SUCCESSFULLY = 12;
	public static final int PASSWORD_NOT_VALID_USER = 7;

	public static final String RESOURCES_PROPERTIES_FILE_PATH = "/com/lafargeholcim/customerportal/resources/resources.properties";
	public static final String SEQUENCE_LOOKUP_DEPENDENCYID = "LookupDependencySequence";
	public static final String SEQUENCE_LOOKUP_MASTERID = "LookupMasterSequence";
	public static final String SEQUENCE_OrderException = "OrderexceptionSequence";
	public static final String SEQUENCE_PostOrderHeader = "PostedorderheaderSequence";
	public static final String SEQUENCE_AuditTrailEntityMaster = "AuditTrailEntityMasterSequence";
	public static final String SEQUENCE_IgImage = "IgImageSequence";
	public static final String SEQUENCE_Customer = "CustomerSequence";
	public static final String SEQUENCE_DistributionChannel = "DistributionChannelSequence";
	public static final String SEQUENCE_SalesOrg = "SalesOrgnisationSequence";
	public static final String SEQUENCE_SalesOffice = "SalesOfficeSequence";
	public static final String SEQUENCE_SalesArea = "SalesAreaSequence";
	public static final String SEQUENCE_SalesGroup = "SalesGroupSequence";

	public static final String ERROR = "error";
	public static final String SUCCESS = "success";

	/**
	 * Mail constants
	 */
	public static final String Mail_CONFIG_FILE_NAME = "mailConfig.properties";

	/**
	 * forgot password
	 */
	public static final String EMAIL_NOT_FOUND = "Email not registered";
	public static final String MOBILE_NOT_FOUND = "Mobile not registered";
	public static final String FORGOT_PASSWORD_FAILED = "Error occured while sending password";
	public static final String FORGOT_PASSWORD_NOT_PRVILIEGED = "This prvilege has not been provided to user.";
	public static final String FORGOT_PASSWORD_SUCCESS = "Password has been sent to email id";
	public static final String FORGOT_PASSWORD_INACTIVE = "Email or Mobile not registered";

	public static final String PASSWORD_EXPIRED = "password expired days";
	public static final String BLOCK_ATTEMPTS = "password blocked attempts";
	public static final String PASSWORD_CHANGED_SUCCESS = "Password changed successfuly.Please login again with new password.";
	public static final String OLD_PASSWORD_IS_NOT_FOUND_MSG = "Old password does not match";
	public static final String PASSWORD_HISTORY_ERROR_MSG = "Password must not be same as previous 5 passwords";
	public static final String WRONG_OLD_PASSWORD = "WrongOldPassword";
	public static final String PASSWORD_HISTORY_ERROR = "passwordHistoryError";

	public static final String PASSWORD_CHANGED_ERROR = "Error occured while changing the password";
	public static final String USER_NOT_FOUND = "User does not exists";

	// Added by Mohsin for Navendor Order Error Message.
	public static final String RETAILER_CUSTOMER_MAPPING_ERROR = "Given Retailer Customer Mapping is Incorrect";
	public static final String RETAILER_MATERIAL_ERROR = "Given Material Article is Incorrect";
	public static final String RETAILER_UPDATE_ERROR = "Given Order Cannot be updated";
	// Mohsin Code ends here

	/**
	 * Order constants
	 */

	public static final String PLACE_ORDER_FAILED = "Error occured while placing the order";

	public enum UserType {
		CUSTOMER("C"), EMPLOYEE("E");
		private final String value;

		UserType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static UserType getEnumByValue(String value) {
			for (UserType v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	public enum EmployeeUploadHeader {
		COMPANYID("COMPANYID"), LOGINID("LOGINID"), EMPCODE("EMPCODE"), FIRSTNAME("FIRST NAME"), MIDDLENAME(
				"MIDDLE NAME"), LASTNAME("LAST NAME"), GENDER("GENDER"), DATEOFBIRTH("DATE OF BIRTH"), EMAIL(
						"EMAIL"), MOBILE("MOBILE"), SALESOFFICE(
								"SALESOFFICE"), SALESUNIT("SALESUNIT"), Role("Role"), District("DISTRICT");

		private final String value;

		EmployeeUploadHeader(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static EmployeeUploadHeader getEnumByValue(String value) {
			for (EmployeeUploadHeader v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	public enum CustomerUploadHeader {
		CODE("KUNNR"), EMAIL("EMAIL"), MOBILE("MOBILE"), SALESUNIT("SALES UNIT"), SALESOFFICE("SALES OFFICE"), NAME(
				"Name"), STREETNAME("Street Name"), STREETSIDE("Street Side"), CITY("City"), POSTALCODE(
						"Postal Code"), STATE(
								"State"), DCODE("CODE"), OFFICEREMAIL("OFFICER EMAIL"), OFFICERID("OFFICER ID");

		private final String value;

		CustomerUploadHeader(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static CustomerUploadHeader getEnumByValue(String value) {
			for (CustomerUploadHeader v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	public enum RetailerUploadHeader {
		CODE("CODE"), EMAIL("EMAIL"), MOBILE("MOBILE"), NAME("NAME"), SALESOFFICE("SALESOFFICE CODE"), PARENTID(
				"PARENTID"), ISPARENT("ISPARENT"), CITY(
						"CITY"), SHOPADDRESS("SHOPADDRESS"), SHOPCONTACT("SHOPCONTACT"), SHOPNAME("SHOPNAME");

		private final String value;

		RetailerUploadHeader(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static RetailerUploadHeader getEnumByValue(String value) {
			for (RetailerUploadHeader v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	public enum MapCustomerSalesPromoterHeader {
		CCODE("Customer Code"), SCODE("SalesPromoter Code"), MAP("Map");

		private final String value;

		MapCustomerSalesPromoterHeader(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static MapCustomerSalesPromoterHeader getEnumByValue(String value) {
			for (MapCustomerSalesPromoterHeader v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Mail placeholders
	 */
	public static final String PLACEHOLDER_LOGINID = "<loginId>";
	public static final String PLACEHOLDER_PASSWORD = "<password>";
	public static final String PLACEHOLDER_PORTALURL = "<portalURL>";
	public static final String PLACEHOLDER_COMPANY_NAME = "<companyName>";

	/**
	 * retailer order
	 * 
	 */
	public static final String DISAPPROVE_ERROR = "disApproveError";
	public static final String DISAPPROVE_ERROR_RESULT = "Error occured while rejecting order";
	public static final String DISAPPROVE_SUUCESS = "disApproveSuccess";
	public static final String DISAPPROVE_SUUCESS_RESULT = "Order rejected successfully";
	public static final String CONVERT_WEB_ORDER_ERROR = "convertWebOrderError";
	public static final String CONVERT_WEB_ORDER_ERROR_RESULT = "Error occured while accpeting order";
	public static final String CONVERT_WEB_ORDER_SUCCESS = "convertWebOrderSuccess";
	public static final String CONVERT_WEB_ORDER_SUCCESS_RESULT = "Order accepted successfully.";
	public static final String EMPTY_SHIP_TO_PARTY_ERROR = "emptyShipToPartyError";
	public static final String EMPTY_SHIP_TO_PARTY_ERROR_RESULT = "Ship-To-Party is not attached to the dealer.";

	public static final String REQUEST_MAPPING_CONPANY_ID = "${companyId}";
	public static final String REQUEST_MAPPING_CONPANY = "${company}";
	public static final String REQUEST_MAPPING_PORTAL_URL = "${portalURL}";

	public static final String LOG_FILE_NAME_MAIL = "mailLogger";

	public static final String EMAIL_TEMPLATE_REGISTER_USER = "RegisterUser";
	public static final String EMAIL_TEMPLATE_FORGOT_PASSWORD = "ForgotPassword";
	public static final String EMAIL_SENDING_TO = "Mail Sending To: ";
	public static final String EMAIL_SENT_TO = "Mail Sent To: ";

	public static final String GENDER_MALE = "100";
	public static final String GENDER_FEMALE = "101";
	public static final String GENDER_OTHER = "102";
	public static final Integer MARRITAL_STATUS_MARRIED = 111;
	public static final Integer MARRITAL_STATUS_SINGLE = 110;
	public static final String ALREADY_EXIST_ERROR_RESULT = "Records already exsits";
	public static final String PROOF_OF_DISPATCH_SUCCESS_RESULT = "Record added successfully";
	public static final String PROOF_OF_DISPATCH_ERROR_RESULT = "Error occured while adding record.";
	public static final String ADD_SECONDARY_SALES_SHIP_TO_PARTY_ERROR = "Ship to party provided is not order partner of the customer ";
	public static final String ADD_SECONDARY_SALES_ERROR = "Records could not be saved";
	public static final String ADD_SECONDARY_SALES_SUCCESS = "Order added successfully";
	public static final String ADD_SECONDARY_SALES_SAP_ERROR = "No data retrieved from sap RFC";
	public static final String EDIT_SECONDARY_SALES_SUCCESS = "Order edited successfully";
	public static final String DELETE_SECONDARY_SALES_SUCCESS = "Order deleted successfully.";
	public static final String DELETE_SECONDARY_SALES_ERROR = "Error occured while deleting the record.";
	public static final String CREDIT_AVAILABLE = "credit_Available";
	public static final String RECEIVABLES = "receivables";
	public static final String AUTHORIZATION = "Authorization";
	public static final String NO_CONTRACT_AVAILABLE = "No contract available";
	public static final String USER_NOT_EXISTS_MSG = "User does not exists";
	public static final String USER_ID_NOT_EXISTS = "UserNotExistsError";
	public static final String BAD_CREDENTIAL = "badCredential";
	public static final String USER_ID_LOCKED_MSG = "userIdLocked";
	public static final String DIFFERENT_COMPANY = "differentCompany";
	public static final String USER_ID_LOCKED = "UserIdLocked";
	public static final String WRONG_CREDENTIAL_MSG = "wrongCredential";
	public static final String INVALID_USER_ID = "invalidUserId";
	public static final String INVALID_OTP = "invalidOtp";
	public static final String ACCOUNT_LOCKED_MSG = "accountLocked";
	public static final String LOGIN_ERROR = "loginError";
	public static final String SWITCH_USER_ERROR = "Error occured while switching to the user.";
	public static final String EMPLOYEE_ACCOUNT_LOCKED_MSG = "employeeAccountLocked";
	public static final String ACCESS_DENIED = "accessDenied";
	public static final String CUSTOMER_INACTIVE = "customerInactive";
	public static final String USER_INACTIVE = "userInactive";
	public static final String USER_MOBILE_NOT_UPDATED = "userMobileNotUpdated";
	public static final String WRONG_GOOGLE_CREDENTIAL_MSG = "Google Auth Fails";


	/*
	 * Content
	 */
	public static final String CONTENT_ADD_SUCCESS = "Content added successfully";
	public static final String CONTENT_ADD_ERROR = "Error occured while adding content";
	public static final String CONTENT_NAME_EXISTS_ERROR = "Page already exists";
	public static final String CONTENT_UPDATE_SUCCESS = "Content updated successfully";
	public static final String CONTENT_UPDATE_ERROR = "Error occured while updating content";
	public static final String EMPTY_PAGE_NAME = "EMPTY_PAGE_NAME";
	public static final String ERROR_MOVING_FILES = "Error occured while moving files";
	public static final String IMAGE_DELETE_SUCCESS = "Image deleted successfully";
	public static final String IMAGE_DELETE_ERROR = "Error occured while deleting image";
	public static final String IMAGE_UPDATE_SUCCESS = "Image updated successfully";
	public static final String IMAGE_UPDATE_ERROR = "Error occured while updating image";
	public static final String LOGIN_PAGE_NAME = "Login";
	public static final String DASHBOARD_PAGE_NAME = "Dashboard";
	public static final String NEWS_PAGE_NAME = "News";
	public static final String BIRTHDAY_MESSAGE_PAGE_NAME = "BirthdayMessage";

	/*
	 * Incident Escalation
	 */
	public static final String INCIDENT_ESCALATION_ADD_SUCCESS = "Incident Escalation added successfully";
	public static final String INCIDENT_ESCALATION_ADD_ERROR = "Error occured while adding Incident Escalation";
	public static final String INCIDENT_ESCALATION_EXISTS_ERROR = "Escaltion Level for Sales Office already exists";
	public static final String INCIDENT_ESCALATION_UPDATE_SUCCESS = "Incident Escalation updated successfully";
	public static final String INCIDENT_ESCALATION_UPDATE_ERROR = "Error occured while updating Incident Escalation";
	public static final String INCIDENT_ESCALATION_DELETE_SUCCESS = "Incident Escalation deleted successfully";
	public static final String INCIDENT_ESCALATION_DELETE_ERROR = "Error occured while deleting Incident Escalation";

	/*
	 * User roles
	 */
	public static final String USER_ROLES_UPDATE_SUCCESS = "User roles updated successfully";
	public static final String USER_ROLES_UPDATE_ERROR = "Error occured while updating User roles";

	/*
	 * Menu roles
	 */
	public static final String MENU_ROLES_UPDATE_SUCCESS = "Menu roles updated successfully";
	public static final String MENU_ROLES_UPDATE_ERROR = "Error occured while updating Menu roles";

	/*
	 * Incident Category
	 */
	public static final String CMSINCATEGORY_ADD_SUCCESS = "Incident Category added successfully";
	public static final String CMSINCATEGORY_ADD_ERROR = "Error occured while adding Incident Category";
	public static final String CMSINCATEGORY_UPDATE_SUCCESS = "Incident Category updated successfully";
	public static final String CMSINCATEGORY_UPDATE_ERROR = "Error occured while updating Incident Category";

	public static final String CMSINSUBCATEGORY_ADD_SUCCESS = "Incident SubCategory added successfully";
	public static final String CMSINSUBCATEGORY_ADD_ERROR = "Error occured while adding Incident SubCategory";
	public static final String CMSINCHILDSUBCATEGORY_ADD_SUCCESS = "Incident ChildSubCategory added successfully";
	public static final String CMSINCHILDSUBCATEGORY_ADD_ERROR = "Error occured while adding Incident ChildSubCategory";

	/*
	 * Customer Group
	 */
	public static final String ADD_CUSTOMERGROUP_SUCCESS = "Customer Group added successfully";
	public static final String ADD_CUSTOMERGROUP_ERROR = "Error occured while adding Customer Group";
	public static final String CUSTOMERGROUP_UPDATE_SUCCESS = "Customer Group updated successfully";
	public static final String CUSTOMERGROUP_UPDATE_ERROR = "Error occured while updating Customer Group";
	public static final String CUSTOMERGROUP_DELETE_SUCCESS = "Customer Group deleted successfully";
	public static final String CUSTOMERGROUP_DELETE_ERROR = "Error occured while deleting Customer Group";
	public static final String IS_FIRST_TIME_LOGIN = "isFirstTimeLogin";

	/*
	 * Message Board
	 */
	public static final String MBTOPIC_ADD_SUCCESS = "Topic added successfully";
	public static final String MBTOPIC_ADD_ERROR = "Error occured while adding Topic";
	public static final String MBTOPIC_UPDATE_SUCCESS = "Topic updated successfully";
	public static final String MBTOPIC_UPDATE_ERROR = "Error occured while updating Topic";
	public static final String MBTOPIC_DELETE_SUCCESS = "Topic deleted successfully";
	public static final String MBTOPIC_DELETE_ERROR = "Error occured while deleting Topic";

	/*
	 * Customer
	 */
	public static final String CUSTOMER_UPDATE_SUCCESS = "Customer updated successfully";
	public static final String CUSTOMER_USER_UPDATE_SUCCESS = "Customer updated successfully";
	public static final String CUSTOMER_UPDATE_ERROR = "Error occured while updating Customer";
	public static final String CUSTOMER_Email_ERROR = "Email id already Exists in the system";
	public static final String CUSTOMER_Mobile_ERROR = "Mobile already Exists in the system";

	/*
	 * Incident Rating
	 */
	public static final String INCIDENT_RATING_ADD_SUCCESS = "Incident Rating added successfully";
	public static final String INCIDENT_RATING_ADD_ERROR = "Error occured while adding Incident Rating";
	public static final String INCIDENT_RATING_UPDATE_SUCCESS = "Incident Rating updated successfully";
	public static final String INCIDENT_RATING_UPDATE_ERROR = "Error occured while updating Incident Rating";
	public static final String INCIDENT_RATING_DELETE_SUCCESS = "Incident Rating deleted successfully";
	public static final String INCIDENT_RATING_DELETE_ERROR = "Error occured while deleting Incident Rating";

	/*
	 * CMS Incident
	 */
	public static final String CMSINCIDENT_ADD_SUCCESS = "Complaint posted successfully";
	public static final String CMSINCIDENT_ADD_ERROR = "Error occured while posting a complaint";
	public static final String CMSINCIDENT_UPDATE_SUCCESS = "Feedback updated successfully";
	public static final String CMSINCIDENT_UPDATE_ERROR = "Error occured while updating Feedback";

	/*
	 * Customer Message Board
	 */
	public static final String MBMESSAGE_ADD_SUCCESS = "Message posted successfully";
	public static final String MBMESSAGE_ADD_ERROR = "Error occured while posting message";

	/*
	 * Menu Master
	 */
	public static final String UNABLE_TO_DELETE_PARENTMENU = "Please delete all assigned submenus before deleting parent menu";
	public static final String DELETE_MENU_SUCCESS = "Menu deleted successfuly";
	public static final String DELETE_MENU_ERROR = "Error occured while updating menu";
	public static final String SUBMENU_ADD_SUCCESS = "Menu added successfuly";
	public static final String SUBMENU_ADD_ERROR = "Error occured while adding menu";
	public static final String SUBMENU_EDIT_SUCCESS = "Menu updated successfuly";
	public static final String SUBMENU_EDIT_ERROR = "Error occured while updating menu";

	/*
	 * Vendor
	 */
	public static final String VENDOR_NAME_EXISTS = "Name already exists";
	public static final String VENDOR_CODE_EXISTS = "Code already exists";
	public static final String VENDOR_ADD_SUCCESS = "Vendor added successfully";
	public static final String VENDOR_ADD_ERROR = "Error occured while adding Vendor";
	public static final String VENDOR_UPDATE_SUCCESS = "Vendor updated successfully";
	public static final String VENDOR_UPDATE_ERROR = "Error occured while updating Vendor";
	public static final String VENDOR_DELETE_SUCCESS = "Vendor deleted successfully";
	public static final String VENDOR_DELETE_ERROR = "Error occured while deleting Vendor";
	public static final String AUTHENTICATED_USER = "authenticatedUser";

	/*
	 * Employee SalesOffice
	 */
	public static final String EMP_SALESOFFICE_UPDATE_SUCCESS = "Sales offices for employee updated successfully";
	public static final String EMP_SALESOFFICE_UPDATE_ERROR = "Error occured while updating Sales offices for employee";
	public static final String EMP_CrdRepGrp_UPDATE_SUCCESS = "Credit Rep group for employee updated successfully";
	public static final String EMP_CrdRepGrp_UPDATE_ERROR = "Error occured while updating Credit Rep group for employee";
/*Dontact Us Details*/
	public static final String CONTACTUS_DETAIL_ADD_SUCCESS = "ContactUs Request posted successfuly";
	public static final String CONTACTUS_DETAIL_ADD_ERROR = "Error occurde while posting ContactUs request";
	public static final String CONTACTUS_DETAIL_UPDATED_SUCCESS = "ContactUs Request Updated successfuly";



	/*
	 * Help Details
	 */
	public static final String HELP_DETAIL_ADD_SUCCESS = "Help Request posted successfuly";
	public static final String HELP_DETAIL_ADD_ERROR = "Error occurde while posting help request";
	public static final String POST_SUCCESS = "postSuccess";
	public static final String CONTARCT_ERROR = "contractError";
	public static final String NO_CHANGE = "noChange";
	public static final String IS_WARE_HOUSE_OPERATOR = "isWareHouseOperator";
	public static final String IS_CUSTOMER_ASSOCIATE = "isCustomerAssociate";
	public static final String PENDING = "PENDING";
	public static final String CUSTOMER_Email_with_Active_user_ERROR = "Email id already Exists in the system With Other Active User";

	public static enum RejectionStatus {

		D0001("D0001", "Credit Limit Exceeded"), D0002("D0002", "Contract Validity Expired "), D0003("D0003",
				"Invalid Material"), D0004("D0004", "Contract exhausted completely"), D0005("D0005", "Target Exceeded");

		private String code, label;

		private RejectionStatus(final String code, final String label) {
			this.code = code;
			this.label = label;
		}

		public String getCode() {
			return this.code;
		}

		public String getLabel() {
			return this.label;
		}

		public void setCode(final String code) {
			this.code = code;
		}

		public void setLabel(final String label) {
			this.label = label;
		}

		public static String getLabelByValue(String code) {
			for (RejectionStatus v : values())
				if (v.getCode().equalsIgnoreCase(code))
					return v.getLabel();
			throw new IllegalArgumentException();
		}
	}

	public static enum LookupMaster {
		GENDER("Gender"), POSITION("Positions"), USERFUNCTIONS("UserFunctions"), MARITALSTATUSES(
				"MaritalStatuses"), SUPERROLES("SuperRoles"), DELIVERYPRIORITY("Delivery Priority");
		private String value;

		LookupMaster(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public static enum ProblemType {

		/*
		 * D0001("D0001", "Credit Limit Exceeded"), D0002("D0002",
		 * "Contract Validity Expired "), D0003("D0003", "Invalid Material"),
		 * D0004("D0004", "Contract exhausted completely"), D0005("D0005",
		 * "Target Exceeded");
		 */

		BG("BG", "Bad Gateway"), PNO("PNO", "Portal not opening"), MSOB("MSOB", "Multiple sales orders booked"), SOND(
				"SOND", "SO number not displayed"), POND("POND", "Pending orders not displayed"), SP("SP",
						"Slow portal"), WONC("WONC", "Web order not created"), EDP("EDP",
								"Error during processing"), OTHER("OTHER", "Other");

		private String label, value;

		private ProblemType(final String label, final String value) {
			this.value = value;
			this.label = label;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.label;
		}

		public void setValue(final String value) {
			this.value = value;
		}

		public void setLabel(final String label) {
			this.label = label;
		}
	}

}
