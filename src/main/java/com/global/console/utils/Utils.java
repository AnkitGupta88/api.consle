package com.global.console.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {

	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * Gets the object mapped.
	 *
	 * @param <S> the generic type
	 * @param <T> the generic type
	 * @param inputObject the input object
	 * @param outputObject the output object
	 * @return the object mapped
	 */
	public static <T> T getObjectMapped(Object inputObject, Class<T> outputObject) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inputObject, outputObject);
	}

}
