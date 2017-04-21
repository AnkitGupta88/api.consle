package com.global.console.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The Class MailConfiguration.
 */
@Configuration
public class MailConfiguration {

	/** The host. */
	@Value("${email.host}")
	private String HOST;

	/** The user. */
	@Value("${email.user}")
	private String USER;

	/** The password. */
	@Value("${email.password}")
	private String PASSWORD;

	/** The port. */
	@Value("${email.port}")
	private String PORT;

	/** The debug. */
	@Value("${email.debug}")
	private String DEBUG;

	/** The starttls. */
	@Value("${email.starttls}")
	private String STARTTLS;

	/** The socket factory. */
	@Value("${email.socket_factory}")
	private String SOCKET_FACTORY;

	/** The auth. */
	@Value("${email.auth}")
	private String AUTH;

	/** The from. */
	@Value("${email.from}")
	private String FROM;

	/** The fallback. */
	@Value("${email.fallback}")
	private String FALLBACK;

	/**
	 * Gets the fallback.
	 *
	 * @return the fallback
	 */
	public String getFALLBACK() {
		return FALLBACK;
	}

	/**
	 * Gets the auth.
	 *
	 * @return the auth
	 */
	public String getAUTH() {
		return AUTH;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHOST() {
		return HOST;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUSER() {
		return USER;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPASSWORD() {
		return PASSWORD;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public String getPORT() {
		return PORT;
	}

	/**
	 * Gets the debug.
	 *
	 * @return the debug
	 */
	public String getDEBUG() {
		return DEBUG;
	}

	/**
	 * Gets the starttls.
	 *
	 * @return the starttls
	 */
	public String getSTARTTLS() {
		return STARTTLS;
	}

	/**
	 * Gets the socket factory.
	 *
	 * @return the socket factory
	 */
	public String getSOCKET_FACTORY() {
		return SOCKET_FACTORY;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFROM() {
		return FROM;
	}

}
