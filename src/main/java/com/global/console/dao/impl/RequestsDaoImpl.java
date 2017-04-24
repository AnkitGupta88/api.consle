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

// TODO: Auto-generated Javadoc
/**
 * The Class RequestsDaoImpl.
 */
@Repository
public class RequestsDaoImpl implements RequestDao {

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	/**
	 * Instantiates a new requests dao impl.
	 */
	public RequestsDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.dao.RequestDao#createServiceRequest(com.global.console
	 * .dto.ServiceRequest)
	 */
	@Override
	public UUID createServiceRequest(ServiceRequest request) {
		WebServiceRequests webServiceRequest = ObjectDeserializer.getObjectMapped(request, WebServiceRequests.class);
		webServiceRequest.setId(UUIDs.timeBased());
		webServiceRequest.setStatus("Pending");
		requestRepository.save(webServiceRequest);
		return webServiceRequest.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.RequestDao#viewServiceRequests()
	 */
	@Override
	public List<WebServiceRequests> viewServiceRequests() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.dao.RequestDao#grantServiceRequest(java.lang.String)
	 */
	@Override
	public void grantServiceRequest(String id) {

	}

}
