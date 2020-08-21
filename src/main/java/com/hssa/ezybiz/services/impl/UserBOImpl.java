package com.hssa.ezybiz.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.dao.UserDAO;
import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.TempUserRegister;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserDto;
import com.hssa.ezybiz.dto.UserPassword;
import com.hssa.ezybiz.services.SequencesBO;
import com.hssa.ezybiz.services.UserPasswordBO;
import com.hssa.ezybiz.services.UserBO;
import com.hssa.ezybiz.utils.Credential;
import com.hssa.ezybiz.utils.CustomerPortalConstants;
import com.hssa.ezybiz.utils.PasswordGeneratorUtil;



@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserBOImpl implements UserBO {

	private static final Logger userLogger = Logger.getLogger("UserBOImpl");

	private transient UserDAO userDAO;

	private transient SequencesBO sequencesBO;

	private transient UserPasswordBO userPasswordBO;

	public UserBOImpl() {
		super();
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public SequencesBO getSequencesBO() {
		return sequencesBO;
	}

	public void setSequencesBO(SequencesBO sequencesBO) {
		this.sequencesBO = sequencesBO;
	}

	public UserPasswordBO getUserPasswordBO() {
		return userPasswordBO;
	}

	public void setUserPasswordBO(UserPasswordBO userPasswordBO) {
		this.userPasswordBO = userPasswordBO;
	}

	public List<User> search(User user) {
		return userDAO.search(user);
	}

	public List<User> getAllUserList(ServerSession serverSession) {
		return userDAO.getAll(null);
	}

	public int deleteUser(ServerSession serverSession, User user) {
		userPasswordBO.deletePassByUserId(serverSession, user.getUserId());
		return userDAO.deleteUser(serverSession, user);
	}

	public int updateUser(User user, int userId) {
		User oldUser = findUserByID(user.getUserId());
		user.setUpdatedBy(userId);
		user.setUpdatedTime(new Timestamp((new java.util.Date()).getTime()));
		return userDAO.updateUser(oldUser, user, user.getUserId(), userId);
	}

	public User findUserByID(int userId) {
		return userDAO.findUserByID(userId);
	}

	public int insertUser(User user) throws Exception {

		int sequenceForUserId = user.getUserId();
		if (sequenceForUserId == 0) {
			sequenceForUserId = sequencesBO.getNextValue(CustomerPortalConstants.SEQUENCE_USERID);
		}
		user.setUserStatus(CustomerPortalConstants.NEW_USER);
		user.setUserId(sequenceForUserId);
		user.setCreatedBy(user.getUserId());
		user.setCreatedTime(new Timestamp((new java.util.Date()).getTime()));
		user.setUpdatedBy(user.getCreatedBy());
		user.setUpdatedTime(user.getCreatedTime());
		user.setLastLoginTime(new Timestamp((new java.util.Date()).getTime()));
		user.setEntityUid(UUID.randomUUID().toString());
		user.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
		int insertSuccess = userDAO.insertUser(user, sequenceForUserId, user.getUserId());

		UserPassword passwordVo = new UserPassword();
		passwordVo.setStatus(true);
		passwordVo.setUserId(user.getUserId());
		passwordVo.setUserName(user.getLoginId());
		// String sysGeneratedPass = PasswordGeneratorUtil.passwordGenerator();
		String sysGeneratedPass = /*user.getLoginId();*/ PasswordGeneratorUtil.newPasswordGenerator();
		passwordVo.setUserPassword(sysGeneratedPass);

		user.setPassword(sysGeneratedPass);

		String encyptpw = Credential.SHA1.digest(Long.toString(System.currentTimeMillis()), sysGeneratedPass);
		passwordVo.setUserPassword(encyptpw);

		int sequenceForUserPasswordId = getSequencesBO().getNextValue(CustomerPortalConstants.SEQUENCE_USERID);
		passwordVo.setUserPassswordId(sequenceForUserPasswordId);
		userPasswordBO.insert(passwordVo, sequenceForUserPasswordId);
		return insertSuccess;

	}

	public User getUserByName(ServerSession serverSession, String userLoginName) {
		return userDAO.getUserByName(serverSession, userLoginName);
	}

	@Override
	public int deletSecurityQuest(ServerSession serverSession, User user) {
		return userDAO.deletSecurityQuest(serverSession, user);
	}

	@Override
	public int deleteUserAndAlldata(ServerSession serverSession, User userVO) {

		userPasswordBO.deletePassByUserId(serverSession, userVO.getUserId());
		return deleteUser(serverSession, userVO);

	}

	@Override
	public List<User> getUserByEmail(ServerSession attributeValueAsObject, User user) {
		return userDAO.getUserByEmail(attributeValueAsObject, user);
	}

	@Override
	public User getUserByEmailID(ServerSession attributeValueAsObject, User user) {
		return userDAO.getUserByEmailID(attributeValueAsObject, user);
	}

	@Override
	public User getUserByEmailIdAndCompanyId(String emailId, int companyId)
			throws IncorrectResultSizeDataAccessException {
		return userDAO.getUserByEmailIdAndCompanyId(emailId, companyId);
	}

	@Override
	public User getUserOnlyByEmailId(String emailId)
			throws IncorrectResultSizeDataAccessException {
		return userDAO.getUserOnlyByEmailId(emailId);
	}
	@Override
	public User getUserByMobileAndCompanyId(String mobileNumber, int companyId)
			throws IncorrectResultSizeDataAccessException {
		return userDAO.getUserByMobileAndCompanyId(mobileNumber, companyId);
	}

	@Override
	public int insertUserForEmployee(User user, int updatedUserID) {
		int sequenceForUserId = sequencesBO.getNextValue(CustomerPortalConstants.SEQUENCE_USERID);
		user.setUserStatus(CustomerPortalConstants.NEW_USER);
		user.setUserId(sequenceForUserId);
		user.setCreatedBy(updatedUserID);
		user.setCreatedTime(new Timestamp((new java.util.Date()).getTime()));
		user.setUpdatedBy(user.getCreatedBy());
		user.setUpdatedTime(user.getCreatedTime());
		user.setLastLoginTime(new Timestamp((new java.util.Date()).getTime()));
		user.setEntityUid(UUID.randomUUID().toString());
		user.setActivationDate(new Timestamp((new java.util.Date()).getTime()));
		return userDAO.insertUser(user, sequenceForUserId, updatedUserID);
	}

	/**
	 * 
	 * getAllFilteredUser
	 */
	@Override
	public List<UserDto> getAllFilteredUser(UserDto userDto, Integer companyId) {
		return userDAO.getAllFilteredUser(userDto, companyId);
	}

	@Override
	public UserDto findUserDtoByID(Integer loggedInUserId) {
		return userDAO.findUserDtoByID(loggedInUserId);
	}

	@Override
	public String getUserTypeByUserId(Integer userId) {
		return userDAO.getUserTypeByUserId(userId);
	}

	@Override
	public List<Map<String, Object>> findUserSalesRegionAndSalesOffice(Integer userId) {
		return userDAO.findUserSalesRegionAndSalesOffice(userId);
	}

	@Override
	public List<Map<String, Object>> findEmployeeSalesRegionAndSalesOffice(Integer userId) {
		return userDAO.findEmployeeSalesRegionAndSalesOffice(userId);
	}

	@Override
	public List<User> getOwnerUsersByCustomerCode(String customerCode) {
		return userDAO.getOwnerUsersByCustomerCode(customerCode);
	}

	@Override
	public User getUserByEmailId(Object object, String email) {
		// get user base on google's provided email id
		return userDAO.getUserByEmailID(object, email);
	}

	@Override
	public User getUserByEmailId(String email) {
		return userDAO.getUserByEmailID(email);
	}

	@Override
	public User getUserByMobile(String mobileNumber) {
		// TODO Auto-generated method stub
		return userDAO.getUserByMobile(mobileNumber);
	}

	@Override
	public User getUserByLoginId(String loginId) {
		// TODO Auto-generated method stub
		List<User> userList = userDAO.getUserByLoginId(loginId);
		return userList.size() > 0 ? userList.get(0) : null;
	}

	@Override
	public List<String> getUniqeLoginIDByLoginId(String loginID) {
		List<String> list = new ArrayList<String>();
		String id=loginID.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[0];
		List<String> userList = userDAO.getLoginIDsByLoginId(id+"%");

		if (userList.contains(loginID)) {
			for (int i = 0; i < userList.size() + 2; i++) {
				if (userList.contains(id + i)) {
				} else {
					list.add(id + i);
					if (list.size() == 2)
						return list;

				}
			}

		} else {
			//list.add(loginID);
			return null;
		}

		return null;
	}

	@Override
	public int addregisterUserApplication(TempUserRegister restServiceRequest) {
		return userDAO.addregisterUserApplication(restServiceRequest);
	}

	@Override
	public TempUserRegister UserApplicationOtpValidetion(String oTP, String id) {
		return userDAO.userApplicationOtpValidetion(oTP,id);
	}

	@Override
	public List<User> getOwnerUsersWithEmailByCustomerID(String customerId) {
		return userDAO.getOwnerUsersWithEmailByCustomerID(customerId);

	}

	@Override
	public List<TempUserRegister> getUserApplicationRequest(String fromDate, String toDate, String id, String status,String customerId,String requestTo) {
		return userDAO.getUserApplicationRequest(fromDate,toDate,id,status,customerId,requestTo);
	}

	@Override
	public boolean updateTempUserRegister(TempUserRegister userRegistrationRequest) {
		return userDAO.updateTempUserRegister(userRegistrationRequest);
	}


	@Override
	public List<User> getPortalAdminUsersWithEmail() {
		return userDAO.getPortalAdminUsersWithEmail();
	}

}
