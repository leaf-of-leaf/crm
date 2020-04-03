package com.briup.crm.service.impl;

import com.briup.crm.bean.User;
import com.briup.crm.dao.UserDao;
import com.briup.crm.service.IRoleService;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/3/26 16:51
 * @Version 1.0
 * 和User用户相关的Service层逻辑
 */
@Service
public class UserServiceImpl implements IUserService {

    //dao层
    @Autowired
    private UserDao userDao;


    @Override
    public User findByName(String name) {
        User user = userDao.findUserByName(name);
        return user;
    }
    @Override
    public void deleteUser(Integer id) {
        System.out.println("执行了userDao deleteById");
        userDao.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }



    public Integer parseInt(String IntStr){
        Integer result = null;
        try{
            result = Integer.valueOf(IntStr);
        } catch (NumberFormatException e){
            result = 1;
        }
        return result;
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public Page<User> findAllUsersByRoleAndPage(Integer roleId, Integer page) {
        if(roleId == null){
            return userDao.findAll(PageRequest.of(page-1, 3));
        }else{
            return userDao.findUsersByRoleId(PageRequest.of(page-1,3), roleId);
        }
    }

    @Override
    public Page<User> findAllUsersByRole(Integer roleId) {
        if(roleId == null){
            return userDao.findAll(PageRequest.of(0, 3));
        }else{
            return userDao.findUsersByRoleId(PageRequest.of(0,3), roleId);
        }
    }

    @Override
    public Page<User> findAllUsers(Integer roleId, Integer page) {
        if(page == null){
            return findAllUsersByRole(roleId);
        }else {
            return findAllUsersByRoleAndPage(roleId,page);
        }
    }

    @Override
    public List<User> findAllUsersByRoleId(Integer roleId) {
        return userDao.findUsersByRoleId(roleId);
    }
}
