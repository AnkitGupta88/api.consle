/**
 * 
 */
package com.global.console.dao;

import java.util.List;
import java.util.UUID;

import com.global.console.dto.ServiceRequest;
import com.global.console.model.WebServiceRequests;

/**
 * @author ankit.gupta4
 *
 */
public interface RequestsDao {
	
	public UUID createServiceRequest(ServiceRequest request);
	
	public void grantServiceRequest(String id);

	public List<WebServiceRequests> viewServiceRequests();
}
