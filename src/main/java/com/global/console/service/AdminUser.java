package com.global.console.service;

import com.global.console.dto.UserDetail;
import com.global.console.kong.response.ApiUser;
import com.global.console.model.User;
import com.global.console.response.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminUser.
 */
public interface AdminUser {

	/**
	 * Adds the user.
	 *
	 * @param user
	 *            the user
	 * @return the response
	 */
	public Response<String> addUser(UserDetail user);

	/**
	 * View all users.
	 *
	 * @return the response
	 */
	public Response<User> viewAllUsers();

	/**
	 * View user.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	public Response<ApiUser> viewUser(String id);

	/**
	 * User key.
	 *
	 * @param userId
	 *            the user id
	 * @return the response
	 */
	public Response<String> userKey(String userId);

	/**
	 * Edits the user.
	 *
	 * @param userId
	 *            the user id
	 * @param user
	 *            the user
	 * @return the response
	 */
	public Response<User> editUser(String userId, UserDetail user);

	/**
	 * Delete user.
	 *
	 * @param userId
	 *            the user id
	 * @return the response
	 */
	public Response<String> deleteUser(String userId);

}
