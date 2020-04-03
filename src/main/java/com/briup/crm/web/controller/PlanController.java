package com.briup.crm.web.controller;

import com.briup.crm.bean.Chance;
import com.briup.crm.bean.Plan;
import com.briup.crm.bean.User;
import com.briup.crm.service.IPlanService;
import com.briup.crm.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/plan")
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
    public String submitPlan(String plan, HttpSession session){
        Chance chance = (Chance) session.getAttribute("chance");
        planService.savePlan(new Plan(chance, plan));
        chance.setStatus("处理中");
        saleService.saveChance(chance);
        return "redirect:findAllChanceByUser";
    }

    @GetMapping("/planEdit")
    public String toPlanEdit(HttpSession session, Integer chanceId){
        User user = (User) session.getAttribute("user");
        List<Plan> plans = planService.findPlansByUserIdAndChanceId(user.getId(), chanceId);
        session.setAttribute("plans", plans);

        Chance chance = saleService.findChanceById(chanceId);
        session.setAttribute("chance", chance);
        return "pages/plan_edit";
    }

    @GetMapping("/planDetail")
    public String toPlanDetail(){
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
}
