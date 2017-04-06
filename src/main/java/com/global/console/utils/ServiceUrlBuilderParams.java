package com.global.console.utils;

import java.util.HashMap;
import java.util.Map;

import com.global.console.dto.ServiceRegister;
import com.global.console.dto.UserDetail;

/**
 * The Class ServiceUrlBuilderParams.
 */
public class ServiceUrlBuilderParams {

	/**
	 * Register service builder params.
	 *
	 * @param service the service
	 * @return the map
	 */
	public static Map<String, String> registerServiceBuilderParams(ServiceRegister service) {
		Map<String, String> params = new HashMap<>();
		params.put("name", service.getServiceName());
		params.put("upstream_url", service.getServiceUrl());
		params.put("request_host", service.getApiName());
		params.put("request_path", "/" + service.getApiName());
		params.put("strip_request_path", "true");
		return params;
	}

	/**
	 * Adds the user service builder params.
	 *
	 * @param user the user
	 * @return the map
	 */
	public static Map<String, String> addUserServiceBuilderParams(UserDetail user) {
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUserName());
		return params;
	}

	/**
	 * Adds the user key service builder params.
	 *
	 * @param user the user
	 * @return the map
	 */
	public static Map<String, String> addUserKeyServiceBuilderParams(UserDetail user) {
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUserName());
		return params;
	}
}