package com.global.console.service;

import com.global.console.dto.PlanDetails;
import com.global.console.model.Plan;
import com.global.console.response.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminPlan.
 */
public interface AdminPlan {

	/**
	 * Creates the plan.
	 *
	 * @param planDetails
	 *            the plan details
	 * @return the response
	 */
	public Response<Plan> createPlan(PlanDetails planDetails);

	/**
	 * View all plans.
	 *
	 * @return the response
	 */
	public Response<Plan> viewAllPlans();

	/**
	 * Edits the plan.
	 *
	 * @param planName
	 *            the plan name
	 * @param planDetails
	 *            the plan details
	 * @return the response
	 */
	public Response<Plan> editPlan(String planId, PlanDetails planDetails);

	/**
	 * Delete plan.
	 *
	 * @param planName
	 *            the plan name
	 * @return the response
	 */
	public Response<String> deletePlan(String planId);

}
