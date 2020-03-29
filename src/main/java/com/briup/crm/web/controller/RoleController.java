package com.briup.crm.web.controller;

import com.briup.crm.bean.Role;
import com.briup.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author kj
 * @Date 2020/3/27 11:50
 * @Version 1.0
 * 角色管理模块
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService service;

    @GetMapping(value = "toRole/{page}")
    public String toRoleByPage(HttpSession session, Map<String, Object> map, @PathVariable("page") String pageStr){
        //将数据库中所有角色信息查询出来。保存到session中
        Integer page = null;
        try{
            page = Integer.valueOf(pageStr);
        } catch (NumberFormatException e){
            page = 1;
        }
        Page<Role> allRoles = service.findAllRoles(page-1);
        session.setAttribute("roles", allRoles.getContent());
        map.put("pageNum", page);
        map.put("rolesNum", service.getRoleNumber());
        return "pages/role";
    }

    @GetMapping("toRole")
    public String toRole(){
        return "redirect:toRole/1";
    }

    @PostMapping("/saveRole")
    @ResponseBody
    public String saveRole(Role role){
        service.saveRole(role);
        return "success";
    }

    @GetMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(@RequestParam(name = "role_id") Integer id){
        service.deleteRole(id);
        return "success";
    }
}
