package com.briup.crm.service.impl;

import com.briup.crm.bean.Role;
import com.briup.crm.dao.RoleDao;
import com.briup.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/3/27 14:42
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> findAllRoles() {
        return findAllRoles(0);
    }

    @Override
    public Page<Role> findAllRoles(Integer pageIndex) {
        //指定查看第几页数据，并且指定一页显示多少条数据
        return roleDao.findAll(PageRequest.of(pageIndex,5));
    }

    @Override
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findRoleById(Integer id) {
        //getOne会使用懒加载模式，并且是通过代理对象
        //jackson序列化与hibernate中的懒加载冲突
        //因为会懒加载会默认在代理对象中属性中加上he...而jackson解析不了这个，所有报错
        //所以在类上加上@JsonIgnoreProperties("")
        return roleDao.getOne(id);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteById(id);
    }

    @Override
    public Integer getRoleNumber() {
        int count = (int)roleDao.count();
        return count % 5 == 0? count / 5 : count / 5 + 1;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
