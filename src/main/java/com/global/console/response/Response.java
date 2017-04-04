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
	private List<T> object;

	/** The http status. */
	private HttpStatus httpStatus;

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
	 * Gets the object.
	 *
	 * @return the object
	 */
	public List<T> getObject() {
		return object;
	}

	/**
	 * Sets the object.
	 *
	 * @param object
	 *            the new object
	 */
	public void setObject(List<T> object) {
		this.object = object;
	}

}
