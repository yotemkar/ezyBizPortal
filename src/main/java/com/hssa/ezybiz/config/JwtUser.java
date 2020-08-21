package com.hssa.ezybiz.config;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.Customer;
import com.hssa.ezybiz.dto.Salesorganisation;
/**
 * @author KPIT
 */
public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userLoginName;
	private String givenName;
	private String middleName;
	private String familyName;
	private String userEmailId;
	private String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final Date lastPasswordResetDate;
	private final Date dateOfBirth;
	private int companyid;
	private Integer superUserId;
	private String userType;
	private Date lastLoginTime;
	private String mobileNumber;
	private String district;
	private Map<String, Content> resourcessMap;
	private List<Salesorganisation> salesOrgs;
	private Customer customer;

	public String getOcpApimSubscriptionKey() {
		return ocpApimSubscriptionKey;
	}

	public void setOcpApimSubscriptionKey(String ocpApimSubscriptionKey) {
		this.ocpApimSubscriptionKey = ocpApimSubscriptionKey;
	}

	private String coretexSessionID;
	private String ocpApimSubscriptionKey;

	public String getCoretexSessionID() {
		return coretexSessionID;
	}

	public void setCoretexSessionID(String coretexSessionID) {
		this.coretexSessionID = coretexSessionID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Salesorganisation> getSalesOrgs() {
		return salesOrgs;
	}

	public void setSalesOrgs(List<Salesorganisation> salesOrgs) {
		this.salesOrgs = salesOrgs;
	}

	public JwtUser(int id, String userLoginName, String givenName, String middleName, String familyName, String email,
			// String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate,
			Date dateOfBirth, int companyid, Integer superUserId, String userType) {
		this.userId = id;
		this.userLoginName = userLoginName;
		this.givenName = givenName;
		this.middleName = middleName;
		this.familyName = familyName;
		this.userEmailId = email;
		// this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.dateOfBirth = dateOfBirth;
		this.companyid = companyid;
		this.superUserId = superUserId;
		this.userType = userType;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userLoginName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSuperUserId() {
		return superUserId;
	}

	public void setSuperUserId(Integer superUserId) {
		this.superUserId = superUserId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTIme) {
		this.lastLoginTime = lastLoginTIme;
	}

	public Map<String, Content> getResourcessMap() {
		return resourcessMap;
	}

	public void setResourcessMap(Map<String, Content> resourcessMap) {
		this.resourcessMap = resourcessMap;
	}

}
