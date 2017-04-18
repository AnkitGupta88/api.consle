package com.global.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.ServiceRequest;
import com.global.console.response.Response;
import com.global.console.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Auto-generated Javadoc
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
	 * @param serviceRequest
	 *            the service request
	 * @return the response entity
	 */
	@ApiOperation(value = "Request Access", notes = "Request Access for a Web Service")
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> requestAccess(@RequestBody ServiceRequest serviceRequest) {
		Response<String> result = userService.requestAccess(serviceRequest);
		return new ResponseEntity<>(result, result.getHttpStatus());
	}

	/**
	 * View services.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View all Web Services")
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> viewServices() {
		Response<Object> webServices = userService.viewServices();
		return new ResponseEntity<>(webServices, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Token", notes = "Get JWT Token")
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity<Response<String>> getToken(@RequestParam String userId) {
		Response<String> token = userService.getToken(userId);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

}
