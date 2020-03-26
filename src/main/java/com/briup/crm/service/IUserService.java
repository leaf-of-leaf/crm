package com.briup.crm.service;

import com.briup.crm.bean.User;

/**
 * @author kj
 * @Date 2020/3/26 16:47
 * @Version 1.0
 *
 */
public interface IUserService {

   User findByName(String name);



}
