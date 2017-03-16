package com.global.service;

import java.util.UUID;

import org.json.simple.JSONArray;

import com.global.dto.User;

// TODO: Auto-generated Javadoc
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
	public UUID requestAccess(String userId, String serviceName);
	
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