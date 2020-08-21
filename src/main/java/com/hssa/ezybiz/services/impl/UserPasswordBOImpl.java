/**
 * 
 */
package com.hssa.ezybiz.services.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.config.EmailServiceHelper;
import com.hssa.ezybiz.dao.UserPasswordDAO;
import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Contact;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserLoginDetails;
import com.hssa.ezybiz.dto.UserPassword;
import com.hssa.ezybiz.services.CompanyBO;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.CustomerBO;
import com.hssa.ezybiz.services.EmployeeBO;
import com.hssa.ezybiz.services.RoleBO;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.services.UserBO;
import com.hssa.ezybiz.services.UserLoginDetailsBO;
import com.hssa.ezybiz.services.UserPasswordBO;
import com.hssa.ezybiz.utils.Credential;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.PasswordGeneratorUtil;

/**
 * @author KPIT changePassword
 * 
 */
@Service
public class UserPasswordBOImpl implements UserPasswordBO {

	private static final String UserPasswordSequence = "UserPasswordSequence";

	private static final Logger logger = Logger.getLogger("maillogger");
	private static final String ROLE_EMPLOYEE = "Employee";
	private static final String ROLE_ADMIN = "Admin";

	private UserPasswordDAO userPasswordDAO;
	private UserBO userBO;

	private EmailServiceHelper emailServiceHelper;

	@Autowired
	private SequencesBO sequencesBO;

	@Autowired
	UserLoginDetailsBO userLoginDetailsBO;

	//@Autowired
	//SMSServiceHelper sMSServiceHelper;

	@Autowired
	private ContactBO contactBo;

	@Autowired
	private CompanyBO companyBO;

	@Autowired
	private RoleBO roleBO;

	@Autowired
	private CustomerBO customerBO;

	@Autowired
	private EmployeeBO employeeBO;

	public SequencesBO getSequencesBO() {
		return sequencesBO;
	}

	public EmailServiceHelper getEmailServiceHelper() {
		return emailServiceHelper;
	}

	public void setEmailServiceHelper(EmailServiceHelper emailServiceHelper) {
		this.emailServiceHelper = emailServiceHelper;
	}

	public void setSequencesBO(SequencesBO sequencesBO) {
		this.sequencesBO = sequencesBO;
	}

	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

	/**
	 * @return the userPasswordDAO
	 */
	public UserPasswordDAO getUserPasswordDAO() {
		return userPasswordDAO;
	}

	/**
	 * @param userPasswordDAO
	 *            the userPasswordDAO to set
	 */
	public void setUserPasswordDAO(UserPasswordDAO userPasswordDAO) {
		this.userPasswordDAO = userPasswordDAO;
	}

	@Override
	public void addUserPassword(ServerSession serverSession, UserPassword userPasswordVO) {
		userPasswordDAO.insert(userPasswordVO, userPasswordVO.getUserPassswordId(), serverSession.getUserID());

	}

	@Override
	public List<UserPassword> getUserPasswords(ServerSession serverSession) {

		return userPasswordDAO.findAll(serverSession);
	}

	@Override
	public UserPassword getUserPasswordByUserId(int userId) {

		return userPasswordDAO.findUserPasswordByUserId(userId);
	}

	@Override
	public void delete(ServerSession serverSession, UserPassword userPasswordVO) {

		userPasswordDAO.delete(serverSession, userPasswordVO);

	}

	@Override
	public void update(ServerSession serverSession, UserPassword userPasswordVO) {

		userPasswordDAO.update(serverSession, userPasswordVO);

	}

	@Override
	public int getNextId(ServerSession serverSession) {

		return (userPasswordDAO.getMaxId(serverSession) + 1);
	}

	@Override
	public boolean changeUserPassword(UserPassword passwordVo) {
		passwordVo.setUpdatedTime(new Timestamp((new java.util.Date()).getTime()));
		return userPasswordDAO.changeUserPassword(passwordVo);

	}

