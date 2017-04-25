package com.global.console.kong.response;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class Consumer {

	/** The id. */
	private String id;

	/** The user name. */
	private String loginId;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The email id. */
	private String emailId;

	/** The phone no. */
	private String phoneNo;

	/** The key. */
	private String key;

	/** The role. */
	private String role;

	/** The web service. */
	private Map<String, List<ApiPlugin>> webService;

	/** The web url. */
	private Map<String, String> webUrl;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets the login id.
	 *
	 * @param loginId
	 *            the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo
	 *            the new phone no
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the web service.
	 *
	 * @return the web service
	 */
	public Map<String, List<ApiPlugin>> getWebService() {
		return webService;
	}

	/**
	 * Sets the web service.
	 *
	 * @param webService
	 *            the web service
	 */
	public void setWebService(Map<String, List<ApiPlugin>> webService) {
		this.webService = webService;
	}

	/**
	 * Gets the web url.
	 *
	 * @return the web url
	 */
	public Map<String, String> getWebUrl() {
		return webUrl;
	}

	/**
	 * Sets the web url.
	 *
	 * @param webUrl
	 *            the web url
	 */
	public void setWebUrl(Map<String, String> webUrl) {
		this.webUrl = webUrl;
	}

}
