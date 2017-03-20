package com.global.console.response;

import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;

import com.global.console.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserResponse.
 */
public class UserResponse {

	/** The user. */
	private User user;

	/** The plugins. */
	private JSONArray plugins;

	/** The response code. */
	private HttpStatus responseCode;

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public HttpStatus getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the plugins.
	 *
	 * @return the plugins
	 */
	public JSONArray getPlugins() {
		return plugins;
	}

	/**
	 * Sets the plugins.
	 *
	 * @param plugins
	 *            the new plugins
	 */
	public void setPlugins(JSONArray plugins) {
		this.plugins = plugins;
	}

}
