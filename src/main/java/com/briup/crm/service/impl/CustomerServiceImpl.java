package com.briup.crm.service.impl;

import com.briup.crm.bean.Customer;
import com.briup.crm.bean.User;
import com.briup.crm.dao.CustomerDao;
import com.briup.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author kj
 * @Date 2020/4/6 17:09
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerDao dao;


    @Override
    public Page<Customer> findAllCustomer(String name, String region, String level, Integer userId) {
        boolean b1 = StringUtils.isEmpty(name);
        boolean b2 = StringUtils.isEmpty(region);
        boolean b3 = StringUtils.isEmpty(level);

        Customer customer = new Customer();
        customer.setRegion(region);
        customer.setLevel(level);
        customer.setManager(new User(userId));
        System.out.println(customer);

        Example<Customer> of = Example.of(customer);
        PageRequest pr = PageRequest.of(0, 3);
        String nL = "%" + name + "%";
        if(b1&&b2&&b3){
            return dao.findAll(pr);
        }else if(!b1&&b2&&b3){
            return dao.findCustomersByNameLikeAndManagerId(pr, nL, userId);
        }else if(b1&&!b2&&b3 || b1&&b2&&!b3 || b1&&!b2&&!b3){
            if(region == "") customer.setRegion(null);
            if(level == "") customer.setLevel(null);
            return dao.findAll(of, pr);
        }else if(!b1&&!b2&&b3){
            return dao.findCustomersByNameLikeAndRegionAndManagerId(pr, nL, region, userId);
        }else if(!b1&&b2&&!b3){
            return dao.findCustomersByNameLikeAndLevelAndManagerId(pr, nL, level, userId);
        }else if(!b1&&!b2&&!b3){
            return dao.findCustomersByNameLikeAndRegionAndLevelAndManagerId(pr, nL, region, level, userId);
        }else{
            return null;
        }
    }

    @Override
    public Page<Customer> findAllCustomer(Integer page, String name, String region, String level, Integer userId) {
        boolean b1 = StringUtils.isEmpty(name);
        boolean b2 = StringUtils.isEmpty(region);
        boolean b3 = StringUtils.isEmpty(level);

        Customer customer = new Customer();
        customer.setRegion(region);
        customer.setLevel(level);
        customer.setManager(new User(userId));

        PageRequest pr = PageRequest.of(page, 3);
        Example<Customer> of = Example.of(customer);
        String nL = "%" + name + "%";
        if(b1&&b2&&b3){
            return dao.findAll(pr);
        }else if(!b1&&b2&&b3){
            return dao.findCustomersByNameLikeAndManagerId(pr, nL, userId);
        }else if(b1&&!b2&&b3 || b1&&b2&&!b3 || b1&&!b2&&!b3){
            if(region == "") customer.setRegion(null);
            if(level == "") customer.setLevel(null);
            return dao.findAll(of, pr);
        }else if(!b1&&!b2&&b3){
            return dao.findCustomersByNameLikeAndRegionAndManagerId(pr, nL, region, userId);
        }else if(!b1&&b2&&!b3){
            return dao.findCustomersByNameLikeAndLevelAndManagerId(pr, nL, level, userId);
        }else if(!b1&&!b2&&!b3){
            return dao.findCustomersByNameLikeAndRegionAndLevelAndManagerId(pr, nL, region, level, userId);
        }else{
            return null;
        }
    }
}
