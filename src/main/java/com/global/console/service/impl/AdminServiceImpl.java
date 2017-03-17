package com.global.console.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.global.console.dto.UserDetail;
import com.global.console.model.RateLimiting_Metrics;
import com.global.console.model.User;
import com.global.console.model.WebService;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.RateLimitingMetricsRepository;
import com.global.console.repository.RequestRepository;
import com.global.console.repository.UserRepository;
import com.global.console.response.Result;
import com.global.console.service.AdminService;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.ServiceUrlBuilderParams;

/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminServiceImpl implements AdminService {

	/** The Constant KONGADMIN. */
	private static final String KONGADMIN = "172.16.24.73:8001";

	/** The Constant KONGUSER. */
	private static final String KONGUSER = "172.16.24.73:8000";

	@Autowired
	private ApiConfiguration apiConfig;

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private RateLimitingMetricsRepository rateLimitingRepository;

	@Autowired
	UserDaoImpl userDaoImpl;

	@Override
	public Result addUser(UserDetail user) {
		Map<String, String> params = null;
		JSONObject response = null;
		String url = null;
		Result result = new Result();
		try {
			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/";
			params = ServiceUrlBuilderParams.addUserServiceBuilderParams(user);
			response = postRequest(url, params, JSONObject.class);
			String id = response.get(ApiConstants.ID).toString();

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + user.getUserName() + "/" + ApiConstants.KEY_AUTH;
			params = ServiceUrlBuilderParams.addUserKeyServiceBuilderParams(user);
			response = postRequest(url, null, JSONObject.class);
			String key = response.get(ApiConstants.KEY).toString();

			userDaoImpl.addUser(user, id, key);

			result.setResponseMsg(id);
			result.setResponseCode(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<User> viewAllUsers() {
		return userDaoImpl.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result viewUser(String id) {
		Result result = new Result();
		JSONObject userDetails = new JSONObject();
		userDetails.put("user", repository.findById(id));
		String response = null;
		String url = apiConfig.getAdminUrl() + "/" + ApiConstants.plugins + "?" + ApiConstants.consumer_id + "=" + id;
		try {
			response = getRequest(url, null, String.class);
			result.setResponseMsg(response);
			result.setResponseCode(HttpStatus.OK);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
//		JSONObject json = (JSONObject) JSONValue.parse(response);
//		userDetails.put("Plugins", json.get("data"));
//		List<RateLimiting_Metrics> rateLimiting_metrics = rateLimitingRepository.findByIdentifier(id);
//		userDetails.put("Rate Limiting", rateLimiting_metrics);
		return result;
	}

	@Override
	public JSONArray viewServices() {
		String url = null;
		String response = null;
		url = "http://" + KONGADMIN + "/apis";
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject json = (JSONObject) JSONValue.parse(response);
		JSONArray data = (JSONArray) json.get("data");
		return data;
	}

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
			url = "http://" + KONGADMIN + "/apis/";
			response = postRequest(url, params, String.class);

			params.clear();
			params.put("name", "key-auth");
			url = "http://" + KONGADMIN + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

			params.clear();
			params.put("name", "rate-limiting");
			params.put("config.second", "0");
			url = "http://" + KONGADMIN + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

			params.clear();
			params.put("name", "acl");
			params.put("config.whitelist", name);
			url = "http://" + KONGADMIN + "/apis/" + name + "/plugins/";
			postRequest(url, params, String.class);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		JSONObject json = (JSONObject) JSONValue.parse(response);

		return json;
	}

	@Override
	public List<WebServiceRequests> viewRequests() {
		return requestRepository.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String grantService(String inputParams) {

		Map<String, Object> inputs = null;
		try {
			inputs = getInputParamsClass(inputParams, Map.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (inputs.get("id") != null) {
			UUID id = UUID.fromString((String) inputs.get("id"));
			String configType = (inputs.get("configType") != null ? (String) inputs.get("configType") : "hour");
			String permittedRequests = (inputs.get("permittedRequests") != null
					? (String) inputs.get("permittedRequests") : "100");

			WebServiceRequests webServiceRequest = requestRepository.findById(id);
			webServiceRequest.setStatus("completed");
			requestRepository.save(webServiceRequest);
			Map<String, String> params = new HashMap<>();
			String url = null;
			try {
				params.put("name", "rate-limiting");
				params.put("consumer_id", webServiceRequest.getUserId());
				params.put("config." + configType, permittedRequests);
				url = "http://" + KONGADMIN + "/apis/" + webServiceRequest.getServiceName() + "/plugins/";
				postRequest(url, params, String.class);

				params.clear();
				params.put("group", webServiceRequest.getServiceName());
				url = "http://" + KONGADMIN + "/consumers/" + webServiceRequest.getUserId() + "/acls/";
				postRequest(url, params, String.class);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			User user = repository.findOne(webServiceRequest.getUserId());
			WebService webService = new WebService();
			webService.setName(webServiceRequest.getServiceName());
			// webService.setUrl("http://" + KONGUSER + "/" +
			// webService.getName() + "?apikey=" + user.getKey());
			webService.setUrl("http://" + KONGUSER + "/" + webService.getName());
			List<WebService> webServiceList = user.getWebServices();
			if (webServiceList == null) {
				webServiceList = new ArrayList<>();
			}
			webServiceList.add(webService);
			user.setWebServices(webServiceList);
			repository.save(user);
			return id.toString();
		} else {
			return null;
		}
	}

	@Override
	public String deleteService(String serviceName) {

		String url = "http://" + KONGADMIN + "/apis/" + serviceName;
		try {
			deleteRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			serviceName = null;
		}

		return serviceName;
	}

	@Override
	public JSONObject viewService(String serviceName) {
		String url = "http://" + KONGADMIN + "/apis/" + serviceName;
		String response = null;
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject res = (JSONObject) JSONValue.parse(response);
		return res;
	}

	@Override
	public String deletePlugins(String serviceName, String id) {
		String url = "http://" + KONGADMIN + "/apis/" + serviceName + "/plugins/" + id;
		try {
			deleteRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			serviceName = null;
		}
		return serviceName;
	}

	@Override
	public JSONArray viewPlugins(String serviceName) {
		String url = "http://" + KONGADMIN + "/apis/" + serviceName + "/plugins";
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

	private void deleteRequest(String url) throws URISyntaxException {
		URI uri = new URI(url);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri);
	}

	@Override
	public Result registerService(ServiceRegister service) {
		Result result = new Result();
		try {
			String url = apiConfig.getAdminUrl() + "/" + ApiConstants.APIS + "/";
			Map<String, String> serviceParams = ServiceUrlBuilderParams.registerServiceBuilderParams(service);
			String response = postRequest(url, serviceParams, String.class);
			result.setResponseCode(HttpStatus.OK);
			result.setResponseMsg(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}