/**
 * 
 */
package com.global.console.dto;

/**
 * The Class ServiceRequest.
 *
 * @author ankit.gupta4
 */
public class ServiceRequest {

	/** The service name. */
	private String serviceName;
	
	/** The user id. */
	private String userId;
	
	/** The subscription. */
	private String planId;

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
	 * @param serviceName the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the subscription.
	 *
	 * @return the subscription
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * Sets the subscription.
	 *
	 * @param subscription the new subscription
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
}
