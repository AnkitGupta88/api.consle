/**
 * 
 */
package com.global.console.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiConfiguration.
 */
@Configuration
public class ApiConfiguration {

	/** The admin host. */
	@Value("${kong.admin.host}")
	private String adminHost;

	/** The admin port. */
	@Value("${kong.admin.port}")
	private String adminPort;

	/** The user host. */
	@Value("${kong.user.host}")
	private String userHost;

	/** The user port. */
	@Value("${kong.user.port}")
	private String userPort;

	/**
	 * Gets the admin host.
	 *
	 * @return the admin host
	 */
	public String getAdminHost() {
		return adminHost;
	}

	/**
	 * Gets the admin port.
	 *
	 * @return the admin port
	 */
	public String getAdminPort() {
		return adminPort;
	}

	/**
	 * Gets the admin url.
	 *
	 * @return the admin url
	 */
	public String getAdminUrl() {
		return "http://" + adminHost + ":" + adminPort;
	}

	/**
	 * Gets the user host.
	 *
	 * @return the user host
	 */
	public String getUserHost() {
		return userHost;
	}

	/**
	 * Gets the user port.
	 *
	 * @return the user port
	 */
	public String getUserPort() {
		return userPort;
	}

	/**
	 * Gets the user url.
	 *
	 * @return the user url
	 */
	public String getUserUrl() {
		return "http://" + userHost + ":" + userPort;
	}

}
