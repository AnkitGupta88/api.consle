package com.global.console.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class DateConverter.
 */
public class DateConverter {

	/** The Constant simpleDateFormat. */
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	public static String getCurrentDate() {
		return simpleDateFormat.format(new Date());
	}

	/**
	 * Gets the date from timestamp.
	 *
	 * @param timestamp
	 *            the timestamp
	 * @return the date from timestamp
	 */
	public static String getDateFromTimestamp(String timestamp) {
		return simpleDateFormat.format(new Date((long) Long.parseLong(timestamp) * 1000));
	}

}
