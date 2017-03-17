package com.global.console.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.ServiceRegister;
import com.global.console.response.Result;
import com.global.console.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin")
public class AdminServiceController {

	/** The admin service. */
	@Autowired
	private AdminService adminService;

	/**
	 * View services.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View all Web Services")
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> viewServices() {
		JSONArray webServices = adminService.viewServices();
		return new ResponseEntity<JSONArray>(webServices, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View a particular Web Service")
	@RequestMapping(value = "/services/{serviceName}", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> viewService(@PathVariable String serviceName) {
		JSONObject webService = adminService.viewService(serviceName);
		return new ResponseEntity<JSONObject>(webService, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Service Plugins", notes = "View Web Service plugins")
	@RequestMapping(value = "/services/{serviceName}/plugins", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> viewPlugins(@PathVariable String serviceName) {
		JSONArray plugins = adminService.viewPlugins(serviceName);
		return new ResponseEntity<JSONArray>(plugins, HttpStatus.OK);
	}

	/**
	 * Delete plugins.
	 *
	 * @param serviceName
	 *            the service name
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Web Service Plugin", notes = "Delet Web Service plugin")
	@RequestMapping(value = "/services/{serviceName}/plugins/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlugins(@PathVariable String serviceName, @RequestParam String id) {
		String plugins = adminService.deletePlugins(serviceName, id);
		return new ResponseEntity<String>(plugins, HttpStatus.OK);
	}

	/**
	 * View services.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Web Services", notes = "Delete a Web Services")
	@RequestMapping(value = "/services/{serviceName}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteService(@PathVariable String serviceName) {
		String webServiceName = adminService.deleteService(serviceName);
		return new ResponseEntity<String>(webServiceName, HttpStatus.OK);
	}

	/**
	 * Adds the service.
	 *
	 * @param inputParams
	 *            the input params
	 * @return the response entity
	 */
	@ApiOperation(value = "Register API", notes = "Register new API")
	@RequestMapping(value = "/services/register", method = RequestMethod.POST)
	public ResponseEntity<Result> registerService(@RequestBody ServiceRegister serviceDetail) {

		Result response = adminService.registerService(serviceDetail);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
