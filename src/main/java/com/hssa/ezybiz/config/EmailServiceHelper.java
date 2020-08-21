package com.hssa.ezybiz.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dto.Company;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.Email;
import com.hssa.ezybiz.dto.UserRegistrationRequest;
import com.hssa.ezybiz.services.CompanyBO;
import com.hssa.ezybiz.services.ContactBO;
import com.hssa.ezybiz.services.EmailTemplateBO;
import com.hssa.ezybiz.utils.CustomerPortalConstants;

@Service
public class EmailServiceHelper {
	@Autowired
	private EmailTemplateBO emailTemplateBO;

	@Value(CustomerPortalConstants.REQUEST_MAPPING_CONPANY)
	private String company;

	@Value(CustomerPortalConstants.REQUEST_MAPPING_PORTAL_URL)
	private String portalURL;
	//@Autowired
	//MessageSender messageSender;

	//@Autowired
	//private VelocityEngine velocityEngine;

	@Autowired
	private ContactBO contactBo;

	@Autowired
	private CompanyBO companyBO;

	//@Autowired
	//ShiptopartyBO shiptopartyBO;

	private static final Logger mailLogger = Logger.getLogger(CustomerPortalConstants.LOG_FILE_NAME_MAIL);

	public EmailTemplateBO getEmailTemplateBO() {
		return emailTemplateBO;
	}

	public void setEmailTemplateBO(EmailTemplateBO emailTemplateBO) {
		this.emailTemplateBO = emailTemplateBO;
	}

	public void sendEmailForRegisterUser(UserRegistrationRequest userRegistration, Map<String, String> emailBody,
			Company company, Customer customer) throws Exception {

		/*
		 * EmailTemplate emailTemplate = emailTemplateBO
		 * .findEmailTemplateByTemplateName(CustomerPortalConstants.
		 * EMAIL_TEMPLATE_REGISTER_USER);
		 */
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("loginId", emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID));
		model.put("password", emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD));
		model.put("company", company);
		model.put("customer", customer);
		model.put("userName", userRegistration.getGivenName());

		mailLogger.info("Password for " + emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID) + " is "
				+ emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD));
		//String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/registerUser.vm",
		//		"UTF-8", model);
		/*
		 * if (emailTemplate != null) { mailBody = emailTemplate.getMailBody(); mailBody
		 * = mailBody.replace(CustomerPortalConstants.PLACEHOLDER_LOGINID,
		 * emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID)); mailBody =
		 * mailBody.replace(CustomerPortalConstants.PLACEHOLDER_PASSWORD,
		 * emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD)); mailBody =
		 * mailBody.replace(CustomerPortalConstants.PLACEHOLDER_PORTALURL, portalURL);
		 * mailBody = mailBody.replace(CustomerPortalConstants.PLACEHOLDER_COMPANY_NAME,
		 * company);
		 * 
		 * }
		 */
		// String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		String emailId = userRegistration.getEmail();
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for username :" + userRegistration.getLoginId());
		//email.setContent(mailBody);
		mailLogger.info("UserRegistration-> Subject: " + email.getSubject() + CustomerPortalConstants.EMAIL_SENDING_TO
				+ userRegistration.getEmail());
		//messageSender.sendMessage(email);
		// MailUtil.sendMail(userRegistration.getEmail(), null,
		// emailTemplate.getSubject(), mailBody);
		mailLogger.info("UserRegistration-> Subject: " + email.getSubject() + CustomerPortalConstants.EMAIL_SENT_TO
				+ userRegistration.getEmail());

	}

	/**
	 * @param customer
	 * @param company2
	 * @param loginId
	 * @param password
	 * @throws Exception
	 */
	public void sendEmailforForgotPassword(String userEmaiid, Map<String, String> emailBody, Company company,
			Customer customer) throws Exception {
		/*
		 * EmailTemplate emailTemplate = emailTemplateBO
		 * .findEmailTemplateByTemplateName(CustomerPortalConstants.
		 * EMAIL_TEMPLATE_FORGOT_PASSWORD);
		 */
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("loginId", emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID));
		model.put("password", emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD));
		model.put("company", company);
		model.put("customer", customer);
	//	String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/forgotPassword.vm",
	//			"UTF-8", model);
		/*
		 * if (emailTemplate != null) { mailBody = emailTemplate.getMailBody(); mailBody
		 * = mailBody.replace(CustomerPortalConstants.PLACEHOLDER_LOGINID,
		 * emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID)); mailBody =
		 * mailBody.replace(CustomerPortalConstants.PLACEHOLDER_PASSWORD,
		 * emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD)); mailBody =
		 * mailBody.replace(CustomerPortalConstants.PLACEHOLDER_PORTALURL,
		 * "https://localhost:3000"); }
		 */
		// String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		Email email = new Email();
		// email.setFrom(contactBo.findContactByID(company.getMaincontactid()).getEmail());
		email.setTo(userEmaiid);
		email.setSubject("Forgot password");
	//	email.setContent(mailBody);
		//messageSender.sendMessage(email);
		// MailUtil.sendMail(userEmaiid, null, emailTemplate.getSubject(), mailBody);

	}

	public void sendEmailforForgotPasswordToWareHouseOperator(String userEmailId, Map<String, String> emailBody,
			Company company) throws Exception {
		/*
		 * EmailTemplate emailTemplate = emailTemplateBO
		 * .findEmailTemplateByTemplateName(CustomerPortalConstants.
		 * EMAIL_TEMPLATE_FORGOT_PASSWORD);
		 */
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("loginId", emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID));
		model.put("password", emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD));
		model.put("company", company);
	//	String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
	//			"velocity/forgotPasswordForEmployee.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(userEmailId);
		email.setSubject("Forgot password");
		//email.setContent(mailBody);
		//messageSender.sendMessage(email);

	}

	/**
	 * 
	 * send mail for place order
	 * 
	 * @author deepaks26
	 * @param customer
	 * @param orderPartner
	 * @param userDto
	 * @param company2
	 * @param orderEmailDtos
	 */
