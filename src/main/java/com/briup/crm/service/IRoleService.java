package com.briup.crm.service;

import com.briup.crm.bean.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    //查询刚进入角色管理模块的所有角色
    Page<Role> findAllRoles();

    //查询指定页上的数据信息
    Page<Role> findAllRoles(Integer pageIndex);

    //新增和修改
    void saveRole(Role role);

    //通过id找到对应的的Role
    Role findRoleById(Integer id);

    //删除
    void deleteRole(Integer id);

    //总共的角色数
    Integer getRoleNumber();

    List<Role> findAll();

}
