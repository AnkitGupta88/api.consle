package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.dto.ServiceRegister;
import com.global.console.response.Response;
import com.global.console.service.AdminService;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.ServiceUrlBuilderParams;

/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminServiceImpl implements AdminService {

	/** The api config. */
	@Autowired
	private ApiConfiguration apiConfig;

	/** The user dao impl. */
	@Autowired
	UserDaoImpl userDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminService#viewServices()
	 */
	@Override
	public Response<Object> viewServices() {
		String url = null;
		String response = null;
		Response<Object> finalResponse;
		url = apiConfig.getAdminUrl() + "/apis";
		try {
			response = getRequest(url, null, String.class);
			finalResponse = new Response<>(Arrays.asList(((JSONObject) JSONValue.parse(response)).get("data")), HttpStatus.OK, "Request Completed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}
		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#deleteService(java.lang.String)
	 */
	@Override
	public Response<String> deleteService(String serviceName) {
		Response<String> finalResponse;

		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName;
		try {
			deleteRequest(url);
			finalResponse = new Response<>(Arrays.asList(serviceName), HttpStatus.OK, "Request Completed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}

		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#viewService(java.lang.String)
	 */
	@Override
	public Response<Object> viewService(String serviceName) {
		Response<Object> finalResponse;
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName;
		String response = null;
		try {
			response = getRequest(url, null, String.class);
			finalResponse = new Response<>(Arrays.asList((JSONObject) JSONValue.parse(response)), HttpStatus.OK, "Request Completed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}
		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#deletePlugins(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Response<String> deletePlugins(String serviceName, String id) {
		Response<String> finalResponse;
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName + "/plugins/" + id;
		try {
			deleteRequest(url);
			finalResponse = new Response<>(Arrays.asList(serviceName), HttpStatus.OK, "Request Completed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}
		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#viewPlugins(java.lang.String)
	 */
	@Override
	public Response<Object> viewPlugins(String serviceName) {
		Response<Object> finalResponse;
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName + "/plugins";
		String response = null;
		try {
			response = getRequest(url, null, String.class);
			finalResponse = new Response<>(Arrays.asList(((JSONObject) JSONValue.parse(response)).get("data")), HttpStatus.OK, "Request Completed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}
		return finalResponse;
	}

	/**
	 * Gets the request.
	 *
	 * @param <T>
	 *            the generic type
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param t
	 *            the t
	 * @return the request
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	private <T> T getRequest(String url, Map<String, String> params, Class<T> t) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(url);
		return restTemplate.getForObject(uri, t);
	}

	/**
	 * Post request.
	 *
	 * @param <T>
	 *            the generic type
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param t
	 *            the t
	 * @return the t
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	private <T> T postRequest(String url, Map<String, String> params, Class<T> t) throws URISyntaxException {
		URI uri = new URI(url);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(uri, params, t);
	}

	/**
	 * Delete request.
	 *
	 * @param url
	 *            the url
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	private void deleteRequest(String url) throws URISyntaxException {
		URI uri = new URI(url);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminService#registerService(com.global.
	 * console.dto.ServiceRegister)
	 */
	@Override
	public Response<String> registerService(ServiceRegister service) {
		Response<String> finalResponse;
		try {
			String url = apiConfig.getAdminUrl() + "/" + ApiConstants.APIS + "/";
			Map<String, String> serviceParams = ServiceUrlBuilderParams.registerServiceBuilderParams(service);
			String response = postRequest(url, serviceParams, String.class);
			finalResponse = new Response<>(Arrays.asList(response), HttpStatus.OK, "Request Completed");

			serviceParams.clear();
			serviceParams.put("name", "key-auth");
			url = apiConfig.getAdminUrl() + "/apis/" + service.getServiceName() + "/plugins/";
			postRequest(url, serviceParams, String.class);

			serviceParams.clear();
			serviceParams.put("name", "rate-limiting");
			serviceParams.put("config.second", "10");
			url = apiConfig.getAdminUrl() + "/apis/" + service.getServiceName() + "/plugins/";
			postRequest(url, serviceParams, String.class);

			serviceParams.clear();
			serviceParams.put("name", "acl");
			serviceParams.put("config.whitelist", service.getServiceName());
			url = apiConfig.getAdminUrl() + "/apis/" + service.getServiceName() + "/plugins/";
			postRequest(url, serviceParams, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, "Unable to process");
		}
		return finalResponse;
	}

}