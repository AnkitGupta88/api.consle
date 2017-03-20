package com.global.console.service;

import org.json.simple.JSONArray;

import com.global.console.dto.ServiceRequest;
import com.global.console.model.User;
import com.global.console.response.Result;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Request access.
	 *
	 * @param userId the user id
	 * @param serviceName the service name
	 * @return the uuid
	 */
	public Result requestAccess(ServiceRequest serviceRequest);
	
	/**
	 * View access.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User viewAccess(String id);

	/**
	 * View services.
	 *
	 * @return the JSON array
	 */
	public JSONArray viewServices();

}
