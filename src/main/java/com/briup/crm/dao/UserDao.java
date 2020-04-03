package com.briup.crm.dao;

import com.briup.crm.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByName(String name);
    Page<User> findUsersByRoleId(Pageable pageable, Integer roleId);
    long countUsersByRoleId(Integer roleId);
    User findUserById(Integer id);
    List<User> findUsersByRoleId(Integer roleId);
}
