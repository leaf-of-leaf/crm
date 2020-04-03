package com.briup.crm.dao;

import com.briup.crm.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/3 10:45
 * @Version 1.0
 */
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    //客户管理相关内容


    //数据分析相关内容
    List<Customer> findByRegion(String region);
    List<Customer> findByLevel(String level);


}
