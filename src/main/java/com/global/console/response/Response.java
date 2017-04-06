package com.global.console.response;

import java.util.List;

import org.springframework.http.HttpStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class Response.
 *
 * @param <T>
 *            the generic type
 */
public class Response<T> {

	/** The object. */
	private List<T> results;

	/** The http status. */
	private HttpStatus httpStatus;

	/** The message. */
	private String message;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 *
	 * @param httpStatus
	 *            the new http status
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<T> getResults() {
		return results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<T> results) {
		this.results = results;
	}

}
