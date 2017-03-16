package com.global.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.dto.User;
import com.global.dto.WebServiceRequests;
import com.global.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin")
public class AdminController {

	/** The admin service. */
	@Autowired
	private AdminService adminService;
	
	/**
	 * Grant access.
	 *
	 * @param inputParams the input params
	 * @return the response entity
	 */
	@ApiOperation(value = "Grant Access", notes = "Grant access to a Web Services")
	@RequestMapping(value = "/request/grant", method = RequestMethod.POST)
	public ResponseEntity<String> grantAccess(@RequestParam String inputParams)
	{
		String response = adminService.grantService(inputParams);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/**
	 * View requests.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Requests", notes = "View All Requests")
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<List<WebServiceRequests>> viewRequests()
	{
		List<WebServiceRequests> webServiceRequests = adminService.viewRequests();
		return new ResponseEntity<List<WebServiceRequests>>(webServiceRequests, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View all Web Services")
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> viewServices()
	{
		JSONArray webServices = adminService.viewServices();
		return new ResponseEntity<JSONArray>(webServices, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View a particular Web Service")
	@RequestMapping(value = "/services/{serviceName}", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> viewService(@PathVariable String serviceName)
	{
		JSONObject webService = adminService.viewService(serviceName);
		return new ResponseEntity<JSONObject>(webService, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Service Plugins", notes = "View Web Service plugins")
	@RequestMapping(value = "/services/{serviceName}/plugins", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> viewPlugins(@PathVariable String serviceName)
	{
		JSONArray plugins = adminService.viewPlugins(serviceName);
		return new ResponseEntity<JSONArray>(plugins, HttpStatus.OK);
	}

	/**
	 * Delete plugins.
	 *
	 * @param serviceName the service name
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Web Service Plugin", notes = "Delet Web Service plugin")
	@RequestMapping(value = "/services/{serviceName}/plugins/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlugins(@PathVariable String serviceName, @RequestParam String id)
	{
		String plugins = adminService.deletePlugins(serviceName, id);
		return new ResponseEntity<String>(plugins, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Web Services", notes = "Delete a Web Services")
	@RequestMapping(value = "/services/{serviceName}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteService(@PathVariable String serviceName)
	{
		String webServiceName = adminService.deleteService(serviceName);
		return new ResponseEntity<String>(webServiceName, HttpStatus.OK);
	}

	/**
	 * Adds the service.
	 *
	 * @param inputParams the input params
	 * @return the response entity
	 */
	@ApiOperation(value = "Add Web Service", notes = "Add a new Web Service")
	@RequestMapping(value = "/services/add", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> addService(@RequestParam String inputParams)
	{
		JSONObject response = adminService.addService(inputParams);
		return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
	}


	/**
	 * Adds the user.
	 *
	 * @param userParams the user params
	 * @return the response entity
	 */
	@ApiOperation(value = "Add User", notes = "Add a new user")
	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam String userParams)
	{
		String id = adminService.addUser(userParams);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	/**
	 * View all users.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Users", notes = "View All Users")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<List<User>> viewAllUsers()
	{
		List<User> users = adminService.viewAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * View user.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@ApiOperation(value = "View Particular User", notes = "View a particular user")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> viewUser(@PathVariable String userId)
	{
		JSONObject user = adminService.viewUser(userId);
		return new ResponseEntity<JSONObject>(user, HttpStatus.OK);
	}

}
