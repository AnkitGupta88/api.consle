package com.global.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.model.WebServiceRequests;
import com.global.console.response.Response;
import com.global.console.service.AdminRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin")
public class AdminRequestController {

	/** The admin service. */
	@Autowired
	private AdminRequest adminService;

	/**
	 * Grant access.
	 *
	 * @param inputParams
	 *            the input params
	 * @return the response entity
	 */
	@ApiOperation(value = "Grant Access", notes = "Grant access to a Web Services")
	@RequestMapping(value = "/request/grant", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> grantAccess(@RequestParam String requestId) {
		Response<String> response = adminService.grantService(requestId);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * View requests.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Requests", notes = "View All Requests")
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<Response<WebServiceRequests>> viewRequests() {
		Response<WebServiceRequests> response = adminService.viewRequests();
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

}
