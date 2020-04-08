package com.briup.crm.dao;

import com.briup.crm.bean.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/3 10:45
 * @Version 1.0
 */
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    //客户管理相关内容
    //通过客户名称查找
    Page<Customer> findCustomersByNameLikeAndManagerId(Pageable pageable, String name, Integer userId);
    //通过名称地址查询
    Page<Customer> findCustomersByNameLikeAndRegionAndManagerId(
            Pageable pageable,
            String name,
            String region,
            Integer userId
    );
    //通过名称等级查询
    Page<Customer> findCustomersByNameLikeAndLevelAndManagerId(
            Pageable pageable,
            String name,
            String level,
            Integer userId
    );
    //通过名称地址等级查询
    Page<Customer> findCustomersByNameLikeAndRegionAndLevelAndManagerId(
            Pageable pageable,
            String name,
            String region,
            String level,
            Integer userId
    );
    //数据分析相关内容
    List<Customer> findByRegion(String region);
    List<Customer> findByLevel(String level);


}
