package com.global.console.dto;

// TODO: Auto-generated Javadoc
/**
 * The Enum SubscriptionConfig.
 */
public enum SubscriptionConfig {

	/** The second. */
	SECOND("second"),

	/** The minute. */
	MINUTE("minute"),

	/** The hour. */
	HOUR("hour"),

	/** The day. */
	DAY("day");

	/** The config type. */
	private String configType;

	/**
	 * Instantiates a new subscription config.
	 *
	 * @param configType
	 *            the config type
	 */
	private SubscriptionConfig(String configType) {
		this.configType = configType;
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
	 * Gets the config type.
	 *
	 * @param configType
	 *            the config type
	 * @return the config type
	 */
	public static SubscriptionConfig getConfigType(String configType) {
		SubscriptionConfig subsConfig = null;
		for (SubscriptionConfig config : SubscriptionConfig.values()) {
			if (configType.equalsIgnoreCase(config.configType)) {
				subsConfig = config;
			}
		}
		return subsConfig;
	}

}
