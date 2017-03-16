package com.global.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.global.dto.WebServiceRequests;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestRepository.
 */
public interface RequestRepository extends CrudRepository<WebServiceRequests, String>{

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<WebServiceRequests> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the web service requests
	 */
	public WebServiceRequests findById(UUID id);
}
