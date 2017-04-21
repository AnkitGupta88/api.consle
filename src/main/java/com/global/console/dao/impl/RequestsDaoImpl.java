package com.global.console.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.utils.UUIDs;
import com.global.console.dao.RequestDao;
import com.global.console.dto.ServiceRequest;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.RequestRepository;
import com.global.console.utils.ObjectDeserializer;

@Repository
public class RequestsDaoImpl implements RequestDao{

	/** The user repository. */
	@Autowired
	private RequestRepository requestRepository;

	
	public RequestsDaoImpl() {
	}


	@Override
	public UUID createServiceRequest(ServiceRequest request) {
		WebServiceRequests webServiceRequest = ObjectDeserializer.getObjectMapped(request, WebServiceRequests.class);
		webServiceRequest.setId(UUIDs.timeBased());
		webServiceRequest.setStatus("Pending");
		requestRepository.save(webServiceRequest);
		return webServiceRequest.getId();
	}


	@Override
	public List<WebServiceRequests> viewServiceRequests() {
		return null;
	}


	@Override
	public void grantServiceRequest(String id) {
		
	}


}
