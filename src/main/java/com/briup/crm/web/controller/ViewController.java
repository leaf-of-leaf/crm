package com.briup.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author kj
 * @Date 2020/3/26 15:26
 * @Version 1.0
 * 用来映射thymeleaf的controller
 */
@Controller
public class ViewController {

    @RequestMapping("/login")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("/index")
    public String toIndexPage(HttpSession session){
        return "index";
    }
}
