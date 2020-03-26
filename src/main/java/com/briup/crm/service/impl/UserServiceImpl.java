package com.briup.crm.service.impl;

import com.briup.crm.bean.User;
import com.briup.crm.dao.UserDao;
import com.briup.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
