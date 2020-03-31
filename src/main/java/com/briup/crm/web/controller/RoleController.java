package com.briup.crm.web.controller;

import com.briup.crm.bean.Role;
import com.briup.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String toRoleByPage(HttpSession session, @PathVariable("page") String pageStr){
        //将数据库中所有角色信息查询出来。保存到session中
        Page<Role> allRoles = service.findAllRoles(Integer.parseInt(pageStr)-1);
        session.setAttribute("roles", allRoles);
        return "pages/role";
    }

    @GetMapping("toRole")
    public String toRole(){
        return "redirect:toRole/1";
    }

    @PostMapping("/saveRole")
    @ResponseBody
    public String saveRole(Role role){
        //role如果id为空，保存后,id就会有值,所以应该先判断，再保存
        String meg = role.getId() == null?"保存成功":"修改成功";
        service.saveRole(role);
        return meg;
    }

    @GetMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(@RequestParam(name = "role_id") Integer id){
        service.deleteRole(id);
        return "删除成功";
    }

    @GetMapping("/findRole")
    @ResponseBody
    public Role findRole(Integer id){
        return service.findRoleById(id);
    }
}
