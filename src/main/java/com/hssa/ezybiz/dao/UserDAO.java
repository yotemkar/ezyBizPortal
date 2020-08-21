package com.hssa.ezybiz.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.TempUserRegister;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserDto;


public interface UserDAO {
	List<User> search(User user);
	
	/**
	 * Used in role mapping
	 * Method returns list of users.
	 * @return
	 */
	List<User> getAll(Integer companyId);

	int insertUser(User user, int id, int userId);

	int deleteUser(ServerSession serverSession, User user);
	/**
	 * 
	 * @param oldUser (for Audit Trail)
	 * @param newUser
	 * @param id (for Audit Trail)
	 * @param userId (for Audit Trail)
	 * @return
	 */
	int updateUser(User oldUser, User newUser, int id, int userId);

	User findUserByID(int userId);

	User getUserByName(ServerSession serverSession, String userLoginName) throws IncorrectResultSizeDataAccessException ;

	int deletSecurityQuest(ServerSession serverSession, User frmUser);

	List<User> getUserByEmail(ServerSession attributeValueAsObject, User user);

	User getUserByEmailID(ServerSession attributeValueAsObject, User user);
	User getUserByEmailIdAndCompanyId(String emailId,int compayId) throws IncorrectResultSizeDataAccessException ;

	List<UserDto> getAllFilteredUser(UserDto userDto, Integer companyId);

	UserDto findUserDtoByID(Integer loggedInUserId);

	String getUserTypeByUserId(Integer userId);

	List<Map<String, Object>> findUserSalesRegionAndSalesOffice(Integer userId);

	List<Map<String, Object>> findEmployeeSalesRegionAndSalesOffice(Integer userId);
	
	User getUserByMobileAndCompanyId(String mobileNumber,int companyId) throws IncorrectResultSizeDataAccessException ;
	
	List<User> getOwnerUsersByCustomerCode(String customerCode);

	User getUserByEmailID(Object object, String email);

	User getUserByMobile(String mobileNumber);

	List<User> getUserByLoginId(String loginId);

	User getUserByEmailID(User user);

	User getUserByEmailID(String email);

	List<String> getLoginIDsByLoginId(String loginID);

	int addregisterUserApplication(TempUserRegister restServiceRequest);

	TempUserRegister userApplicationOtpValidetion(String oTP, String id);

	List<User> getOwnerUsersWithEmailByCustomerID(String customerId);

	List<TempUserRegister> getUserApplicationRequest(String fromDate, String toDate, String id, String status,String customerId,String requestTo);

	boolean updateTempUserRegister(TempUserRegister userRegistrationRequest);


	List<User> getPortalAdminUsersWithEmail();

	User getUserOnlyByEmailId(String emailId);
	
}
