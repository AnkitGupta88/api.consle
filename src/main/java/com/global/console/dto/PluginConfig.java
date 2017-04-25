package com.global.console.dto;

// TODO: Auto-generated Javadoc
/**
 * The Enum SubscriptionConfig.
 */
public enum PluginConfig {

	/** The rate limiting. */
	RATE_LIMITING("rate-limiting"),

	/** The minute. */
	KEY_AUTH("key-auth"),

	/** The hour. */
	OAUTH("oauth2"),

	/** The day. */
	ACL("acl"),

	/** The jwt. */
	JWT("jwt");

	/** The config type. */
	private String configType;

	/**
	 * Instantiates a new subscription config.
	 *
	 * @param configType
	 *            the config type
	 */
	private PluginConfig(String configType) {
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
	public static PluginConfig getConfigType(String configType) {
		PluginConfig subsConfig = null;
		for (PluginConfig config : PluginConfig.values()) {
			if (configType.equalsIgnoreCase(config.configType)) {
				subsConfig = config;
			}
		}
		return subsConfig;
	}

}
