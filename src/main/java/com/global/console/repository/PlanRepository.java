package com.global.console.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.global.console.model.Plan;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlanRepository.
 */
public interface PlanRepository extends CrudRepository<Plan, String> {

	/**
	 * Find by plan name.
	 *
	 * @param planName
	 *            the plan name
	 * @return the plan
	 */
	public Plan findByPlanName(String planName);

	/**
	 * Find by plan id.
	 *
	 * @param planId
	 *            the plan id
	 * @return the plan
	 */
	public Plan findByPlanId(UUID planId);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<Plan> findAll();

}
