package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.dto.UserDetail;
import com.global.console.kong.response.ApiPlugin;
import com.global.console.kong.response.ApiResponse;
import com.global.console.kong.response.ApiUser;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;
import com.global.console.response.Response;
import com.global.console.service.AdminUser;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.MailConfig;
import com.global.console.utils.ServiceUrlBuilderParams;

/**
 * The Class AdminUserImpl.
 */
@Service
public class AdminUserImpl implements AdminUser {

	/** The api config. */
	@Autowired
	private ApiConfiguration apiConfig;

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The user dao impl. */
	@Autowired
	UserDaoImpl userDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#addUser(com.global.console.dto.
	 * UserDetail)
	 */
	@Override
	public Response<String> addUser(UserDetail user) {
		Response<String> finalResponse;
		Map<String, String> params = null;
		JSONObject response = null;
		String url = null;
		try {
			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/";
			params = ServiceUrlBuilderParams.addUserServiceBuilderParams(user);
			response = postRequest(url, params, JSONObject.class);
			String id = response.get(ApiConstants.ID).toString();

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + user.getUserName() + "/"
					+ ApiConstants.KEY_AUTH;
			params = ServiceUrlBuilderParams.addUserKeyServiceBuilderParams(user);
			response = postRequest(url, null, JSONObject.class);
			String key = response.get(ApiConstants.KEY).toString();

			userDaoImpl.addUser(user, id, key);

			finalResponse = new Response<>(Arrays.asList(id), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);

		} catch (Exception e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
		}
		
		if(finalResponse.getResults()!=null)
		{
			MailConfig.send(user.getEmailId(), "Welcome to API CONSOLE", "Thankyou for registering");
		}

		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#viewAllUsers()
	 */
	@Override
	public Response<User> viewAllUsers() {
		Response<User> response = new Response<>(userDaoImpl.findAll(), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#viewUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response<ApiUser> viewUser(String id) {
		Response<ApiUser> response;
		ApiUser user = new ApiUser();
		String url = apiConfig.getAdminUrl() + "/" + ApiConstants.plugins + "?" + ApiConstants.consumer_id + "=" + id;
		ApiResponse<ApiPlugin> requestResponse = null;
		try {
			requestResponse = getRequest(url, null, ApiResponse.class);
			user.setUser(repository.findById(id));
			user.setPlugins(requestResponse.getData());
			response = new Response<>(Arrays.asList(user), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			response = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
		}
		return response;
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

}