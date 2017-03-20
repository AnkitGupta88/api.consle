package com.global.console.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.global.console.dto.ServiceRegister;
import com.global.console.response.Result;

/**
 * The Interface AdminService.
 */
public interface AdminService {

	
	/**
	 * View services.
	 *
	 * @return the JSON array
	 */
	public JSONArray viewServices();
	

	/**
	 * Adds the service.
	 *
	 * @param inputParams the input params
	 * @return the JSON object
	 */
	public JSONObject addService(String inputParams);
	
	/**
	 * Register service.
	 *
	 * @param service the service
	 * @return the result
	 */
	public Result registerService(ServiceRegister service);
	
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
