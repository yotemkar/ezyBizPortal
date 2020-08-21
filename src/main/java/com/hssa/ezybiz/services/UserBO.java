package com.hssa.ezybiz.services;

import java.util.List;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.hssa.ezybiz.dto.ServerSession;
import com.hssa.ezybiz.dto.TempUserRegister;
import com.hssa.ezybiz.dto.User;
import com.hssa.ezybiz.dto.UserDto;


public interface UserBO {
	/**
	 * Returns the list of frmUser as per parameters contain in paramList.
	 * <p>
	 * Any developer added fields need to be taken care of by developer The
	 * lookup descriptions and FK descriptions are also fetched in VO. <br/>
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param paramList
	 *            arguments having the zero or more argument with same or
	 *            different datatypes. <br/>
	 *            The type of paramList values will be identify in
	 *            getSearchableTokens method of DaoHelper class.
	 * @return list of frmUser of type FrmBase
	 * @see getAllFrmUserList
	 */
	List<User> search(User user);

	/**
	 * This method returns all frmUser. <br/>
	 * The lookup descriptions and FK descriptions are also fetched in VO.
	 * <p>
	 * Any developer added fields need to be taken care of by developer
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @return list of Employees
	 * @see search
	 */
	List<User> getAllUserList(ServerSession serverSession);

	/**
	 * Returns 1 if value inserted successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values inserted into frm_user table. The graphics primitives that
	 * show all rows on the screen with new one.
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param user
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set createdBy field
	 * @return 1 if values inserted successfully otherwise return 0.
	 * @throws Exception 
	 * @see getNextValue method of frmSequence class
	 */
	int insertUser(User user) throws Exception;

	/**
	 * Returns 1 if value deleted from database.
	 * <p>
	 * This method always returns immediately, When this method called the
	 * current row from frm_user table will be deleted base on id. The graphics
	 * primitives that show remaining rows on the screen.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID,
	 *            module, subModule
	 * @param user
	 *            arguments having the current selected row values
	 * @return 1 if current row deleted successfully otherwise return 0.
	 * @see nothing
	 */
	int deleteUser(ServerSession serverSession, User user);


	/**
	 * Returns only single row based on id.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * current id values loaded in frmUser object. The graphics primitives show
	 * other fields based on that id from same row.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID,
	 *            module, subModule
	 * @param id
	 *            argument is a current selected row id that use to fetch other
	 *            related data of same row
	 * @return Employees object
	 * @see nothing
	 */
	
	
	User findUserByID(int userId);

	int updateUser(User user, int userId);

	/**
	 * Method to get user object by loginName.
	 * @param serverSession
	 * @param userLoginName
	 * @return
	 */
	User getUserByName(ServerSession serverSession, String userLoginName) throws IncorrectResultSizeDataAccessException ;

	/**
	 * Method to delete security questions
	 * @param attributeValueAsObject
	 * @param user
	 * @return
	 */
	int deletSecurityQuest(ServerSession attributeValueAsObject, User user);

	/**
	 * Method to get user list by email id
	 * @param attributeValueAsObject
	 * @param user
	 * @return
	 */
	List<User> getUserByEmail(ServerSession attributeValueAsObject, User user);

	/**
	 * Method to get user from email id
	 * @param attributeValueAsObject
	 * @param user
	 * @return
	 */
	User getUserByEmailID(ServerSession attributeValueAsObject, User user);

	/**
	 * Method to delete user and all of its data.
	 * @param serverSession
	 * @param userVO
	 * @return
	 */
	int deleteUserAndAlldata(ServerSession serverSession, User userVO);

	/**
	 * Method to get user from email and company id
	 * @param emailId
	 * @param companyId
	 * @return
	 * @throws IncorrectResultSizeDataAccessException
	 */
	User getUserByEmailIdAndCompanyId(String emailId, int companyId)throws IncorrectResultSizeDataAccessException ;

	/**
	 * Method to insert user of employee.
	 * @param user
	 * @param updatedUserID
	 * @return
	 */
	int insertUserForEmployee(User user, int updatedUserID);

	List<UserDto> getAllFilteredUser(UserDto userDto, Integer companyId);

	UserDto findUserDtoByID(Integer loggedInUserId);
	
	public String getUserTypeByUserId(Integer userId);

	List<Map<String, Object>> findUserSalesRegionAndSalesOffice(Integer userId);

	List<Map<String, Object>> findEmployeeSalesRegionAndSalesOffice(Integer userId);
	
	User getUserByMobileAndCompanyId(String mobileNumber, int companyId)throws IncorrectResultSizeDataAccessException ;
	
	List<User> getOwnerUsersByCustomerCode(String customerCode);

	User getUserByEmailId(Object object, String email);

	User getUserByMobile(String mobileNumber);

	User getUserByLoginId(String loginId);

	User getUserByEmailId(String email);

	List<String> getUniqeLoginIDByLoginId(String loginID);

	int addregisterUserApplication(TempUserRegister restServiceRequest);

	TempUserRegister UserApplicationOtpValidetion(String oTP, String id);

	List<User> getOwnerUsersWithEmailByCustomerID(String customerId);

	List<TempUserRegister> getUserApplicationRequest(String fromDate, String toDate, String id, String status,String customerId,String requestTo);

	boolean updateTempUserRegister(TempUserRegister userRegistrationRequest);


	List<User> getPortalAdminUsersWithEmail();

	User getUserOnlyByEmailId(String emailId) throws IncorrectResultSizeDataAccessException;
}
