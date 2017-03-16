package com.global.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.global.dto.RateLimiting_Metrics;

// TODO: Auto-generated Javadoc
/**
 * The Interface RateLimitingMetricsRepository.
 */
public interface RateLimitingMetricsRepository extends CrudRepository<RateLimiting_Metrics, String>{

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<RateLimiting_Metrics> findAll();

	/**
	 * Find by api id.
	 *
	 * @param api_id the api id
	 * @return the rate limiting metrics
	 */
	public List<RateLimiting_Metrics> findByIdentifier(String identifier);
}
