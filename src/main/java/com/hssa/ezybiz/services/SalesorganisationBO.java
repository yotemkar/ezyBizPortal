package com.hssa.ezybiz.services;

import java.util.List;

import com.hssa.ezybiz.dto.Salesorganisation;
import com.hssa.ezybiz.dto.ServerSession;

public interface SalesorganisationBO {

	/**
	 * Returns the list of salesorganisation as per parameters contain in paramList.
	 * <p>
	 * Any developer added fields need to be taken care of by developer The lookup
	 * descriptions and FK descriptions are also fetched in VO. <br/>
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param paramList
	 *            arguments having the zero or more argument with same or different
	 *            datatypes. <br/>
	 *            The type of paramList values will be identify in
	 *            getSearchableTokens method of DaoHelper class.
	 * @return list of salesorganisation of type Base
	 * @see getAllSalesorganisationList
	 */
	List<Salesorganisation> search(ServerSession serverSession, List<Object> paramList);

	/**
	 * This method returns all salesorganisation. <br/>
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
	List<Salesorganisation> getAllSalesorganisationList();

	/**
	 * Returns 1 if value inserted successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values inserted into salesorganisation table. The graphics primitives
	 * that show all rows on the screen with new one.
	 *
	 * @param serverSession
	 *            argument having the information of current sessionID, userID,
	 *            module, subModule
	 * @param salesorganisation
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set createdBy field
	 * @return 1 if values inserted successfully otherwise return 0.
	 * @see getNextValue method of Sequence class
	 */
	int insertSalesorganisation(Salesorganisation salesorganisation, int id, int userId);

	/**
	 * Returns 1 if value deleted from database.
	 * <p>
	 * This method always returns immediately, When this method called the current
	 * row from salesorganisation table will be deleted base on id. The graphics
	 * primitives that show remaining rows on the screen.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID, module,
	 *            subModule
	 * @param salesorganisation
	 *            arguments having the current selected row values
	 * @return 1 if current row deleted successfully otherwise return 0.
	 * @see nothing
	 */
	int deleteSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation);

	/**
	 * Returns 1 if value updated successfully.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * object values updated into salesorganisation table. The graphics primitives
	 * that show all rows on the screen with new updated one.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID, module,
	 *            subModule
	 * @param salesorganisation
	 *            arguments having the current inserted values
	 * @param userId
	 *            argument is a current login user id use to set updatedBy field
	 * @return 1 if values updated successfully otherwise return 0.
	 * @see nothing
	 */
	int updateSalesorganisation(ServerSession serverSession, Salesorganisation salesorganisation, int userId);

	/**
	 * Returns only single row based on id.
	 * <p>
	 * This method always returns immediately, When this method called the the
	 * current id values loaded in salesorganisation object. The graphics primitives
	 * show other fields based on that id from same row.
	 *
	 * @param serverSession
	 *            argument having the information about sessionID, userID, module,
	 *            subModule
	 * @param id
	 *            argument is a current selected row id that use to fetch other
	 *            related data of same row
	 * @return Employees object
	 * @see nothing
	 */
	Salesorganisation findSalesorganisationByID(int id);

	public List<Salesorganisation> findSalesorganisationByFKEnterpriseid(ServerSession serverSession, int enterpriseid);

	Salesorganisation findSalesOrganisationByCustomerId(Integer customerId);

	int getSaleOrganisationCountByCode(String code);

	Salesorganisation getSaleOrganisationByCode(String salesOrg);

	String findSalesOrganisationCodeByCustomerId(Integer customerId);

	String getSalesOrgCodeByCustomerCode(String customerCode);

	List<Salesorganisation> getAllSalesOrgByCustomerCode(String customerCode);

}
