package com.global.console.response;

import org.springframework.http.HttpStatus;

/**
 * The Class Result.
 */
public class Result {

	/** The response code. */
	private HttpStatus responseCode;

	/** The response msg. */
	private String responseMsg;

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public HttpStatus getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode
	 *            the new response code
	 */
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the response msg.
	 *
	 * @return the response msg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * Sets the response msg.
	 *
	 * @param responseMsg
	 *            the new response msg
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}
