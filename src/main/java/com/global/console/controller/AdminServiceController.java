package com.global.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.ServiceRegister;
import com.global.console.kong.response.ApiPlugin;
import com.global.console.kong.response.ApiService;
import com.global.console.response.Response;
import com.global.console.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminServiceController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin/services")
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
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Response<ApiService>> viewServices() {
		Response<ApiService> webServices = adminService.viewServices();
		return new ResponseEntity<>(webServices, webServices.getHttpStatus());
	}

	/**
	 * View service.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Services", notes = "View a particular Web Service")
	@RequestMapping(value = "/{serviceName}", method = RequestMethod.GET)
	public ResponseEntity<Response<ApiService>> viewService(@PathVariable String serviceName) {
		Response<ApiService> webService = adminService.viewService(serviceName);
		return new ResponseEntity<>(webService, webService.getHttpStatus());
	}

	/**
	 * View plugins.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "View Web Service Plugins", notes = "View Web Service plugins")
	@RequestMapping(value = "/{serviceName}/plugins", method = RequestMethod.GET)
	public ResponseEntity<Response<ApiPlugin>> viewPlugins(@PathVariable String serviceName) {
		Response<ApiPlugin> plugins = adminService.viewPlugins(serviceName);
		return new ResponseEntity<>(plugins, plugins.getHttpStatus());
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
	@RequestMapping(value = "/{serviceName}/plugins/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> deletePlugins(@PathVariable String serviceName, @RequestParam String id) {
		Response<String> plugins = adminService.deletePlugins(serviceName, id);
		return new ResponseEntity<>(plugins, plugins.getHttpStatus());
	}

	/**
	 * Delete service.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Web Services", notes = "Delete a Web Services")
	@RequestMapping(value = "/{serviceName}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> deleteService(@PathVariable String serviceName) {
		Response<String> webServiceName = adminService.deleteService(serviceName);
		return new ResponseEntity<>(webServiceName, webServiceName.getHttpStatus());
	}

	/**
	 * Register service.
	 *
	 * @param serviceDetail
	 *            the service detail
	 * @return the response entity
	 */
	@ApiOperation(value = "Register API", notes = "Register new API")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response<ApiService>> registerService(@RequestBody ServiceRegister serviceDetail) {
		Response<ApiService> response = adminService.registerService(serviceDetail);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

}
