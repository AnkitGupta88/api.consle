package com.global.console.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The Class PropertiesConfiguration.
 */
@Configuration
public class PropertiesConfiguration {

	/** The kongadmin. */
	@Value("${spring.data.kong.admin}")
	private String KONGADMIN;
	
	/** The konguser. */
	@Value("${spring.data.kong.user}")
	private String KONGUSER;

	/**
	 * Gets the kongadmin.
	 *
	 * @return the kongadmin
	 */
	public String getKONGADMIN() {
		return KONGADMIN;
	}

	/**
	 * Gets the konguser.
	 *
	 * @return the konguser
	 */
	public String getKONGUSER() {
		return KONGUSER;
	}

}
