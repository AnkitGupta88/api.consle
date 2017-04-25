package com.global.console.kong.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiConfig.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiConfig {

	/** The key names. */
	private List<String> key_names;

	/** The limit by. */
	private String limit_by;

	/** The fault tolerant. */
	private String fault_tolerant;

	/** The redis timeout. */
	private String redis_timeout;

	/** The policy. */
	private String policy;

	/** The second. */
	private String second;

	/** The minute. */
	private String minute;

	/** The hour. */
	private String hour;

	/** The day. */
	private String day;

	/** The redis port. */
	private String redis_port;

	/** The whitelist. */
	private List<String> whitelist;

	private List<String> uri_param_names;
	
	private String secret_is_base64;
	
	private String key_claim_name;
	
	public List<String> getUri_param_names() {
		return uri_param_names;
	}

	public void setUri_param_names(List<String> uri_param_names) {
		this.uri_param_names = uri_param_names;
	}

	public String getSecret_is_base64() {
		return secret_is_base64;
	}

	public void setSecret_is_base64(String secret_is_base64) {
		this.secret_is_base64 = secret_is_base64;
	}

	public String getKey_claim_name() {
		return key_claim_name;
	}

	public void setKey_claim_name(String key_claim_name) {
		this.key_claim_name = key_claim_name;
	}

	/**
	 * Gets the limit by.
	 *
	 * @return the limit by
	 */
	public String getLimit_by() {
		return limit_by;
	}

	/**
	 * Sets the limit by.
	 *
	 * @param limit_by
	 *            the new limit by
	 */
	public void setLimit_by(String limit_by) {
		this.limit_by = limit_by;
	}

	/**
	 * Gets the fault tolerant.
	 *
	 * @return the fault tolerant
	 */
	public String getFault_tolerant() {
		return fault_tolerant;
	}

	/**
	 * Sets the fault tolerant.
	 *
	 * @param fault_tolerant
	 *            the new fault tolerant
	 */
	public void setFault_tolerant(String fault_tolerant) {
		this.fault_tolerant = fault_tolerant;
	}

	/**
	 * Gets the redis timeout.
	 *
	 * @return the redis timeout
	 */
	public String getRedis_timeout() {
		return redis_timeout;
	}

	/**
	 * Sets the redis timeout.
	 *
	 * @param redis_timeout
	 *            the new redis timeout
	 */
	public void setRedis_timeout(String redis_timeout) {
		this.redis_timeout = redis_timeout;
	}

	/**
	 * Gets the policy.
	 *
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * Sets the policy.
	 *
	 * @param policy
	 *            the new policy
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * Gets the second.
	 *
	 * @return the second
	 */
	public String getSecond() {
		return second;
	}

	/**
	 * Sets the second.
	 *
	 * @param second
	 *            the new second
	 */
	public void setSecond(String second) {
		this.second = second;
	}

	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public String getMinute() {
		return minute;
	}

	/**
	 * Sets the minute.
	 *
	 * @param minute
	 *            the new minute
	 */
	public void setMinute(String minute) {
		this.minute = minute;
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * Sets the hour.
	 *
	 * @param hour
	 *            the new hour
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day
	 *            the new day
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * Gets the redis port.
	 *
	 * @return the redis port
	 */
	public String getRedis_port() {
		return redis_port;
	}

	/**
	 * Sets the redis port.
	 *
	 * @param redis_port
	 *            the new redis port
	 */
	public void setRedis_port(String redis_port) {
		this.redis_port = redis_port;
	}

	/**
	 * Gets the key names.
	 *
	 * @return the key names
	 */
	public List<String> getKey_names() {
		return key_names;
	}

	/**
	 * Sets the key names.
	 *
	 * @param key_names
	 *            the new key names
	 */
	public void setKey_names(List<String> key_names) {
		this.key_names = key_names;
	}

	/**
	 * Gets the whitelist.
	 *
	 * @return the whitelist
	 */
	public List<String> getWhitelist() {
		return whitelist;
	}

	/**
	 * Sets the whitelist.
	 *
	 * @param whitelist
	 *            the new whitelist
	 */
	public void setWhitelist(List<String> whitelist) {
		this.whitelist = whitelist;
	}

}