	public void insert(UserPassword passwordVo, int userId) {
		passwordVo.setUserPassswordId(sequencesBO.getNextValue(UserPasswordSequence));
		passwordVo.setCreatedBy(passwordVo.getUserId());
		passwordVo.setCreatedTime(new Timestamp((new java.util.Date()).getTime()));
		passwordVo.setUpdatedBy(passwordVo.getUserId());
		passwordVo.setUpdatedTime(new Timestamp((new java.util.Date()).getTime()));
		userPasswordDAO.insert(passwordVo, passwordVo.getUserPassswordId(), userId);

	}

	@Override
	public int getPasswordAttempts(String param) {
		return userPasswordDAO.getPasswordAttempts(param);
	}

	@Override
	public void updateAttempts(User frmUser, int userId) {
		User oldUser = userBO.findUserByID(frmUser.getUserId());
		userPasswordDAO.updateAttempts(oldUser, frmUser, frmUser.getUserId(), userId);

	}

	@Override
	public void updateAttemptsToZero(User frmUser, int userId) {
		User oldUser = userBO.findUserByID(frmUser.getUserId());
		userPasswordDAO.updateAttemptsToZero(oldUser, frmUser, frmUser.getUserId(), userId);

	}

	/**
	 * changePassword
	 * 
	 */
	@Override
	public Map<String, String> changePassword(UserPassword userPasswordVO) {
		Map<String, String> statusMap = new HashMap<String, String>();
		User loginUser = userBO.findUserByID(userPasswordVO.getUserId());
		if (loginUser == null) {
			statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.USER_NOT_FOUND);
		} else {
			UserPassword userPassVO = getUserPasswordByUserId(userPasswordVO.getUserId());
			if (userPassVO == null
					|| !validatePassword(userPassVO, loginUser.getLoginId(), userPasswordVO.getOldPassword())) {
				statusMap.put(CustomerPortalConstants.WRONG_OLD_PASSWORD,
						CustomerPortalConstants.OLD_PASSWORD_IS_NOT_FOUND_MSG);
			} else if (!checkPasswordHistoryMaintainance(userPasswordVO.getNewPassword(), loginUser.getUserId())) {
				statusMap.put(CustomerPortalConstants.PASSWORD_HISTORY_ERROR,
						CustomerPortalConstants.PASSWORD_HISTORY_ERROR_MSG);
			} else {
				try {
					/**
					 * 
					 * delete the previous password
					 */
					userPasswordDAO.removePreviousUserPassword(loginUser.getUserId());

					UserPassword userPassword = new UserPassword();
					userPassword.setUserId(loginUser.getUserId());
					userPassword.setUserName(loginUser.getLoginId());
					userPassword.setStatus(true);
					userPasswordVO.setUserPassword(Credential.SHA1.digest(Long.toString(System.currentTimeMillis()),
							userPasswordVO.getNewPassword()));
					userPasswordVO.setStatus(true);
					insert(userPasswordVO, loginUser.getUserId());
					statusMap.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.PASSWORD_CHANGED_SUCCESS);

					/**
					 * 
					 * update user log in details
					 */
					UserLoginDetails userLoginDetails = userLoginDetailsBO
							.getUserLoginDetailsByUserId(loginUser.getUserId());
					UserLoginDetails previousUserLoginDetails = (UserLoginDetails) SerializationUtils
							.clone(userLoginDetails);
					userLoginDetails.setPasswordChanged(true);
					userLoginDetailsBO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails,
							userLoginDetails.getId(), loginUser.getUserId());
					/*
					 * else{ statusMap.put(CustomerPortalConstants.ERROR,
					 * CustomerPortalConstants.PASSWORD_CHANGED_ERROR); }
					 */
				} catch (Exception e) {
					statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.PASSWORD_CHANGED_ERROR);
				}
			}
		}
		return statusMap;
	}

	private boolean checkPasswordHistoryMaintainance(String userEnteredPassword, Integer userId) {
		List<String> lastFivePassword = userPasswordDAO.getLastFivePasswordByUserId(userId);
		return (lastFivePassword.stream().filter(
				password -> (Credential.SHA1.digest(Long.toString(System.currentTimeMillis()), userEnteredPassword))
						.equals(password))
				.findAny().orElse(null)) == null;
	}

	private boolean validatePassword(UserPassword userVO, String userName, String oldPassword) {
		/*
		 * JCOEPasswordEncoder encoder = null; boolean passwordMatched = false;
		 * 
		 * try { encoder = JCOEPasswordEncoder.getEncoder(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		final Credential credential_ = Credential.getCredential(userVO.getUserPassword());
		return credential_.check(oldPassword);
	}

	public String cancelPasswordChange() {
		return "/Default";
	}

	/**
	 * deepak
	 */
	@Override
	public Map<String, Object> forgotPassword(String emailId, String mobileNumber) {

		/**
		 * check for role
		 * 
		 */
		Map<String, Object> statusMap = new HashMap<>();
		logger.info("Entering into forgotPassword in UserPasswordBOImpl ==> emailId=" + emailId);
		User currentUser = null;
		Company company = null;
		emailId = emailId + "";
		mobileNumber = mobileNumber + "";
		if (!emailId.equals("null"))
			currentUser = userBO.getUserByEmailId(null, emailId);
		else if (!mobileNumber.equals("null"))
			currentUser = userBO.getUserByMobile(mobileNumber);
		if (currentUser != null) {
			company = companyBO.findCompanyByID(currentUser.getCompanyId());
			Customer customer = customerBO.findCustomerByUserId(currentUser.getUserId());
			if (customer.getAccessPortal() == 0 || currentUser.getUserStatus() == 0) {
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.FORGOT_PASSWORD_INACTIVE);
				return statusMap;
			}
			boolean isWareHouseOperaotr = false;

			/**
			 * check for role
			 * 
			 */
			List<String> roleNames = roleBO.getAllRoleTypeByUserId(currentUser.getUserId());
			if (roleNames.stream().filter(role -> ROLE_EMPLOYEE.equals(role)).findAny().orElse(null) != null
					&& Boolean.TRUE.equals(employeeBO.checkForWareHouseOperator(currentUser.getUserId()))) {
				isWareHouseOperaotr = true;
			}

			if (!isWareHouseOperaotr && ((roleNames.stream().filter(role -> ROLE_EMPLOYEE.equals(role)).findAny()
					.orElse(null)) != null
					|| (roleNames.stream().filter(role -> ROLE_ADMIN.equals(role)).findAny().orElse(null)) != null)) {
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.FORGOT_PASSWORD_NOT_PRVILIEGED);
				return statusMap;
			}

			/**
			 * 
			 * set the previous password deleted = true
			 */
			userPasswordDAO.removePreviousUserPassword(currentUser.getUserId());
			// String sysGeneratedPass=PasswordGeneratorUtil.passwordGenerator();
			String sysGeneratedPass = PasswordGeneratorUtil.newPasswordGenerator();
			logger.info("New password " + sysGeneratedPass);
			System.out.println("New password " + sysGeneratedPass);
			UserPassword userPassword = new UserPassword();
			userPassword.setUserId(currentUser.getUserId());
			userPassword.setUserName(currentUser.getLoginId());
			userPassword.setStatus(true);
			try {
				String encyptpw = Credential.SHA1.digest(Long.toString(System.currentTimeMillis()), sysGeneratedPass);
				userPassword.setUserPassword(encyptpw);
				insert(userPassword, currentUser.getUserId());
				/**
				 * send mail
				 */
				Map<String, String> emailBody = new HashMap<String, String>();
				emailBody.put(CustomerPortalConstants.PLACEHOLDER_LOGINID, currentUser.getLoginId());
				emailBody.put(CustomerPortalConstants.PLACEHOLDER_PASSWORD, sysGeneratedPass);
				if (userLoginDetailsBO.getUserLoginDetailsCountByUserId(currentUser.getUserId()) != 0) {
					UserLoginDetails userLoginDetails = userLoginDetailsBO
							.getUserLoginDetailsByUserId(currentUser.getUserId());
					UserLoginDetails previousUserLoginDetails = (UserLoginDetails) SerializationUtils
							.clone(userLoginDetails);
					userLoginDetails.setPasswordChanged(false);
					userLoginDetailsBO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails,
							userLoginDetails.getId(), currentUser.getUserId());
				}

				if (!isWareHouseOperaotr) {
					emailServiceHelper.sendEmailforForgotPassword(currentUser.getUserEmailId(), emailBody, company, customer);
				} else {
					// send mail to employee
					emailServiceHelper.sendEmailforForgotPasswordToWareHouseOperator(currentUser.getUserEmailId(), emailBody,
							company);
				}

				/**
				 * SMS text
				 */
				StringBuffer sb = new StringBuffer();
				sb.append("Your account has been activated to access Websales.Login with following");
				sb.append(System.getProperty("line.separator"));
				sb.append(" Login: " + currentUser.getLoginId());
				sb.append(System.getProperty("line.separator"));
				sb.append("Password: " + sysGeneratedPass);
				sb.append(System.getProperty("line.separator"));
				sb.append("URL: " + companyBO.getPortalURLByCompanyId(currentUser.getCompanyId()));
				//sMSServiceHelper.sendSMS(contactBo.getPhoneNumberByUserId(currentUser.getUserId()), sb.toString(),
				//		company.getId());

				/**
				 * 
				 * send SMS to customer also
				 */
				if (!isWareHouseOperaotr) {
					List<Map<String, Object>> custDetails = customerBO
							.getCustomerDetailsByUserId(currentUser.getUserId());
					if (!custDetails.isEmpty() && custDetails.get(0).get("mobile") != null) {
						StringBuffer cusSb = new StringBuffer();
						cusSb.append("Dear Customer " + custDetails.get(0).get("customerName").toString() + " - "
								+ custDetails.get(0).get("code").toString());
						cusSb.append(System.getProperty("line.separator"));
						cusSb.append(
								"your login is " + currentUser.getLoginId() + " and password is " + sysGeneratedPass);
						//sMSServiceHelper.sendSMS(custDetails.get(0).get("mobile").toString(), cusSb.toString(),
								//currentUser.getCompanyId());
					}
				}

				statusMap.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.FORGOT_PASSWORD_SUCCESS);

			} catch (Exception e) {
				logger.error("Exception in forgotPassword in UserPasswordBOImpl==>" + e.getMessage(), e);
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.FORGOT_PASSWORD_FAILED);
			}
		} else {
			if (!emailId.equals("null"))
				statusMap.put("INVALID_EMAIL", CustomerPortalConstants.EMAIL_NOT_FOUND);
			else
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.MOBILE_NOT_FOUND);
		}
		return statusMap;
	}

	@Override
	public void deletePassByUserId(ServerSession serverSession, int userId) {
		userPasswordDAO.deletePassByUserId(serverSession, userId);

	}

	@Override
	public int getUserPasswordCountByUserId(int userId) {
		return userPasswordDAO.getUserPasswordCountByUserId(userId);
	}

	@Override
	public Map<String, Object> resetPassword(int userId) {

		Map<String, Object> statusMap = new HashMap<>();
		User currentUser = userBO.findUserByID(userId);
		if (currentUser != null) {

			try {
				/**
				 * 
				 * set the previous password deleted = true
				 */
				userPasswordDAO.removePreviousUserPassword(currentUser.getUserId());
				// String sysGeneratedPass=PasswordGeneratorUtil.passwordGenerator();
				String sysGeneratedPass = PasswordGeneratorUtil.newPasswordGenerator();
				UserPassword userPassword = new UserPassword();
				userPassword.setUserId(currentUser.getUserId());
				userPassword.setUserName(currentUser.getLoginId());
				userPassword.setStatus(true);

				String encyptpw = Credential.SHA1.digest(Long.toString(System.currentTimeMillis()), sysGeneratedPass);
				userPassword.setUserPassword(encyptpw);
				insert(userPassword, currentUser.getUserId());

				if (userLoginDetailsBO.getUserLoginDetailsCountByUserId(currentUser.getUserId()) != 0) {
					UserLoginDetails userLoginDetails = userLoginDetailsBO
							.getUserLoginDetailsByUserId(currentUser.getUserId());
					UserLoginDetails previousUserLoginDetails = (UserLoginDetails) SerializationUtils
							.clone(userLoginDetails);
					userLoginDetails.setPasswordChanged(false);
					userLoginDetailsBO.updateUserLoginDetails(userLoginDetails, previousUserLoginDetails,
							userLoginDetails.getId(), currentUser.getUserId());
				}

				/**
				 * change after SMS and email format change
				 */
				/**
				 * send mail
				 */
				Map<String, Object> emailBody = new HashMap<String, Object>();

				// List<Map<String, Object>> custDetails =
				// shiptopartyBO.getCustomerDetailsForShipToParty(customerId);
				Contact co = contactBo.findContactByID(currentUser.getMainContactId());
				List<Map<String, Object>> custDetails = customerBO.getCustomerDetailsByUserId(userId);
				emailBody = custDetails.get(0);
				emailBody.put("cemail", co.getEmail());
				emailBody.put("company", companyBO.findCompanyByID(currentUser.getCompanyId()));
				emailBody.put(CustomerPortalConstants.PLACEHOLDER_LOGINID, currentUser.getLoginId());
				emailBody.put(CustomerPortalConstants.PLACEHOLDER_PASSWORD, sysGeneratedPass);
				// emailServiceHelper.sendEmailforForgotPassword(currentUser.getUserEmailId(),
				// emailBody);
				//emailServiceHelper.sendEmailforCustomerResetPassword(emailBody);

				/**
				 * SMS text
				 */
				String companuURL = companyBO.getPortalURLByCompanyId(currentUser.getCompanyId());

				/*
				 * StringBuffer sb = new StringBuffer(); sb.
				 * append("Your account has been activated to access Websales.Login with following"
				 * ); sb.append(System.getProperty("line.separator"));
				 * sb.append("Login: "+currentUser.getLoginId());
				 * sb.append(System.getProperty("line.separator")); sb.append("Password: "+
				 * sysGeneratedPass); sb.append(System.getProperty("line.separator"));
				 * sb.append("URL: "+companuURL);
				 * sMSServiceHelper.sendSMS(contactBo.getPhoneNumberByUserId(currentUser.
				 * getUserId()), sb.toString(),currentUser.getCompanyId());
				 */

				/**
				 * send SMS to customer also
				 * 
				 */
				StringBuffer cusSb = new StringBuffer();
				cusSb.append("Dear Customer " + emailBody.get("customerName").toString() + " - "
						+ emailBody.get("code").toString());
				cusSb.append(System.getProperty("line.separator"));
				cusSb.append("your password has been successfully changed as");
				cusSb.append(System.getProperty("line.separator"));
				cusSb.append("Password: " + sysGeneratedPass);
				cusSb.append(System.getProperty("line.separator"));
				cusSb.append(" for your login id " + currentUser.getLoginId());
				// sMSServiceHelper.sendSMS(emailBody.get("mobile").toString(),
				// cusSb.toString(),currentUser.getCompanyId());
				//sMSServiceHelper.sendSMS(co.getMobile(), cusSb.toString(), currentUser.getCompanyId());
				statusMap.put(CustomerPortalConstants.SUCCESS, CustomerPortalConstants.FORGOT_PASSWORD_SUCCESS);

			} catch (Exception e) {
				logger.error("Exception in resetPassword", e);
				statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.FORGOT_PASSWORD_FAILED);
			}
		} else {
			statusMap.put(CustomerPortalConstants.ERROR, CustomerPortalConstants.EMAIL_NOT_FOUND);
		}
		return statusMap;
	}

}
