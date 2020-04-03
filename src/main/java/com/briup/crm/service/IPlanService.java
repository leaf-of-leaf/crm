package com.briup.crm.service;

import com.briup.crm.bean.Plan;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/2 13:04
 * @Version 1.0
 * 查询分配给当前经理的商机计划
 *
 */
public interface IPlanService {

    List<Plan> findPlansByUserIdAndChanceId(Integer userId, Integer chanceId);
    void deletePlanById(Integer planId);
    void savePlan(Plan plan);

}
