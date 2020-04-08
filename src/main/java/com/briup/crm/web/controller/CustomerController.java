package com.briup.crm.web.controller;

import com.briup.crm.bean.Customer;
import com.briup.crm.bean.User;
import com.briup.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author kj
 * @Date 2020/4/6 17:06
 * @Version 1.0
 */
@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @RequestMapping("toCustomer")
    public String toCustomer(){
        return "redirect:findAllCustomer";
    }

    @RequestMapping("findAllCustomer")
    public String findAllCustomer(String name, String region, String level, HttpSession session){
        User user = (User) session.getAttribute("user");
        Page<Customer> allCustomer = customerService.findAllCustomer(name, region, level, user.getId());
        System.out.println(allCustomer.getContent());
        session.setAttribute("name", name);
        session.setAttribute("region", region);
        session.setAttribute("level", level);
        session.setAttribute("customers", allCustomer);
        return "pages/customer";
    }

    @RequestMapping("updateCustomerPage")
    public String updateCustomerPage(Integer page, HttpSession session){
        User user = (User) session.getAttribute("user");
        String name = (String) session.getAttribute("name");
        String region = (String) session.getAttribute("region");
        String level = (String) session.getAttribute("level");
        Page<Customer> allCustomer = customerService.findAllCustomer(page - 1, name, region, level, user.getId());

        System.out.println(allCustomer.getContent());

        session.setAttribute("customers", allCustomer);
        return "pages/customer";
    }
}
