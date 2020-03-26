package com.briup.crm.web.controller;

import com.briup.crm.bean.User;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kj
 * @Date 2020/3/26 16:32
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //service层
    @Autowired
    private IUserService service;

    @RequestMapping("/login")
    @ResponseBody
    public String login(String name, String password){
        User user = service.findByName(name);
        return user == null? "当前用户不存在!":
                !user.getPassword().equals(password)? "密码不正确!":
                        !user.getFlag().equals(1)? "账户已注销":
                                "success";
    }
}
