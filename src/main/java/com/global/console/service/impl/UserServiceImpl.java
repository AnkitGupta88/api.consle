package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.dao.impl.RequestsDaoImpl;
import com.global.console.dto.ServiceRequest;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;
import com.global.console.response.Result;
import com.global.console.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	private RequestsDaoImpl requestsDaoImpl;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.UserService#requestAccess(com.global.console.
	 * dto.ServiceRequest)
	 */
	@Override
	public Result requestAccess(ServiceRequest serviceRequest) {
		UUID id = requestsDaoImpl.createServiceRequest(serviceRequest);
		Result result = new Result();
		result.setResponseMsg(id.toString());
		result.setResponseCode(HttpStatus.OK);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.UserService#viewAccess(java.lang.String)
	 */
	@Override
	public User viewAccess(String userId) {
		User user = userRepository.findById(userId);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.UserService#viewServices()
	 */
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

}
