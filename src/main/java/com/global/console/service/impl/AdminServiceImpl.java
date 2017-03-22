package com.global.console.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.dto.ServiceRegister;
import com.global.console.response.Result;
import com.global.console.service.AdminService;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.ServiceUrlBuilderParams;

// TODO: Auto-generated Javadoc
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
	public JSONArray viewServices() {
		String url = null;
		String response = null;
		url = apiConfig.getAdminUrl() + "/apis";
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject json = (JSONObject) JSONValue.parse(response);
		JSONArray data = (JSONArray) json.get("data");
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminService#addService(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject addService(String inputParams) {

		Map<String, Object> inputs = null;
		try {
			inputs = getInputParamsClass(inputParams, Map.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String name = (String) inputs.get("name");
		String upstream_url = (String) inputs.get("upstream_url");
		String request_host = (String) inputs.get("request_host");

		Map<String, String> params = new HashMap<>();
		String url;
		String response = null;
		try {
			params.put("name", name);
			params.put("upstream_url", upstream_url);
			params.put("request_host", request_host);
			params.put("request_path", "/" + name);
			params.put("strip_request_path", "true");
			url = apiConfig.getAdminUrl() + "/apis/";
			response = postRequest(url, params, String.class);

			params.clear();
			params.put("name", "key-auth");
			url = apiConfig.getAdminUrl() + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

			params.clear();
			params.put("name", "rate-limiting");
			params.put("config.second", "0");
			url = apiConfig.getAdminUrl() + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

			params.clear();
			params.put("name", "acl");
			params.put("config.whitelist", name);
			url = apiConfig.getAdminUrl() + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		JSONObject json = (JSONObject) JSONValue.parse(response);

		return json;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#deleteService(java.lang.String)
	 */
	@Override
	public String deleteService(String serviceName) {

		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName;
		try {
			deleteRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			serviceName = null;
		}

		return serviceName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#viewService(java.lang.String)
	 */
	@Override
	public JSONObject viewService(String serviceName) {
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName;
		String response = null;
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject res = (JSONObject) JSONValue.parse(response);
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#deletePlugins(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String deletePlugins(String serviceName, String id) {
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName + "/plugins/" + id;
		try {
			deleteRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			serviceName = null;
		}
		return serviceName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminService#viewPlugins(java.lang.String)
	 */
	@Override
	public JSONArray viewPlugins(String serviceName) {
		String url = apiConfig.getAdminUrl() + "/apis/" + serviceName + "/plugins";
		String response = null;
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject res = (JSONObject) JSONValue.parse(response);
		return (JSONArray) res.get("data");
	}

	/**
	 * Gets the input params class.
	 *
	 * @param <T>
	 *            the generic type
	 * @param inputParams
	 *            the input params
	 * @param t
	 *            the t
	 * @return the input params class
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private <T> T getInputParamsClass(String inputParams, Class<T> t)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputParams, t);
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
	public Result registerService(ServiceRegister service) {
		Result result = new Result();
		try {
			String url = apiConfig.getAdminUrl() + "/" + ApiConstants.APIS + "/";
			Map<String, String> serviceParams = ServiceUrlBuilderParams.registerServiceBuilderParams(service);
			String response = postRequest(url, serviceParams, String.class);
			result.setResponseCode(HttpStatus.OK);
			result.setResponseMsg(response);
			
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
		}
		return result;
	}

}