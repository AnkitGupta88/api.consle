package com.global.console.utils;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * The Class JwtTokenGenerator.
 */
public class JwtTokenGenerator {

	/**
	 * Gets the token.
	 *
	 * @param secret
	 *            the secret
	 * @param key
	 *            the key
	 * @return the token
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public static String getToken(String secret, String key)
			throws IllegalArgumentException, UnsupportedEncodingException {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		String token = JWT.create().withIssuer(key).sign(algorithm);
		return token;
	}
}
