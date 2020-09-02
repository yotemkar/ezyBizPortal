package com.hssa.ezybiz.controllers;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hssa.ezybiz.config.GoogleAuthenticationService;
import com.hssa.ezybiz.config.JwtAuthenticationRequest;
import com.hssa.ezybiz.config.JwtAuthenticationResponse;
import com.hssa.ezybiz.config.JwtTokenUtil;
import com.hssa.ezybiz.config.JwtUser;
import com.hssa.ezybiz.config.LdapAuthenticationService;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.RoleDto;
import com.hssa.ezybiz.dto.Salesorganisation;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserLoginDetails;
import com.hssa.ezybiz.dto.Userlogintracker;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.ContentBO;
import com.hssa.ezybiz.services.CoretexService;
import com.hssa.ezybiz.services.CustomerBO;
import com.hssa.ezybiz.services.CustomeruserBO;
import com.hssa.ezybiz.services.EmployeeBO;
import com.hssa.ezybiz.services.LocationBO;
import com.hssa.ezybiz.services.SalesorganisationBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.services.UserLoginDetailsBO;
import com.hssa.ezybiz.services.UserBO;
import com.hssa.ezybiz.services.UserlogintrackerBO;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.SequenceConstants;

@RestController
@RequestMapping(value = "/UserLoginBeanService")
public class UserLoginController {

	final static Logger LOG = LoggerFactory.getLogger(UserLoginController.class);
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserLoginService");

	private static final String ROLE_EMPLOYEE = "Employee";
	private static final String ROLE_CUSTOMER = "Customer";
	private static final String ROLE_ADMIN = "Admin";
	private static final String ROLE_WARE_HOUSE = "WareHouseOperator";
	private static final String SWITCH_USER = "switchUser";
	
	private static final Integer FIRST_WRONG_PASSWORD_ATTEMPT_COUNT = 1;
	
	@Autowired
	private UserBO userBo;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private CustomerBO customerBO;
	
	@Autowired
	private CustomeruserBO customeruserBO;
	
	@Autowired
	private UserLoginDetailsBO userLoginDetailsBO;	

	@Autowired
	ContactBO contactBO;
	
	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private SalesorganisationBO salesorganisationBO;
	
	@Autowired
	LocationBO locationBO;
	
	@Autowired
	private SequencesBO sequencesBO;
	
	@Autowired
	CoretexService coretexService;
	
	@Autowired
	private UserlogintrackerBO userlogintrackerBO;
	
	@Value("${service.Ocp-Apim-Subscription-Key}")
    private String ocpApimSubscriptionKey;
	
	@Autowired
	private GoogleAuthenticationService googleAuthenticationService;
	
	@Autowired
	private LdapAuthenticationService ldapAuthenticationService;
	

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Value("${employeeLdapAuthentication}")
	private Boolean employeeLdapAuthentication;
	
	@Value("${passwordMaxWronCount}")
	private Integer passwordMaxWronCount;

