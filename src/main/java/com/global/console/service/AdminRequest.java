package com.global.console.service;

import com.global.console.model.WebServiceRequests;
import com.global.console.response.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminRequest.
 */
public interface AdminRequest {

	/**
	 * Grant service.
	 *
	 * @param requestId
	 *            the request id
	 * @return the string
	 */
	public Response<String> grantService(String requestId);

	/**
	 * View requests.
	 *
	 * @return the list
	 */
	public Response<WebServiceRequests> viewRequests();

}
