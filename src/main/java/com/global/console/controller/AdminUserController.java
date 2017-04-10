package com.global.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.UserDetail;
import com.global.console.kong.response.ApiUser;
import com.global.console.model.User;
import com.global.console.response.Response;
import com.global.console.service.AdminUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin/users")
public class AdminUserController {

	/** The admin service. */
	@Autowired
	private AdminUser adminService;

	/**
	 * Adds the user.
	 *
	 * @param userParams
	 *            the user params
	 * @return the response entity
	 */
	@ApiOperation(value = "Add User", notes = "Add a new user")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> addUser(@RequestBody UserDetail user) {
		Response<String> result = adminService.addUser(user);
		return new ResponseEntity<>(result, result.getHttpStatus());
	}

	/**
	 * View all users.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Users", notes = "View All Users")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Response<User>> viewAllUsers() {
		Response<User> users = adminService.viewAllUsers();
		return new ResponseEntity<>(users, users.getHttpStatus());
	}

	/**
	 * View user.
	 *
	 * @param userId
	 *            the user id
	 * @return the response entity
	 */
	@ApiOperation(value = "View Particular User", notes = "View a particular user")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Response<ApiUser>> viewUser(@PathVariable String userId) {
		Response<ApiUser> result = adminService.viewUser(userId);
		return new ResponseEntity<>(result, result.getHttpStatus());
	}

}
