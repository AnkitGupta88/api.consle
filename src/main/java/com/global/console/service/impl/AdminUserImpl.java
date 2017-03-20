package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.dto.UserDetail;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;
import com.global.console.response.Result;
import com.global.console.response.UserResponse;
import com.global.console.service.AdminUser;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.ServiceUrlBuilderParams;

/**
 * The Class AdminServiceImpl.
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

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminUser#addUser(com.global.console.dto.UserDetail)
	 */
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

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + user.getUserName() + "/"
					+ ApiConstants.KEY_AUTH;
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

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminUser#viewAllUsers()
	 */
	@Override
	public List<User> viewAllUsers() {
		return userDaoImpl.findAll();
	}

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminUser#viewUser(java.lang.String)
	 */
	@Override
	public UserResponse viewUser(String id) {
		UserResponse result = new UserResponse();
		result.setUser(repository.findById(id));
		String response = null;
		String url = apiConfig.getAdminUrl() + "/" + ApiConstants.plugins + "?" + ApiConstants.consumer_id + "=" + id;
		try {
			response = getRequest(url, null, String.class);

			result.setPlugins((JSONArray) ((JSONObject) JSONValue.parse(response)).get("data"));
			result.setResponseCode(HttpStatus.OK);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// JSONObject json = (JSONObject) JSONValue.parse(response);
		// userDetails.put("Plugins", json.get("data"));
		// List<RateLimiting_Metrics> rateLimiting_metrics =
		// rateLimitingRepository.findByIdentifier(id);
		// userDetails.put("Rate Limiting", rateLimiting_metrics);
		return result;
	}

	/**
	 * Gets the input params class.
	 *
	 * @param <T>            the generic type
	 * @param url the url
	 * @param params the params
	 * @param t            the t
	 * @return the input params class
	 * @throws URISyntaxException the URI syntax exception
	 */

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

}