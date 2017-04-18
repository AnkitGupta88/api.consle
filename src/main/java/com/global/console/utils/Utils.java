package com.global.console.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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
	 * @param <S>
	 *            the generic type
	 * @param <T>
	 *            the generic type
	 * @param inputObject
	 *            the input object
	 * @param outputObject
	 *            the output object
	 * @return the object mapped
	 */
	public static <T> T getObjectMapped(Object inputObject, Class<T> outputObject) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inputObject, outputObject);
	}

	/**
	 * Gets the date from timestamp.
	 *
	 * @param timestamp
	 *            the timestamp
	 * @return the date from timestamp
	 */
	public static String getDateFromTimestamp(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(new Date((long)Long.parseLong(timestamp)*1000));
	}
	
	public static String getToken(String secret, String key) throws IllegalArgumentException, UnsupportedEncodingException
	{
		Algorithm algorithm = Algorithm.HMAC256(secret);
	    String token = JWT.create()
	        .withIssuer(key)
	        .sign(algorithm);
	    return token;
	}
}
