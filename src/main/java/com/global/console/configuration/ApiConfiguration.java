/**
 * 
 */
package com.global.console.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiConfiguration.
 *
 * @author ankit.gupta4
 */
@Configuration
public class ApiConfiguration {

	/** The admin host. */
	@Value("${kong.admin.host}")
	private String adminHost;

	/** The admin port. */
	@Value("${kong.admin.port}")
	private String adminPort;
	
	@Value("${kong.user.host}")
	private String userHost;

	/** The admin port. */
	@Value("${kong.user.port}")
	private String userPort;

	/**
	 * Gets the adminhost.
	 *
	 * @return the adminhost
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

	public String getUserHost() {
		return userHost;
	}

	public String getUserPort() {
		return userPort;
	}

	public String getUserUrl() {
		return "http://" + userHost + ":" + userPort;
	}

	
}