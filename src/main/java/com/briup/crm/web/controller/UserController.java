package com.briup.crm.web.controller;

import com.briup.crm.bean.User;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @GetMapping("/toUser")
    public String toUser(){
        return "redirect:toUser/1";
    }

    @GetMapping(value = "toUser/{page}")
    public String toUserByPage(HttpSession session, Map<String, Object> map, @PathVariable("page") String pageStr){
        //将数据库中所有角色信息查询出来。保存到session中
        Integer page = null;
        try{
            page = Integer.valueOf(pageStr);
        } catch (NumberFormatException e){
            page = 1;
        }
        Page<User> allUsers = service.findAllUsers(page - 1);
        System.out.println(allUsers.getContent());
        session.setAttribute("users", allUsers.getContent());
        map.put("pageNum", page);
        map.put("usersNum", service.getUserNum());
        return "pages/user";
    }

    @GetMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam("user_id") Integer id){
        service.deleteUser(id);
        return "success";
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(User user){
        System.out.println("user debug:" + user);
        service.saveUser(user);
        return "success";
    }
}
