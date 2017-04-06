package com.global.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.global.console.dto.PlanDetails;
import com.global.console.model.Plan;
import com.global.console.response.Response;
import com.global.console.service.impl.AdminPlanImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class AdminPlanController.
 */
@RestController
@Api("Admin Controller")
@RequestMapping("/api/admin/plans")
public class AdminPlanController {

	/** The admin plan impl. */
	@Autowired
	private AdminPlanImpl adminPlanImpl;

	/**
	 * Creates the plan.
	 *
	 * @param planDetails
	 *            the plan details
	 * @return the response entity
	 */
	@ApiOperation(value = "Create Plan", notes = "Create a new plan")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Response<Plan>> createPlan(@RequestBody PlanDetails planDetails) {
		Response<Plan> response = adminPlanImpl.createPlan(planDetails);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * View plans.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "View Plans", notes = "View all the plans")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Response<Plan>> viewPlans() {
		Response<Plan> plans = adminPlanImpl.viewAllPlans();
		return new ResponseEntity<>(plans, plans.getHttpStatus());
	}

	/**
	 * Edits the plan.
	 *
	 * @param planName
	 *            the plan name
	 * @param planDetails
	 *            the plan details
	 * @return the response entity
	 */
	@ApiOperation(value = "Edit Plan", notes = "Edit a plan")
	@RequestMapping(value = "/{planId}/edit", method = RequestMethod.POST)
	public ResponseEntity<Response<Plan>> editPlan(@PathVariable String planId, @RequestBody PlanDetails planDetails) {
		Response<Plan> result = adminPlanImpl.editPlan(planId, planDetails);
		return new ResponseEntity<>(result, result.getHttpStatus());
	}

	/**
	 * Adds the user.
	 *
	 * @param planName
	 *            the plan name
	 * @return the response entity
	 */
	@ApiOperation(value = "Delete Plan", notes = "Delete a plan")
	@RequestMapping(value = "/{planId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> addUser(@PathVariable String planId) {
		Response<String> result = adminPlanImpl.deletePlan(planId);
		return new ResponseEntity<>(result, result.getHttpStatus());
	}

}
