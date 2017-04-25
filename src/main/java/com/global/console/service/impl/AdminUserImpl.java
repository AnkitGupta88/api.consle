package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.configuration.MailConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.dto.UserDetail;
import com.global.console.kong.response.ApiPlugin;
import com.global.console.kong.response.ApiResponse;
import com.global.console.kong.response.ApiUser;
import com.global.console.kong.response.Consumer;
import com.global.console.kong.response.UserKeyAuth;
import com.global.console.model.User;
import com.global.console.model.WebService;
import com.global.console.repository.UserRepository;
import com.global.console.response.Response;
import com.global.console.service.AdminUser;
import com.global.console.utils.ApiConstants;
import com.global.console.utils.MailConfig;
import com.global.console.utils.ObjectDeserializer;
import com.global.console.utils.ServiceUrlBuilderParams;

// TODO: Auto-generated Javadoc
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

	/** The mail configuration. */
	@Autowired
	private MailConfiguration mailConfiguration;

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

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + user.getLoginId() + "/"
					+ ApiConstants.KEY_AUTH;
			params = ServiceUrlBuilderParams.addUserKeyServiceBuilderParams(user);
			response = postRequest(url, null, JSONObject.class);
			String key = response.get(ApiConstants.KEY).toString();

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + user.getLoginId() + "/"
					+ ApiConstants.JWT;
			response = postRequest(url, null, JSONObject.class);

			userDaoImpl.addUser(user, id, key);

			finalResponse = new Response<>(Arrays.asList(id), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);

		} catch (Exception e) {
			e.printStackTrace();
			finalResponse = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
		}

		if (finalResponse.getResults() != null) {
			MailConfig.getInstance(mailConfiguration).send(user.getEmailId(), "Welcome to API CONSOLE",
					"Thankyou for registering");
		}

		return finalResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#viewAllUsers()
	 */
	@Override
	public Response<Consumer> viewAllUsers() {

		List<User> users = userDaoImpl.findAll();
		List<Consumer> consumers = new ArrayList<>();
		for (User user : users) {
			Consumer consumer = ObjectDeserializer.getObjectMapped(user, Consumer.class);
			Map<String, List<ApiPlugin>> webServicePlugin = new HashMap<>();
			Map<String, String> webUrl = new HashMap<>();
			if (user.getWebServices() != null) {
				for (WebService webService : user.getWebServices()) {
					String url = apiConfig.getAdminUrl() + "/apis/" + webService.getName() + "/plugins?consumer_id="
							+ user.getId();
					ApiResponse<ApiPlugin> response = null;
					try {
						response = getRequest(url, null, new ParameterizedTypeReference<ApiResponse<ApiPlugin>>() {
						});
						webServicePlugin.put(webService.getName(), response.getData());
						webUrl.put(webService.getName(), webService.getUrl());
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				consumer.setWebUrl(webUrl);
				consumer.setWebService(webServicePlugin);
			}
			consumers.add(consumer);
		}

		Response<Consumer> response = new Response<>(consumers, HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#userKey(java.lang.String)
	 */
	@Override
	public Response<String> userKey(String userId) {
		Response<String> finalResponse;
		String url = null;
		JSONObject response = null;
		ApiResponse<UserKeyAuth> keyResponse = null;
		try {
			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + userId + "/" + ApiConstants.KEY_AUTH;
			keyResponse = getRequest(url, null, new ParameterizedTypeReference<ApiResponse<UserKeyAuth>>() {
			});

			for (UserKeyAuth key : keyResponse.getData()) {
				String id = key.getId();
				url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + userId + "/"
						+ ApiConstants.KEY_AUTH + "/" + id;
				deleteRequest(url);
			}

			url = apiConfig.getAdminUrl() + "/" + ApiConstants.CONSUMERS + "/" + userId + "/" + ApiConstants.KEY_AUTH;
			response = postRequest(url, null, JSONObject.class);
			String key = response.get(ApiConstants.KEY).toString();
			userDaoImpl.updateUserKey(userId, key);
			finalResponse = new Response<>(Arrays.asList(key), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);
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

	/**
	 * Gets the request.
	 *
	 * @param <T>
	 *            the generic type
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param parameterizedTypeReference
	 *            the parameterized type reference
	 * @return the request
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	private <T> ApiResponse<T> getRequest(String url, Object params,
			ParameterizedTypeReference<ApiResponse<T>> parameterizedTypeReference) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(url);
		return restTemplate.exchange(uri, HttpMethod.GET, null, parameterizedTypeReference).getBody();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#editUser(java.lang.String,
	 * com.global.console.dto.UserDetail)
	 */
	@Override
	public Response<User> editUser(String userId, UserDetail user) {
		Response<User> response;

		try {
			response = new Response<>(Arrays.asList(userDaoImpl.editUser(user, userId)), HttpStatus.OK,
					ApiConstants.REQUEST_COMPLETED);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminUser#deleteUser(java.lang.String)
	 */
	@Override
	public Response<String> deleteUser(String userId) {
		Response<String> response;

		try {
			String url = apiConfig.getAdminUrl() + "/consumers/" + userId;
			deleteRequest(url);
			userDaoImpl.deleteUser(userId);

			response = new Response<>(Arrays.asList(userId), HttpStatus.OK, ApiConstants.REQUEST_COMPLETED);

		} catch (Exception e) {
			e.printStackTrace();
			response = new Response<>(HttpStatus.BAD_REQUEST, ApiConstants.REQUEST_ERROR);
		}
		return response;
	}

}