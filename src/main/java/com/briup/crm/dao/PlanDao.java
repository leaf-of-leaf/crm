package com.briup.crm.dao;

import com.briup.crm.bean.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/2 13:04
 * @Version 1.0
 */
public interface PlanDao extends JpaRepository<Plan, Integer> {
//    @Query("select u from User u where u.name = ?1")
    @Query(value = "select p.id,p.chance_id,p.todo,p.result " +
            "from plan p left join chance c " +
            "on p.chance_id = c.id and c.handler_id = ?1 " +
            "where chance_id = ?2", nativeQuery = true)
    List<Plan> findPlansByUserIdAndChanceId(Integer userId, Integer chanceId);

    List<Plan> findPlansByChanceId(Integer chanceId);
}
