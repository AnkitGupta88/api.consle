package com.global.console.kong.response;

import com.global.console.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiService.
 */
public class ApiService {

	/** The strip request path. */
	private String strip_request_path;

	/** The upstream url. */
	private String upstream_url;

	/** The name. */
	private String name;

	/** The request path. */
	private String request_path;

	/** The created at. */
	private String created_at;

	/** The preserve host. */
	private String preserve_host;

	/** The id. */
	private String id;

	/**
	 * Gets the strip request path.
	 *
	 * @return the strip request path
	 */
	public String getStrip_request_path() {
		return strip_request_path;
	}

	/**
	 * Sets the strip request path.
	 *
	 * @param strip_request_path
	 *            the new strip request path
	 */
	public void setStrip_request_path(String strip_request_path) {
		this.strip_request_path = strip_request_path;
	}

	/**
	 * Gets the upstream url.
	 *
	 * @return the upstream url
	 */
	public String getUpstream_url() {
		return upstream_url;
	}

	/**
	 * Sets the upstream url.
	 *
	 * @param upstream_url
	 *            the new upstream url
	 */
	public void setUpstream_url(String upstream_url) {
		this.upstream_url = upstream_url;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the request path.
	 *
	 * @return the request path
	 */
	public String getRequest_path() {
		return request_path;
	}

	/**
	 * Sets the request path.
	 *
	 * @param request_path
	 *            the new request path
	 */
	public void setRequest_path(String request_path) {
		this.request_path = request_path;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * Sets the created at.
	 *
	 * @param created_at
	 *            the new created at
	 */
	public void setCreated_at(String created_at) {
		this.created_at = Utils.getDateFromTimestamp(created_at);
	}

	/**
	 * Gets the preserve host.
	 *
	 * @return the preserve host
	 */
	public String getPreserve_host() {
		return preserve_host;
	}

	/**
	 * Sets the preserve host.
	 *
	 * @param preserve_host
	 *            the new preserve host
	 */
	public void setPreserve_host(String preserve_host) {
		this.preserve_host = preserve_host;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
