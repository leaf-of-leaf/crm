package com.briup.crm.service.impl;

import com.briup.crm.bean.User;
import com.briup.crm.dao.UserDao;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<User> findAllUsers(Integer page) {
        return userDao.findAll(PageRequest.of(page,3));
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public Page<User> findAllUsersByRole(String pageStr, String roleIdStr) {
        return userDao.findUsersByRoleId(PageRequest.of(parseInt(pageStr) - 1,3),
                parseInt(roleIdStr));
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
}
