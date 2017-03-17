package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datastax.driver.core.utils.UUIDs;
import com.global.console.model.User;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.RequestRepository;
import com.global.console.repository.UserRepository;
import com.global.console.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	@Override
	public UUID requestAccess(String userId, String serviceName) {
		WebServiceRequests webServiceRequests = new WebServiceRequests();
		webServiceRequests.setId(UUIDs.timeBased());
		webServiceRequests.setUserId(userId);
		webServiceRequests.setServiceName(serviceName);
		webServiceRequests.setStatus("Pending");
		requestRepository.save(webServiceRequests);
		return webServiceRequests.getId();
	}

	@Override
	public User viewAccess(String userId) {
		User user = userRepository.findById(userId);
		return user;
	}

	@Override
	public JSONArray viewServices() {
		String url = null;
		String response = null;
		url = "http://172.16.24.73:8001/apis";
		try {
			response = getRequest(url, null, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject json = (JSONObject) JSONValue.parse(response);
		JSONArray data = (JSONArray) json.get("data");
		return data;
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

}
