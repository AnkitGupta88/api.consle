package com.global.console.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.console.dao.PlanDao;
import com.global.console.dto.PlanDetails;
import com.global.console.model.Plan;
import com.global.console.repository.PlanRepository;

// TODO: Auto-generated Javadoc
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
		ModelMapper modelMapper = new ModelMapper();
		Plan plan = modelMapper.map(planDetails, Plan.class);
		plan.setPlanId(id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String createdOn = sdf.format(new Date());
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
		plan.setPlanName(planDetails.getPlanName());
		plan.setCreatedBy(planDetails.getCreatedBy());
		plan.setConfigType(planDetails.getConfigType());
		plan.setConfigQuantity(planDetails.getConfigQuantity());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String createdOn = sdf.format(new Date());
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
