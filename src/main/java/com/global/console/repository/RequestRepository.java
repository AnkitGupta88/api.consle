package com.global.console.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.global.console.model.WebServiceRequests;

/**
 * The Interface RequestRepository.
 */
public interface RequestRepository extends CrudRepository<WebServiceRequests, String>{

	@Override
	public List<WebServiceRequests> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the web service requests
	 */
	public WebServiceRequests findById(UUID id);
}
