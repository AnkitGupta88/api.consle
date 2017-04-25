package com.global.console.service;

import com.global.console.dto.ServiceRegister;
import com.global.console.kong.response.ApiPlugin;
import com.global.console.kong.response.ApiService;
import com.global.console.response.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminService.
 */
public interface AdminService {

	/**
	 * View services.
	 *
	 * @return the response
	 */
	public Response<ApiService> viewServices();

	/**
	 * Register service.
	 *
	 * @param service
	 *            the service
	 * @return the response
	 */
	public Response<ApiService> registerService(ServiceRegister service);

	/**
	 * Delete service.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response
	 */
	public Response<String> deleteService(String serviceName);

	/**
	 * View service.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response
	 */
	public Response<ApiService> viewService(String serviceName);

	/**
	 * Delete plugins.
	 *
	 * @param serviceName
	 *            the service name
	 * @param id
	 *            the id
	 * @return the response
	 */
	public Response<String> deletePlugins(String serviceName, String id);

	/**
	 * View plugins.
	 *
	 * @param serviceName
	 *            the service name
	 * @return the response
	 */
	public Response<ApiPlugin> viewPlugins(String serviceName);
}
