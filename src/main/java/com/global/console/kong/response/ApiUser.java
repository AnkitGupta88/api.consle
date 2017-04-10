package com.global.console.kong.response;

import java.util.List;

import com.global.console.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiUser.
 */
public class ApiUser {

	/** The user. */
	private User user;

	/** The plugins. */
	private List<ApiPlugin> plugins;

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
	public List<ApiPlugin> getPlugins() {
		return plugins;
	}

	/**
	 * Sets the plugins.
	 *
	 * @param plugins
	 *            the new plugins
	 */
	public void setPlugins(List<ApiPlugin> plugins) {
		this.plugins = plugins;
	}

}
