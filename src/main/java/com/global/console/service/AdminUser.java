package com.global.console.service;

import com.global.console.dto.UserDetail;
import com.global.console.model.User;
import com.global.console.response.Response;

/**
 * The Interface AdminService.
 */
public interface AdminUser {

	/**
	 * Adds the user.
	 *
	 * @param userParams the user params
	 * @return the string
	 */
	public Response<String> addUser(UserDetail user);
	
	/**
	 * View all users.
	 *
	 * @return the list
	 */
	public Response<User> viewAllUsers();
	
	/**
	 * View user.
	 *
	 * @param id the id
	 * @return the user
	 */
	public Response<Object> viewUser(String id);
	
}
