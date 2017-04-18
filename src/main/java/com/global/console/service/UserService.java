package com.global.console.service;


import com.global.console.dto.ServiceRequest;
import com.global.console.model.User;
import com.global.console.response.Response;

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
	public Response<String> requestAccess(ServiceRequest serviceRequest);
	
	/**
	 * View access.
	 *
	 * @param id the id
	 * @return the user
	 */
	public Response<User> viewAccess(String id);

	/**
	 * View services.
	 *
	 * @return the JSON array
	 */
	public Response<Object> viewServices();

	public Response<String> getToken(String userId);

}
