package com.briup.crm.dao;

import com.briup.crm.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByName(String name);

}
