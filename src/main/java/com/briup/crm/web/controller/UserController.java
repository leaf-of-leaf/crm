package com.briup.crm.web.controller;

import com.briup.crm.bean.User;
import com.briup.crm.service.IRoleService;
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
        return "redirect:toUser/1";
    }

    @GetMapping(value = "toUser/{page}")
    public String toUserByPage(HttpSession session, Map<String, Object> map, @PathVariable("page") String pageStr){
        //将数据库中所有角色信息查询出来。保存到session中
        Page<User> allUsers = service.findAllUsers(Integer.parseInt(pageStr) - 1);
        session.setAttribute("users", allUsers);
        session.setAttribute("user_roles", roleService.findAll());
        return "pages/user";
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

    @GetMapping("/toUser/byRole/{roleId}/{page}")
    public String toUserByRoleAndPage(HttpSession session, Map<String, Object> map
            , @PathVariable("page") String pageStr, @PathVariable("roleId") String roleIdStr){
        Page<User> allUsers = service.findAllUsersByRole(pageStr,roleIdStr);
        if(Integer.parseInt(pageStr) > allUsers.getTotalPages()) return "redirect:1";
        session.setAttribute("users", allUsers);
        session.setAttribute("user_roles", roleService.findAll());
        map.put("roleId", Integer.valueOf(roleIdStr));
        return "pages/user";
    }

    @GetMapping("/findUser")
    @ResponseBody
    public User findUser(Integer id){
        return service.findUserById(id);
    }

//    @GetMapping("findAllUsers")
//    public String findAllUsers(Integer roleId, Map<String, Object> map, HttpSession session){
//        return "pages/user";
//    }
//
//    @GetMapping("updatePages")
//    public String updatePages(Integer roleId, Integer page, HttpSession session){
//        return "pages/user";
//    }
}
