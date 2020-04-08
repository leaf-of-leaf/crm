package com.briup.crm.service;

import com.briup.crm.bean.Customer;
import org.springframework.data.domain.Page;

/**
 * @author kj
 * @Date 2020/4/6 17:03
 * @Version 1.0
 */
public interface ICustomerService {

    Page<Customer> findAllCustomer(String name, String region, String level, Integer userId);
    Page<Customer> findAllCustomer(Integer page, String name, String region, String level, Integer userId);

}
