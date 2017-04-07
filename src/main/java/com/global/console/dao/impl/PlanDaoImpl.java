package com.global.console.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.console.dao.PlanDao;
import com.global.console.dto.PlanDetails;
import com.global.console.model.Plan;
import com.global.console.repository.PlanRepository;
import com.global.console.utils.Utils;

/**
 * The Class PlanDaoImpl.
 */
@Repository
public class PlanDaoImpl implements PlanDao {

	/** The plan repository. */
	@Autowired
	PlanRepository planRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.PlanDao#createPlan(com.global.console.dto.
	 * PlanDetails, java.util.UUID)
	 */
	@Override
	public Plan createPlan(PlanDetails planDetails, UUID id) {
		Plan plan = Utils.getObjectMapped(planDetails, Plan.class);
		plan.setPlanId(id);
		String createdOn = Utils.getCurrentDate();
		plan.setCreatedOn(createdOn);
		planRepository.save(plan);
		return plan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.PlanDao#findAll()
	 */
	@Override
	public List<Plan> findAll() {
		return planRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.PlanDao#editPlan(java.lang.String,
	 * com.global.console.dto.PlanDetails)
	 */
	@Override
	public Plan editPlan(String planId, PlanDetails planDetails) {

		UUID id = UUID.fromString(planId);
		Plan plan = planRepository.findByPlanId(id);
		plan.setPlanName((planDetails.getPlanName()==null?plan.getPlanName():planDetails.getPlanName()));
		plan.setCreatedBy(planDetails.getCreatedBy()==null?plan.getCreatedBy():planDetails.getCreatedBy());
		plan.setConfigType(planDetails.getConfigType()==null?plan.getConfigType():planDetails.getConfigType());
		plan.setConfigQuantity(planDetails.getConfigQuantity()==0?plan.getConfigQuantity():planDetails.getConfigQuantity());
		String createdOn = Utils.getCurrentDate();
		plan.setCreatedOn(createdOn);
		planRepository.save(plan);
		return plan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.PlanDao#deletePlan(java.lang.String)
	 */
	@Override
	public UUID deletePlan(String planId) {
		UUID id = UUID.fromString(planId);
		Plan plan = planRepository.findByPlanId(id);
		planRepository.delete(plan);
		return plan.getPlanId();
	}

}
