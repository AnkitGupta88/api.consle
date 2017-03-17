package com.global.console.model;

import org.springframework.data.cassandra.mapping.UserDefinedType;

/**
 * The Class WebService.
 */
@UserDefinedType(value = "WebService")
public class WebService {

	/** The name. */
	private String name;
	
	/** The url. */
	private String url;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
