package com.briup.crm.web.controller;

import com.briup.crm.bean.User;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author kj
 * @Date 2020/3/26 16:32
 * @Version 1.0
 */
@Controller
public class UserController {

    //service层
    @Autowired
    private IUserService service;

    @RequestMapping("/user/login")
    @ResponseBody
    public String login(String name, String password, HttpServletRequest request){
        User user = service.findByName(name);
        System.out.println(user);
        if(user == null){
            return "当前用户不存在";
        }
        if(!user.getPassword().equals(password)){
            return  "密码不正确!";
        }
        if(!user.getFlag().equals(1)){
            return "账户已注销";
        }
        request.getSession().setAttribute("user", user);
        return "success";
    }

    //退出功能
    @GetMapping("/loginout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }


}
