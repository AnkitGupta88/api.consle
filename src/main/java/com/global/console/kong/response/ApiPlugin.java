package com.global.console.kong.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.console.dto.PluginConfig;
import com.global.console.utils.DateConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiPlugin.
 */
public class ApiPlugin {

	/** The api id. */
	private String api_id;

	/** The id. */
	private String id;

	/** The created at. */
	private String created_at;

	/** The enabled. */
	private String enabled;

	/** The name. */
	private String name;

	/** The consumer id. */
	private String consumer_id;

	/** The config. */
	@JsonIgnoreProperties(ignoreUnknown = true)
	private ApiConfig config;

	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public ApiConfig getConfig() {
		return config;
	}

	/**
	 * Sets the config.
	 *
	 * @param config
	 *            the new config
	 */
	public void setConfig(ApiConfig config) {
		this.config = config;
	}

	/**
	 * Gets the api id.
	 *
	 * @return the api id
	 */
	public String getApi_id() {
		return api_id;
	}

	/**
	 * Sets the api id.
	 *
	 * @param api_id
	 *            the new api id
	 */
	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * Sets the created at.
	 *
	 * @param created_at
	 *            the new created at
	 */
	public void setCreated_at(String created_at) {
		this.created_at = DateConverter.getDateFromTimestamp(created_at);
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public String getEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

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
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = PluginConfig.getConfigType(name).toString();
	}

	/**
	 * Gets the consumer id.
	 *
	 * @return the consumer id
	 */
	public String getConsumer_id() {
		return consumer_id;
	}

	/**
	 * Sets the consumer id.
	 *
	 * @param consumer_id
	 *            the new consumer id
	 */
	public void setConsumer_id(String consumer_id) {
		this.consumer_id = consumer_id;
	}

}