	@Value("${customerLdapAuthentication}")
	private Boolean customerLdapAuthentication;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			 HttpServletRequest request) throws AuthenticationException {
		Map<String, Object> statusMap = new HashMap<>();
		Boolean isWareHouseOperator = false;
		request.getRemoteAddr();
		request.getRemoteHost();
		Customer customer = null;
		try {

			InetAddress address = InetAddress.getByName(request.getRemoteAddr());
			//logger.info(address.getCanonicalHostName());
		} catch (Exception exception) {

		}
		String userType;
		String deviceType="Desktop";
		//String deviceType = device.isMobile() ? "Mobile" : device.isTablet() ? "Tablet" : "Desktop";
		Boolean isCheckUserLoginDetails = true;
		String roleName = null;
		User user=null;
		if (authenticationRequest.getIsGoogleAuth() != null && authenticationRequest.getIsGoogleAuth()) {
			user = userBo.getUserByEmailId(null, authenticationRequest.getEmail());

		} else {
			user = userBo.getUserByName(null, authenticationRequest.getUsername());

		}
		// user = userBo.getUserByName(null,authenticationRequest.getUsername());

		// obtain locale from LocaleContextHolder
		Locale currentLocale = request.getLocale();
		/*
		 * String referer = request.getHeader("referer"); if(null == referer ||
		 * "Mobile".equals(deviceType)) { referer = request.getHeader("mobilereferer");
		 * } Integer companyId = commonBO.getCompanyId(request.getHeader("referer"));
		 */
		Integer companyId = null;// commonBO.getCompanyId(CommonWebUtil.getReferer(request, deviceType));
		/*
		 * if(companyId==null){
		 * logger.error("log in failed due to null company id . Requestes URL is ==>"+
		 * request.getRequestURL().toString());
		 * statusMap.put(CustomerPortalConstants.ERROR,
		 * messageSource.getMessage(CustomerPortalConstants.LOGIN_ERROR , null,
		 * currentLocale)); return new ResponseEntity<>(statusMap,HttpStatus.OK); }
		 */

		if (user != null) {
			companyId = user.getCompanyId();
			if (companyId == null || companyId.equals(0)) {
//				logger.error("log in failed due to null company id . Requestes URL is ==>"
//						+ request.getRequestURL().toString());
				statusMap.put(CustomerPortalConstants.ERROR,
						messageSource.getMessage(CustomerPortalConstants.LOGIN_ERROR, null, currentLocale));
				return new ResponseEntity<>(statusMap, HttpStatus.OK);
			}
			// check for company
			/*
			 * if(user.getCompanyId()!=companyId){
			 * statusMap.put(CustomerPortalConstants.ERROR,
			 * messageSource.getMessage(CustomerPortalConstants.DIFFERENT_COMPANY, null,
			 * currentLocale)); return new ResponseEntity<>(statusMap,HttpStatus.OK); }
			 */

			// check for user active and inactive
			if (user.getUserStatus() == 0) {
				statusMap.put(CustomerPortalConstants.ERROR,
						messageSource.getMessage(CustomerPortalConstants.USER_INACTIVE, null, currentLocale));
				return new ResponseEntity<>(statusMap, HttpStatus.OK);
			}

			userType = userBo.getUserTypeByUserId(user.getUserId());

			/**
			 * check for ware house operator
			 */
			if (ROLE_EMPLOYEE.equals(userType)) {
				if (Boolean.TRUE.equals(employeeBO.checkForWareHouseOperator(user.getUserId()))) {
					isWareHouseOperator = true;
					if (user.getEmployeeLocked() != null && user.getEmployeeLocked() == 1) {
						statusMap.put(CustomerPortalConstants.USER_ID_LOCKED, messageSource
								.getMessage(CustomerPortalConstants.EMPLOYEE_ACCOUNT_LOCKED_MSG, null, currentLocale));
						return new ResponseEntity<>(statusMap, HttpStatus.OK);
					}
					roleName = ROLE_WARE_HOUSE;
				}
			}

			if (!isWareHouseOperator) {
				// check for employee
				if (ROLE_EMPLOYEE.equals(userType) || ROLE_ADMIN.equals(userType)) {
					if (user.getEmployeeLocked() != null && user.getEmployeeLocked() == 1) {
						statusMap.put(CustomerPortalConstants.USER_ID_LOCKED, messageSource
								.getMessage(CustomerPortalConstants.EMPLOYEE_ACCOUNT_LOCKED_MSG, null, currentLocale));
						return new ResponseEntity<>(statusMap, HttpStatus.OK);
					} else {
						// check if google Auth
						if (authenticationRequest.getIsGoogleAuth()) {
							if (!googleAuthenticationService.authenticateUserFromGoogle(
									authenticationRequest.getEmail(), authenticationRequest.getGoogleIdToken(),
									authenticationRequest.getCode())) {
								// google auth unsuccess
								statusMap.put(CustomerPortalConstants.ERROR, messageSource.getMessage(
										CustomerPortalConstants.WRONG_GOOGLE_CREDENTIAL_MSG, null, currentLocale));
								return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
							} else {
								// google Auth is success
								// setting usename and password so that further code need not to be change for
								// google auth
								authenticationRequest.setUsername(user.getLoginId());
								authenticationRequest.setPassword(authenticationRequest.getCode());
								roleName = ROLE_EMPLOYEE;
								isCheckUserLoginDetails = false;
							}
						}
						// check LDAP
						else if (employeeLdapAuthentication) {
							if (!ldapAuthenticationService.authenticateUserFromLDAP(authenticationRequest.getUsername(),
									authenticationRequest.getPassword())) {
								statusMap.put(CustomerPortalConstants.ERROR, messageSource
										.getMessage(CustomerPortalConstants.WRONG_CREDENTIAL_MSG, null, currentLocale));
								return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
							} else {
								roleName = ROLE_EMPLOYEE;
								isCheckUserLoginDetails = false;
							}
						} // else block added by rehman for bypassing ldap auth by setting
							// employeeLdapAuthentication flage to false in customerportal.properties file
						else {
							roleName = ROLE_EMPLOYEE;
							isCheckUserLoginDetails = false;
						} // changes end here
					}
				} else {// if(customerRole!=null){
						// check for customer
					if (customerLdapAuthentication) {
						statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.ACCESS_DENIED);
						return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
					} else {
						roleName = ROLE_CUSTOMER;
						/**
						 * Add conditions here (locked,blocked,active,inactive)
						 */

						customer = customerBO.findCustomerByUserId(user.getUserId());
						if (customer != null && (customer.getAccessPortal() == null
								|| (customer.getAccessPortal() != null && customer.getAccessPortal() == 0))) {
							statusMap.put(CustomerPortalConstants.CUSTOMER_INACTIVE, messageSource
									.getMessage(CustomerPortalConstants.CUSTOMER_INACTIVE, null, currentLocale));
							return new ResponseEntity<>(statusMap, HttpStatus.OK);
						}

						// check for owner/ asscoiate
						if (customer != null && user != null)
							statusMap.put(CustomerPortalConstants.IS_CUSTOMER_ASSOCIATE,
									customeruserBO.checkCustomerUserAssociate(customer.getId(), user.getUserId()));

					}
				}
			}

			// check if locked or not
			UserLoginDetails userLoginDetails = null;
			if (userLoginDetailsBO.getUserLoginDetailsCountByUserId(user.getUserId()) != 0) {
				userLoginDetails = userLoginDetailsBO.getUserLoginDetailsByUserId(user.getUserId());
			}

			if (isCheckUserLoginDetails && userLoginDetails != null
					&& Boolean.TRUE.equals(userLoginDetails.getLocked())) {
				statusMap.put(CustomerPortalConstants.USER_ID_LOCKED,
						messageSource.getMessage(CustomerPortalConstants.USER_ID_LOCKED_MSG, null, currentLocale));
				return new ResponseEntity<>(statusMap, HttpStatus.OK);
			} else if (user != null && (user.getUserStatus() == 0)) {
				statusMap.put(CustomerPortalConstants.USER_INACTIVE,
						messageSource.getMessage(CustomerPortalConstants.USER_INACTIVE, null, currentLocale));
				return new ResponseEntity<>(statusMap, HttpStatus.OK);
			} else {
				try {
					// Perform the security
					final Authentication authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
									user.getUserId() + "," + authenticationRequest.getUsername() + "," + roleName + ","
											+ authenticationRequest.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(authentication);

					// Reload password post-security so we can generate token
					final JwtUser userDetails = (JwtUser) userDetailsService
							.loadUserByUsername(authenticationRequest.getUsername());
					// userDetails.setUserType(userType);
					final String token = jwtTokenUtil.generateToken(userDetails);
					userDetails.setMobileNumber(contactBO.getPhoneNumberByUserId(userDetails.getUserId()));
					// set resourcess depend on Company id
					userDetails.setResourcessMap(contentBO.findContentMapByCompanyID(userDetails.getCompanyid()));
					//set Coretex session Id for traking Order
					userDetails.setCoretexSessionID(coretexService.getCoretexSessionId());
					//setting Header Value for Coretex
					userDetails.setOcpApimSubscriptionKey(ocpApimSubscriptionKey);
					// set salesOrg's base on customer Code
					if (customer != null) {
						List<Salesorganisation> list = new ArrayList<Salesorganisation>();
						list.add(salesorganisationBO.getSaleOrganisationByCode(customer.getSalesOrgCode()));
						userDetails.setSalesOrgs(list);
						userDetails.setCustomer(customer);

					} // userDetails.setSalesOrgs(salesorganisationBO.getAllSalesOrgByCustomerCode(customer.getCode()));
						// Added below code to add warehouse district in user object by Mohsn.
					RoleDto role = null;
					String district = "";
					Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
					for (Object x : roles) {
						role = (RoleDto) x;
					}
					if (role != null && role.getRole().equals("WareHouseOperator")) {
						district = locationBO.getUserDistrict(userDetails.getUserId() + "");
					}
					userDetails.setDistrict(district);
					// code ends here

					// create the user login service

					if (userLoginDetails == null) {
						UserLoginDetails userLoginDetailsSuccess = new UserLoginDetails();
						userLoginDetailsSuccess
								.setId(sequencesBO.getNextValue(SequenceConstants.USER_LOG_IN_DETAILS_SEQUENCE));
						userLoginDetailsSuccess.setUserId(user.getUserId());
						userLoginDetailsSuccess.setLastLogInSuccess(new Timestamp(new Date().getTime()));
						userLoginDetailsSuccess.setLastLogInFailure(null);
						userLoginDetailsSuccess.setWrongPasswordAttempts(0);
						userLoginDetailsSuccess.setLocked(false);
						userLoginDetailsSuccess.setSource(deviceType);
						userLoginDetailsBO.saveUserLoginDetails(userLoginDetailsSuccess);
						statusMap.put(CustomerPortalConstants.IS_FIRST_TIME_LOGIN, true);

						// set last log in time

						userDetails.setLastLoginTime(userLoginDetailsSuccess.getLastLogInSuccess());
					} else {
						if (!Boolean.TRUE.equals(userLoginDetails.getPasswordChanged())) {
							statusMap.put(CustomerPortalConstants.IS_FIRST_TIME_LOGIN, true);
						}

						UserLoginDetails previousUserLoginDetails = (UserLoginDetails) SerializationUtils
								.clone(userLoginDetails);
						userDetails.setLastLoginTime(previousUserLoginDetails.getLastLogInSuccess());
						userLoginDetails.setLastLogInSuccess(new Timestamp(new Date().getTime()));
						userLoginDetails.setWrongPasswordAttempts(0);
						userLoginDetails.setLocked(false);
						userLoginDetails.setSource(deviceType);
						userLoginDetailsBO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails,
								userLoginDetails.getId(), user.getUserId());
					}

					/**
					 * save user login tracker
					 */

//					if (isCheckUserLoginDetails) {
						Userlogintracker userLoginTracker = new Userlogintracker();
						userLoginTracker.setUserid(user.getUserId());
						userLoginTracker.setLogintime(new Timestamp(new Date().getTime()));
						if (deviceType.equalsIgnoreCase("Mobile"))
							userLoginTracker.setIsMobile(1);
						else
							userLoginTracker.setIsMobile(0);
						userlogintrackerBO.insertUserlogintracker(userLoginTracker);
//					}
					statusMap.put(CustomerPortalConstants.SUCCESS, new JwtAuthenticationResponse(token));
					// JwtUser authenticatedUser = (JwtUser)
					// userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
					// authenticatedUser.setUserType(userType);
					statusMap.put(CustomerPortalConstants.AUTHENTICATED_USER, userDetails);
					statusMap.put(CustomerPortalConstants.IS_WARE_HOUSE_OPERATOR, isWareHouseOperator);
					return new ResponseEntity<>(statusMap, HttpStatus.OK);
				} catch (Exception exception) {
					logger.error("Exception in log in in USerLoginService", exception);
					if (exception instanceof BadCredentialsException && exception.getMessage().equals(
							messageSource.getMessage(CustomerPortalConstants.BAD_CREDENTIAL, null, currentLocale))) {
						// add the failure part
						if (isCheckUserLoginDetails) {
							if (userLoginDetails != null) {
								UserLoginDetails previousUserLoginDetails = (UserLoginDetails) SerializationUtils
										.clone(userLoginDetails);
								// update user log in details
								Integer wronPasswordCount = userLoginDetails.getWrongPasswordAttempts();
								userLoginDetails.setLastLogInFailure(new Timestamp(new Date().getTime()));
								userLoginDetails.setWrongPasswordAttempts(wronPasswordCount + 1);
								if (userLoginDetails.getWrongPasswordAttempts().equals(passwordMaxWronCount)) {
									userLoginDetails.setLocked(true);
								}
								userLoginDetails.setSource(deviceType);
								userLoginDetailsBO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails,
										userLoginDetails.getId(), user.getUserId());
								// if(userLoginDetailsBO.updateUserLoginDetails(userLoginDetails,previousUserLoginDetails,userLoginDetails.getId(),user.getUserId())==1){
								if (Boolean.TRUE.equals(userLoginDetails.getLocked())) {
									statusMap.put(CustomerPortalConstants.ERROR, messageSource.getMessage(
											CustomerPortalConstants.ACCOUNT_LOCKED_MSG, null, currentLocale));
								} else {
									statusMap.put(CustomerPortalConstants.ERROR, messageSource.getMessage(
											CustomerPortalConstants.WRONG_CREDENTIAL_MSG, null, currentLocale));
								}
								return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
								// }
							} else {
								UserLoginDetails userLoginDetailsToSave = new UserLoginDetails();
								userLoginDetailsToSave.setId(
										sequencesBO.getNextValue(SequenceConstants.USER_LOG_IN_DETAILS_SEQUENCE));
								userLoginDetailsToSave.setUserId(user.getUserId());
								userLoginDetailsToSave.setLastLogInFailure(new Timestamp(new Date().getTime()));
								userLoginDetailsToSave.setWrongPasswordAttempts(FIRST_WRONG_PASSWORD_ATTEMPT_COUNT);
								userLoginDetailsToSave.setLocked(false);
								userLoginDetailsToSave.setSource(deviceType);
								userLoginDetailsBO.saveUserLoginDetails(userLoginDetailsToSave);
								// if(userLoginDetailsBO.saveUserLoginDetails(userLoginDetailsToSave)==1){
								statusMap.put(CustomerPortalConstants.ERROR, messageSource
										.getMessage(CustomerPortalConstants.WRONG_CREDENTIAL_MSG, null, currentLocale));
								return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
								// }
							}
						}
					}
					statusMap.put(CustomerPortalConstants.ERROR,
							messageSource.getMessage(CustomerPortalConstants.LOGIN_ERROR, null, currentLocale));
					return new ResponseEntity<>(statusMap, HttpStatus.OK);
				}
			}
		} else {
			statusMap.put(CustomerPortalConstants.ERROR,
					messageSource.getMessage(CustomerPortalConstants.WRONG_CREDENTIAL_MSG, null, currentLocale));
			return new ResponseEntity<>(statusMap, HttpStatus.UNAUTHORIZED);
		}
	}



}
