package com.global.console.model;

import java.util.UUID;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * The Class RateLimiting_Metrics.
 */
@Table
public class RateLimiting_Metrics {

	/** The api id. */
	@PrimaryKey
	private UUID api_id;
	
	/** The identifier. */
	private String identifier;
	
	/** The period date. */
	private String period_date;
	
	/** The period. */
	private String period;
	
	/** The value. */
	private int value;

	/**
	 * Gets the api id.
	 *
	 * @return the api id
	 */
	public UUID getApi_id() {
		return api_id;
	}

	/**
	 * Sets the api id.
	 *
	 * @param api_id the new api id
	 */
	public void setApi_id(UUID api_id) {
		this.api_id = api_id;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param identifier the new identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the period date.
	 *
	 * @return the period date
	 */
	public String getPeriod_date() {
		return period_date;
	}

	/**
	 * Sets the period date.
	 *
	 * @param period_date the new period date
	 */
	public void setPeriod_date(String period_date) {
		this.period_date = period_date;
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * Sets the period.
	 *
	 * @param period the new period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
}
