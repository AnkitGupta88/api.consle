package com.global.console.utils;

import java.util.HashMap;
import java.util.Map;

import com.global.console.dto.ServiceRegister;
import com.global.console.dto.UserDetail;

public class ServiceUrlBuilderParams {

	public static Map<String, String> registerServiceBuilderParams(ServiceRegister service) {
		Map<String, String> params = new HashMap<>();
		params.put("name", service.getServiceName());
		params.put("upstream_url", service.getServiceUrl());
		params.put("request_host", service.getApiName());
		params.put("request_path", "/" + service.getApiName());
		params.put("strip_request_path", "true");
		return params;
	}

	public static Map<String, String> addUserServiceBuilderParams(UserDetail user) {
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUserName());
		return params;
	}

	public static Map<String, String> addUserKeyServiceBuilderParams(UserDetail user) {
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUserName());
		return params;
	}
}