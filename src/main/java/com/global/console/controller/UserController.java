package com.global.console.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.ServiceRequest;
import com.global.console.response.Result;
import com.global.console.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class UserController.
 */
@RestController
@Api("User Controller")
@RequestMapping("/api/user")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Request access.
	 *
	 * @param userId the user id
	 * @param serviceName the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "Request Access", notes = "Request Access for a Web Service")
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public ResponseEntity<String> requestAccess(@RequestBody ServiceRequest serviceRequest)
	{
		Result result = userService.requestAccess(serviceRequest);
		return new ResponseEntity<String>(result.getResponseMsg(), result.getResponseCode());
	}

	/**
	 * View access.
	 *
	 * @param userId
	 *            the user id
	 * @return the response entity
	 */
//	@ApiOperation(value = "View Accesses", notes = "View all Web Service accesses")
//	@RequestMapping(value = "/{userId}/view", method = RequestMethod.POST)
//	public ResponseEntity<User> viewAccess(@PathVariable String userId) {
//		User user = userService.viewAccess(userId);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}

	/**
	 * View services.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View all Web Services")
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> viewServices() {
		JSONArray webServices = userService.viewServices();
		return new ResponseEntity<JSONArray>(webServices, HttpStatus.OK);
	}

}
