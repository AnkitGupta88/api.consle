package com.global.console.model;

import java.util.UUID;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Plan.
 */
@Table
public class Plan {

	/** The plan id. */
	@PrimaryKey
	private UUID planId;

	/** The plan name. */
	private String planName;

	/** The created by. */
	private String createdBy;

	/** The config type. */
	private String configType;

	/** The config quantity. */
	private int configQuantity;

	/** The created on. */
	private String createdOn;

	/**
	 * Gets the plan id.
	 *
	 * @return the plan id
	 */
	public UUID getPlanId() {
		return planId;
	}

	/**
	 * Sets the plan id.
	 *
	 * @param planId
	 *            the new plan id
	 */
	public void setPlanId(UUID planId) {
		this.planId = planId;
	}

	/**
	 * Gets the plan name.
	 *
	 * @return the plan name
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * Sets the plan name.
	 *
	 * @param planName
	 *            the new plan name
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the config type.
	 *
	 * @return the config type
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * Sets the config type.
	 *
	 * @param configType
	 *            the new config type
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	/**
	 * Gets the config quantity.
	 *
	 * @return the config quantity
	 */
	public int getConfigQuantity() {
		return configQuantity;
	}

	/**
	 * Sets the config quantity.
	 *
	 * @param configQuantity
	 *            the new config quantity
	 */
	public void setConfigQuantity(int configQuantity) {
		this.configQuantity = configQuantity;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn
	 *            the new created on
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

}
