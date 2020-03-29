package com.briup.crm.service;

import com.briup.crm.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author kj
 * @Date 2020/3/26 16:47
 * @Version 1.0
 *
 */
public interface IUserService {

   User findByName(String name);

   Page<User> findAllUsers(Integer page);

   int getUserNum();
   int getUserNumByRoleId(String roleIdStr);
   //删除User
   void deleteUser(Integer id);
   //保存或修改User
   void saveUser(User user);

   Page<User> findAllUsersByRole(String pageStr, String roleIdStr);
}
