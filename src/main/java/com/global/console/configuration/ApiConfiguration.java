/**
 * 
 */
package com.global.console.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The Class ApiConfiguration.
 *
 * @author ankit.gupta4
 */
@Configuration
public class ApiConfiguration {

	/** The adminhost. */
	@Value("${kong.admin.host}")
	private String adminhost;

	/** The admin port. */
	@Value("${kong.admin.port}")
	private String adminPort;

	/**
	 * Gets the adminhost.
	 *
	 * @return the adminhost
	 */
	public String getAdminhost() {
		return adminhost;
	}

	/**
	 * Gets the admin port.
	 *
	 * @return the admin port
	 */
	public String getAdminPort() {
		return adminPort;
	}
	
}
