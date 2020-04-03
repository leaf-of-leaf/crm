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

   Page<User> findAllUsersByRole(Integer roleId);
   Page<User> findAllUsers(Integer roleId, Integer page);

   List<User> findAllUsersByRoleId(Integer roleId);

   //删除User
   void deleteUser(Integer id);
   //保存或修改User
   void saveUser(User user);

   Page<User> findAllUsersByRoleAndPage(Integer roleId, Integer page);

   User findUserById(Integer id);
}