/*	@Async
	@Transactional
	public void sendMailForPlaceOrder(Customer customer, OrderPartner orderPartner, Company company, UserDto userDto,
			List<OrderEmailDto> orderEmailDtos) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		String salesPromoterEmailId = null;
		try {
			salesPromoterEmailId = contactBo.getEmailIdByCustomerId(customer.getSalesPromoterId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(emailId);
		sb.append(",");
		if (salesPromoterEmailId != null) {
			sb.append(salesPromoterEmailId);
		}
		model.put("customer", customer);
		model.put("shiptoparty", orderPartner);
		model.put("orders", orderEmailDtos);
		model.put("company", company);
		model.put("date", new DateTool());
		model.put("user", userDto);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/orderPlaced.vm",
				"UTF-8", model);
		Email email = new Email();
		if (salesPromoterEmailId != null) {
			email.setTo(sb.toString());
		} else {
			email.setTo(emailId);
		}
		email.setSubject("Order successfully created for account :" + customer.getCode());
		email.setContent(mailBody);
		messageSender.sendMessage(email);

	}

	@Async
	@Transactional
	public void sendMailForPlaceOrderIsDirectPosting(Customer customer, OrderPartner orderPartner, Company company,
			UserDto userDto, OrderEmailDto orderEmailDto) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
			String salesPromoterEmailId = null;
			try {
				salesPromoterEmailId = contactBo.getEmailIdByCustomerId(customer.getSalesPromoterId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			StringBuffer sb = new StringBuffer();
			sb.append(emailId);
			sb.append(",");
			sb.append(salesPromoterEmailId);
			model.put("customer", customer);
			model.put("shiptoparty", orderPartner);
			model.put("order", orderEmailDto);
			model.put("company", company);
			model.put("date", new DateTool());
			model.put("user", userDto);
			String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
					"velocity/orderDirectPosted.vm", "UTF-8", model);
			Email email = new Email();
			if (salesPromoterEmailId != null) {
				email.setTo(sb.toString());
			} else {
				email.setTo(emailId);
			}
			email.setCc(userDto.getEmailId());
			email.setSubject("Web order no :" + orderEmailDto.getWebOrderNumber() + " acknowledged.");
			email.setContent(mailBody);
			messageSender.sendMessage(email);
		} catch (Exception ex) {
			mailLogger.error("Error occured while sending mail for autoposting ==>" + ex.getMessage(), ex);
		}

	}

	public void sendCustomerAccessKey(String pw, Customer customer, Company company) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		model.put("customer", customer);
		model.put("accessKey", pw);
		model.put("company", company);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/sendAccessKey.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for code :" + customer.getCode());
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	public void sendCustomerUpload(Customer customer, Company company, String pw) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		model.put("customer", customer);
		model.put("accessKey", pw);
		model.put("company", company);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/customerUpload.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for code :" + customer.getCode());
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	public void setMailToOfficerForCustomerUpload(Customer customer, Company company, String emailId, String pw) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("customer", customer);
		model.put("accessKey", pw);
		model.put("company", company);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/customerUploadNotifToOfficer.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for code :" + customer.getCode());
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	public void sendEmailforCustomerResetPassword(Map<String, Object> emailBody) {
		
		 * EmailTemplate emailTemplate = emailTemplateBO
		 * .findEmailTemplateByTemplateName(CustomerPortalConstants.
		 * EMAIL_TEMPLATE_FORGOT_PASSWORD);
		 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("loginId", emailBody.get(CustomerPortalConstants.PLACEHOLDER_LOGINID).toString());
		model.put("password", emailBody.get(CustomerPortalConstants.PLACEHOLDER_PASSWORD).toString());
		model.put("company", (Company) emailBody.get("company"));
		model.put("customerName", emailBody.get("customerName").toString());
		model.put("code", emailBody.get("code").toString());

		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/customerPasswordReset.vm", "UTF-8", model);

		Email email = new Email();
		email.setTo(emailBody.get("cemail").toString());
		email.setSubject("Reset Password");
		email.setContent(mailBody);
		messageSender.sendMessage(email);

	}

	@Async
	@Transactional
	public void sendMailForRejectOreder(PendingOrder pendingOrder, Integer companyId) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			String toEmailId = contactBo.getEmailIdByCustomerId(pendingOrder.getCustomerId());
			Company company = companyBO.findCompanyByID(companyId);
			String cityName = shiptopartyBO.getCityNameByPartnerNumber(pendingOrder.getPartnernumber());
			model.put("order", pendingOrder);
			model.put("company", company);
			model.put("cityName", cityName);
			String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/rejectOrder.vm",
					"UTF-8", model);
			Email email = new Email();
			email.setTo(toEmailId);
			email.setSubject("Order rejected for account :" + pendingOrder.getSoldTo());
			email.setContent(mailBody);
			messageSender.sendMessage(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendEmployeeUploadMail(Employee employee, Company company, User user) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByUserId(employee.getId());
		model.put("employee", employee);
		model.put("company", company);
		model.put("user", user);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/employeeUpload.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for :" + user.getGivenName());
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	@Async
	@Transactional
	public void sendShipToPartyRequestMail(Customer customer, Employee employee, Company company) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByUserId(employee.getId());
		model.put("employee", employee);
		model.put("company", company);
		model.put("customer", customer);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/shipToPartyRequest.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("ShipToParty Request");
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	@Async
	@Transactional
	public void sendShipToPartyRequestApprovalMail(Customer customer, ShipToPartyRequest stpRequest, Company company) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		System.out.println("Sending Email to " + emailId);
		model.put("shipToParty", stpRequest);
		model.put("company", company);
		model.put("customer", customer);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/shipToPartyRequestCompleted.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("ShipToParty Request Completed");
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	@Async
	@Transactional
	public void sendShipToPartyRequestRejectionMail(Customer customer, ShipToPartyRequest stpRequest, Company company,
			String rejectionReason) {
		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByCustomerId(customer.getId());
		System.out.println("Sending Email to " + emailId);
		model.put("shipToParty", stpRequest);
		model.put("company", company);
		model.put("customer", customer);
		model.put("resonToReject", rejectionReason);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/shipToPartyRequestRejected.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("ShipToParty request has been Rejected");
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	@Async
	@Transactional
	public void sendWarehouseUploadMail(Employee employee, Company company, User user) {

		Map<String, Object> model = new HashMap<String, Object>();
		String emailId = contactBo.getEmailIdByUserId(employee.getId());
		model.put("employee", employee);
		model.put("company", company);
		model.put("user", user);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/wareHouseUpload.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(emailId);
		email.setSubject("Portal access notification for :" + user.getGivenName());
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	public void sendEmailForRegisterUserOTP(TempUserRegister request, String OTP) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", request.getId());
		model.put("OTP", OTP);
		model.put("companyName", request.getCompanyName());
		model.put("name", request.getGivenName());

		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/registerUserOTP.vm",
				"UTF-8", model);
		Email email = new Email();
		email.setTo(request.getEmail());
		email.setSubject("OTP for User Registeration:" + OTP);
		email.setContent(mailBody);
		messageSender.sendMessage(email);
	}

	public void sendEmailForRegisterUserNotification(User user, TempUserRegister record) {
		if(user.getEmail()==null ||user.getEmail().equals(""))
			return;
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("user", user);

		model.put("record", record);

		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/registerUserCustomerNotification.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(user.getEmail());
		email.setSubject("User Registeration Notification");
		email.setContent(mailBody);
		messageSender.sendMessage(email);

	}

	public void sendRequestRejectionMail(TempUserRegister userRegistrationRequest) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("rejectBy", userRegistrationRequest.getModifier());
		model.put("rejectComment", userRegistrationRequest.getApproversComments());
		model.put("requestNumber", userRegistrationRequest.getId());
		model.put("companyName", userRegistrationRequest.getCompanyName());





		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/userRegisterationRejection.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(userRegistrationRequest.getEmail());
		email.setSubject("User Registeration Request "+userRegistrationRequest.getId()+" Is Rejected");
		email.setContent(mailBody);
		messageSender.sendMessage(email);		
	}

	public void sendEmailForContactUsDetailsNotification(User user, ContactUsDetails contactUsDetails) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("contactUsDetails",contactUsDetails);
		



		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/contactusDetailsNotification.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(user.getEmail());
		email.setSubject("Contact Us Notification");
		email.setContent(mailBody);
		messageSender.sendMessage(email);		
	}

	public void sendEmailTOUserOfContactUsRequestRegister(ContactUsDetails contactUsDetails) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("contactUsDetails",contactUsDetails);
		



		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocity/emailTOUserOfContactUsRequestRegister.vm", "UTF-8", model);
		Email email = new Email();
		email.setTo(contactUsDetails.getEmail());
		email.setSubject("Contact Us Notification");
		email.setContent(mailBody);
		messageSender.sendMessage(email);			
	}*/
}
