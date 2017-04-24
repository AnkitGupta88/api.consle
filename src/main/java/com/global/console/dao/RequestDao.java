/**
 * 
 */
package com.global.console.dao;

import java.util.List;
import java.util.UUID;

import com.global.console.dto.ServiceRequest;
import com.global.console.model.WebServiceRequests;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestDao.
 */
public interface RequestDao {

	/**
	 * Creates the service request.
	 *
	 * @param request
	 *            the request
	 * @return the uuid
	 */
	public UUID createServiceRequest(ServiceRequest request);

	/**
	 * Grant service request.
	 *
	 * @param id
	 *            the id
	 */
	public void grantServiceRequest(String id);

	/**
	 * View service requests.
	 *
	 * @return the list
	 */
	public List<WebServiceRequests> viewServiceRequests();
}
