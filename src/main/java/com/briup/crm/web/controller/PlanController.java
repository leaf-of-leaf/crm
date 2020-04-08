package com.briup.crm.web.controller;

import com.briup.crm.bean.Chance;
import com.briup.crm.bean.Plan;
import com.briup.crm.bean.User;
import com.briup.crm.service.IPlanService;
import com.briup.crm.service.ISaleService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kj
 * @Date 2020/4/2 13:09
 * @Version 1.0
 */
@Controller
public class PlanController {

    @Autowired
    private ISaleService saleService;
    @Autowired
    private IPlanService planService;

    @GetMapping("/toPlan")
    public String toPlan(){
        return "redirect:findAllChanceByUser";
    }

    @RequestMapping("/planAdd")
    public String toPlanAdd(HttpSession session, Integer chanceId){
        Chance chance = saleService.findChanceById(chanceId);
        session.setAttribute("chance", chance);
        return "pages/plan_add";
    }

    @RequestMapping("submitPlan")
    public String submitPlan(String todo, HttpSession session, Integer planId, String result){
        Chance chance = (Chance) session.getAttribute("chance");
        Plan plan = planId == null? new Plan(chance,todo, StringUtils.isEmpty(result)?"进行中":result):
                new Plan(planId, chance,todo, StringUtils.isEmpty(result)?"进行中":result);
        planService.savePlan(plan);
        chance.setStatus("处理中");
        saleService.saveChance(chance);
        return "redirect:findAllChanceByUser";
    }

    @RequestMapping("editPlan")
    @ResponseBody
    public String editPlan(String todo, HttpSession session, Integer planId, String result){
        String msg = planId == null?"新增成功":"修改成功";
        Chance chance = (Chance) session.getAttribute("chance");
        planService.savePlan(new Plan(planId, chance, todo, StringUtils.isEmpty(result)?"进行中":result));
        return msg;
    }

    @RequestMapping("/planEdit/{chanceId}")
    public String toPlanEdit(HttpSession session, @PathVariable("chanceId") Integer chanceId){
        List<Plan> plans = planService.findPlansByChanceId(chanceId);
        session.setAttribute("plans", plans);

        Chance chance = saleService.findChanceById(chanceId);
        session.setAttribute("chance", chance);
        return "pages/plan_edit";
    }

    @RequestMapping("/planDetail")
    public String toPlanDetail(Integer chanceId, HttpSession session){
        Chance chance = saleService.findChanceById(chanceId);
        session.setAttribute("chance", chance);
        User user = (User) session.getAttribute("user");
        List<Plan> plans = planService.findPlansByUserIdAndChanceId(user.getId(), chanceId);
        session.setAttribute("plans", plans);
        return "pages/plan_detail";
    }

    @RequestMapping("/findAllChanceByUser")
    public String findAllChanceByUser(HttpSession session, String address){
        User user = (User) session.getAttribute("user");
        Page<Chance> chances = saleService.findAllChanceByUser(null, user.getId(), address);
        session.setAttribute("chances", chances);
        session.setAttribute("address", address);
        return "pages/plan";
    }

    @RequestMapping("/updatePagesByUser")
    public String updateChancePagesByUser(HttpSession session, Integer page){
        User user = (User) session.getAttribute("user");
        String address = (String) session.getAttribute("address");
        Page<Chance> chances = saleService.findAllChanceByUser(page - 1, user.getId(), address);
        session.setAttribute("chances", chances);
        return "pages/plan";
    }

    @RequestMapping("/findPlan")
    @ResponseBody
    public Plan findPlan(Integer planId){
        return planService.findPlanById(planId);
    }

    @ResponseBody
    @RequestMapping("deletePlan/{id}")
    public String deletePlan(@PathVariable("id") Integer planId){
        planService.deletePlanById(planId);
        return "删除成功";
    }

    @ResponseBody
    @RequestMapping("submitPlans")
    public String submitPlans(Integer chanceId){
        Chance chance = saleService.findChanceById(chanceId);
        chance.setStatus("处理完成");
        saleService.saveChance(chance);
        return "提交成功";
    }
}
