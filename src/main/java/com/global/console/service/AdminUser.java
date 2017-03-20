package com.global.console.service;

import java.util.List;

import com.global.console.dto.UserDetail;
import com.global.console.model.User;
import com.global.console.response.Result;
import com.global.console.response.UserResponse;

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
	public Result addUser(UserDetail user);
	
	/**
	 * View all users.
	 *
	 * @return the list
	 */
	public List<User> viewAllUsers();
	
	/**
	 * View user.
	 *
	 * @param id the id
	 * @return the user
	 */
	public UserResponse viewUser(String id);
	
}
