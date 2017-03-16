package com.global.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.global.dto.User;
import com.global.dto.WebServiceRequests;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminService.
 */
public interface AdminService {

	/**
	 * Adds the user.
	 *
	 * @param userParams the user params
	 * @return the string
	 */
	public String addUser(String userParams);
	
	/**
	 * View all users.
	 *
	 * @return the list
	 */
	public List<User> viewAllUsers();
	
	/**
	 * View user.
	 *
	 * @param id the id
	 * @return the user
	 */
	public JSONObject viewUser(String id);
	
	/**
	 * View services.
	 *
	 * @return the JSON array
	 */
	public JSONArray viewServices();
	
	/**
	 * Grant service.
	 *
	 * @param inputParams the input params
	 * @return the string
	 */
	public String grantService(String inputParams);
	
	/**
	 * Adds the service.
	 *
	 * @param inputParams the input params
	 * @return the JSON object
	 */
	public JSONObject addService(String inputParams);
	
	/**
	 * View requests.
	 *
	 * @return the list
	 */
	public List<WebServiceRequests> viewRequests();
	
	/**
	 * Delete service.
	 *
	 * @param serviceName the service name
	 * @return the string
	 */
	public String deleteService(String serviceName);

	public JSONObject viewService(String serviceName);

	public String deletePlugins(String serviceName, String id);

	public JSONArray viewPlugins(String serviceName);
}
