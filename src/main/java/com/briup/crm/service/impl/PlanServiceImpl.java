package com.briup.crm.service.impl;

import com.briup.crm.bean.Plan;
import com.briup.crm.dao.PlanDao;
import com.briup.crm.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/2 20:32
 * @Version 1.0
 */
@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private PlanDao planDao;

    @Override
    public List<Plan> findPlansByUserIdAndChanceId(Integer userId, Integer chanceId) {
        return planDao.findPlansByUserIdAndChanceId(userId, chanceId);
    }

    @Override
    public void deletePlanById(Integer planId) {
        planDao.deleteById(planId);
    }

    @Override
    public void savePlan(Plan plan) {

        planDao.save(plan);
    }
}
