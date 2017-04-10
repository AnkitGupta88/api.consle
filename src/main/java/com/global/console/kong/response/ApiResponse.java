package com.global.console.kong.response;

import java.util.List;

/**
 * The Class ApiResponse.
 *
 * @param <T>
 *            the generic type
 */
public class ApiResponse<T> {

	/** The data. */
	private List<T> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

}
