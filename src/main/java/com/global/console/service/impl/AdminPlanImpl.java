package com.global.console.service.impl;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.global.console.dao.impl.PlanDaoImpl;
import com.global.console.dto.PlanDetails;
import com.global.console.dto.SubscriptionConfig;
import com.global.console.model.Plan;
import com.global.console.response.Response;
import com.global.console.service.AdminPlan;

/**
 * The Class AdminPlanImpl.
 */
@Service
public class AdminPlanImpl implements AdminPlan {

	/** The plan dao impl. */
	@Autowired
	PlanDaoImpl planDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.service.AdminPlan#createPlan(com.global.console.dto.
	 * PlanDetails)
	 */
	@Override
	public Response<Plan> createPlan(PlanDetails planDetails) {
		UUID planId = UUIDs.timeBased();
		SubscriptionConfig config = SubscriptionConfig.getConfigType(planDetails.getConfigType());
		Response<Plan> response;
		if (config != null) {
			planDetails.setConfigType(config.getConfigType());
			response = new Response<>(Arrays.asList(planDaoImpl.createPlan(planDetails, planId)), HttpStatus.OK,
					"Request Completed");
		} else {
			response = new Response<>(HttpStatus.BAD_REQUEST, "Invalid Config type entered");
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminPlan#viewAllPlans()
	 */
	@Override
	public Response<Plan> viewAllPlans() {
		Response<Plan> response = new Response<>(planDaoImpl.findAll(), HttpStatus.OK, "Request Completed");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminPlan#viewPlan(java.lang.String)
	 */
	@Override
	public Response<Plan> editPlan(String planId, PlanDetails planDetails) {
		Response<Plan> response = new Response<>(Arrays.asList(planDaoImpl.editPlan(planId, planDetails)), HttpStatus.OK, "Request Completed");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.service.AdminPlan#deletePlan(java.lang.String)
	 */
	@Override
	public Response<String> deletePlan(String planId) {
		Response<String> response = new Response<>(Arrays.asList(planDaoImpl.deletePlan(planId).toString()), HttpStatus.OK, "Request Completed");
		return response;
	}

}