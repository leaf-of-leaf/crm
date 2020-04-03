package com.briup.crm.web.controller;

import com.briup.crm.bean.Role;
import com.briup.crm.bean.User;
import com.briup.crm.service.IRoleService;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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
    @Autowired
    private IRoleService roleService;

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
        return "redirect:findAllUsers";
    }

    @GetMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam("user_id") Integer id){
        service.deleteUser(id);
        return "删除成功";
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(User user){
        String info = user.getId()==null?"保存成功":"修改成功";
        service.saveUser(user);
        return info;
    }

    @GetMapping("/findUser")
    @ResponseBody
    public User findUser(Integer id){
        return service.findUserById(id);
    }

    @RequestMapping("/findAllUsers")
    public String findAllUsers(Integer roleId, HttpSession session){
        Page<User> allUsers = service.findAllUsers(roleId, null);
        List<Role> Roles = roleService.findAll();
        session.setAttribute("user_roles", Roles);
        session.setAttribute("users", allUsers);
        session.setAttribute("roleId", roleId);
        return "pages/user";
    }

    @RequestMapping("/updateUserPages")
    public String updatePages(Integer page, HttpSession session){
        Page<User> allUsers = service.findAllUsers((Integer) session.getAttribute("roleId"), page);
        List<Role> Roles = roleService.findAll();
        session.setAttribute("user_roles", Roles);
        session.setAttribute("users", allUsers);
        return "pages/user";
    }
}
