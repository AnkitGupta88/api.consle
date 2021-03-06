/**
 * 
 */
package com.global.console.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceRegister.
 */
public class ServiceRegister {

	/** The service name. */
	private String serviceName;

	/** The service url. */
	private String serviceUrl;

	/** The api name. */
	private String apiName;

	/** The created by. */
	private String createdBy;

	/** The security. */
	private List<String> securityPlugins;

	/**
	 * Gets the securityPlugins.
	 *
	 * @return the securityPlugins
	 */
	public List<String> getSecurityPlugins() {
		return securityPlugins;
	}

	/**
	 * Sets the securityPlugins.
	 *
	 * @param securityPlugins
	 *            the new securityPlugins
	 */
	public void setSecurityPlugins(List<String> securityPlugins) {
		this.securityPlugins = securityPlugins;
	}

	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName
	 *            the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Gets the service url.
	 *
	 * @return the service url
	 */
	public String getServiceUrl() {
		return serviceUrl;
	}

	/**
	 * Sets the service url.
	 *
	 * @param serviceUrl
	 *            the new service url
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * Gets the api name.
	 *
	 * @return the api name
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * Sets the api name.
	 *
	 * @param apiName
	 *            the new api name
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
