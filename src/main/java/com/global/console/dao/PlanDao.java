/**
 * 
 */
package com.global.console.dao;

import java.util.List;
import java.util.UUID;

import com.global.console.dto.PlanDetails;
import com.global.console.model.Plan;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlanDao.
 */
public interface PlanDao {

	/**
	 * Creates the plan.
	 *
	 * @param planDetails
	 *            the plan details
	 * @param id
	 *            the id
	 * @return the plan
	 */
	public Plan createPlan(PlanDetails planDetails, UUID id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Plan> findAll();

	/**
	 * Edits the plan.
	 *
	 * @param planId
	 *            the plan id
	 * @param planDetails
	 *            the plan details
	 * @return the plan
	 */
	public Plan editPlan(String planId, PlanDetails planDetails);

	/**
	 * Delete plan.
	 *
	 * @param planId
	 *            the plan id
	 * @return the uuid
	 */
	public UUID deletePlan(String planId);
}
