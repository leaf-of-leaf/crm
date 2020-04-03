package com.briup.crm.web.controller;

import com.briup.crm.bean.Chance;
import com.briup.crm.bean.User;
import com.briup.crm.service.ISaleService;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kj
 * @Date 2020/3/31 13:16
 * @Version 1.0
 */
@Controller
public class SaleController {

    @Autowired
    private ISaleService service;
    @Autowired
    private IUserService userService;

    @GetMapping("/toSale")
    public String toSale(){
        return "redirect:findAllChance";
    }

    @GetMapping("/deleteChance")
    @ResponseBody
    public String deleteChance(Integer id){
        service.deleteChance(id);
        return "删除成功";
    }

    @GetMapping("/findChance")
    @ResponseBody
    public Chance findChance(Integer id){
        return service.findChanceById(id);
    }

    @PostMapping("/saveChance")
    @ResponseBody
    public String saveChance(Chance chance){
        System.out.println("Chance DEBUG:"+chance);
        User user = userService.findByName(chance.getCreator().getName());
        chance.setCreator(user);
        String info = chance.getId() == null?"保存成功":"修改成功";
        service.saveChance(chance);
        return info;
    }

    @RequestMapping("/findAllChance")
    public String findAllChance(String address, String customer, HttpSession session){
        Page<Chance> allChance = service.findAllChance(address, customer, null);
        List<User> allUsersByRole = userService.findAllUsersByRoleId(4);
        session.setAttribute("managers", allUsersByRole);
        session.setAttribute("address", address);
        session.setAttribute("customer", customer);
        session.setAttribute("chances", allChance);
        return "pages/sales";
    }

    @RequestMapping("/updateChancePages")
    public String updatePages(Integer page, HttpSession session){
        String address = (String)session.getAttribute("address");
        String customer = (String)session.getAttribute("customer");
        Page<Chance> allChance = service.findAllChance(address, customer, page-1);
        Page<User> allUsersByRole = userService.findAllUsersByRole(4);
        session.setAttribute("managers", allUsersByRole);
        session.setAttribute("chances",allChance);
        return "pages/sales";
    }
}
