package com.global.console.service;

import java.util.List;

import com.global.console.model.WebServiceRequests;

/**
 * The Interface AdminService.
 */
public interface AdminRequest {

	/**
	 * Grant service.
	 *
	 * @param inputParams
	 *            the input params
	 * @return the string
	 */
	public String grantService(String inputParams);

	/**
	 * View requests.
	 *
	 * @return the list
	 */
	public List<WebServiceRequests> viewRequests();

}
