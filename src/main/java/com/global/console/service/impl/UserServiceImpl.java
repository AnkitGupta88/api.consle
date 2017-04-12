package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.RequestsDaoImpl;
import com.global.console.dto.ServiceRequest;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;
import com.global.console.response.Response;
import com.global.console.service.UserService;
import com.global.console.utils.ApiConstants;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	private RequestsDaoImpl requestsDaoImpl;

	@Autowired
	private ApiConfiguration apiConfig;

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
	public Response<String> requestAccess(ServiceRequest serviceRequest) {
		UUID id = requestsDaoImpl.createServiceRequest(serviceRequest);
		Response<String> response = new Response<>(Arrays.asList(id.toString()), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.UserService#viewAccess(java.lang.String)
	 */
	@Override
	public Response<User> viewAccess(String userId) {
		Response<User> response = new Response<>(Arrays.asList(userRepository.findById(userId)), HttpStatus.OK,
				ApiConstants.REQUEST_COMPLETED);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.UserService#viewServices()
	 */
	@Override
	public Response<Object> viewServices() {
		Response<Object> finalResponse;
		String url = null;
		String response = null;
		url = apiConfig.getAdminUrl() + "/apis";
		try {
			response = getRequest(url, null, String.class);
			JSONObject json = (JSONObject) JSONValue.parse(response);
			finalResponse = new Response<>(Arrays.asList(json.get("data")), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
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

}
