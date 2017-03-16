package com.global.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.dto.RateLimiting_Metrics;
import com.global.dto.User;
import com.global.dto.WebService;
import com.global.dto.WebServiceRequests;
import com.global.repository.RateLimitingMetricsRepository;
import com.global.repository.RequestRepository;
import com.global.repository.UserRepository;
import com.global.service.AdminService;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminServiceImpl implements AdminService {

	/** The Constant KONGADMIN. */
	private static final String KONGADMIN = "172.16.24.73:8001";
	
	/** The Constant KONGUSER. */
	private static final String KONGUSER = "172.16.24.73:8000";

	/** The repository. */
	@Autowired
	UserRepository repository;

	/** The request repository. */
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RateLimitingMetricsRepository rateLimitingRepository;

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#addUser(java.lang.String)
	 */
	@Override
	public String addUser(String userParams) {
		User user = null;
		try {
			user = getInputParamsClass(userParams, User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (user != null) {
			Map<String, String> params = null;
			JSONObject json = null;
			String response = null;
			String url = null;
			try {
				params = new HashMap<>();
				params.put("username", user.getName());
				url = "http://" + KONGADMIN + "/consumers/";
				response = postRequest(url, params, String.class);
				json = (JSONObject) JSONValue.parse(response);
				user.setId(json.get("id").toString());

				params.clear();
				params.put(" ", " ");
				url = "http://" + KONGADMIN + "/consumers/" + user.getName() + "/key-auth";
				response = postRequest(url, null, String.class);
				json = (JSONObject) JSONValue.parse(response);
				user.setKey((String)json.get("key"));
				repository.save(user);
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return user.getId();
	}

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#viewAllUsers()
	 */
	@Override
	public List<User> viewAllUsers() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#viewUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject viewUser(String id) {
		JSONObject userDetails = new JSONObject();
		userDetails.put("user", repository.findById(id));
		String response = null;
		String url = "http://" + KONGADMIN + "/plugins?consumer_id="+id;
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject json = (JSONObject) JSONValue.parse(response);
		userDetails.put("Plugins", json.get("data"));
		List<RateLimiting_Metrics> rateLimiting_metrics = rateLimitingRepository.findByIdentifier(id);
		userDetails.put("Rate Limiting", rateLimiting_metrics);
		return userDetails;
	}

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#viewServices()
	 */
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

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#addService(java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#viewRequests()
	 */
	@Override
	public List<WebServiceRequests> viewRequests() {
		return requestRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.lakshayswani.service.AdminService#grantService(java.lang.String)
	 */
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
//			webService.setUrl("http://" + KONGUSER + "/" + webService.getName() + "?apikey=" + user.getKey());
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
		String url = "http://" + KONGADMIN + "/apis/" + serviceName +"/plugins";
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
	 * @param <T> the generic type
	 * @param inputParams the input params
	 * @param t the t
	 * @return the input params class
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private <T> T getInputParamsClass(String inputParams, Class<T> t)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputParams, t);
	}

	/**
	 * Gets the request.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param params the params
	 * @param t the t
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
	 * @param <T> the generic type
	 * @param url the url
	 * @param params the params
	 * @param t the t
	 * @return the t
	 * @throws URISyntaxException the URI syntax exception
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

}