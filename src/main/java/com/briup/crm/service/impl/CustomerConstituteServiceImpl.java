package com.briup.crm.service.impl;

import com.briup.crm.bean.Customer;
import com.briup.crm.bean.CustomerConstitute;
import com.briup.crm.dao.CustomerDao;
import com.briup.crm.service.ICustomerConstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kj
 * @Date 2020/4/3 10:44
 * @Version 1.0
 */
@Service
public class CustomerConstituteServiceImpl
        implements ICustomerConstituteService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<CustomerConstitute> regionAnalyze() {
        List<CustomerConstitute> customerConstitutes = new ArrayList<>();
        //从数据库将所有客户信息查找出来。(需要获取总数)
        float nums = customerDao.findAll().size();

        String[] regions = {"华中","华南","华东","华北"};

        //然后根据地区进行筛选,然后封装成List自定义对象
        for (String region: regions){
            //根据地区，查出数据库中对应的条目数
            float num = customerDao.findByRegion(region).size();
            //获取百分比
            float f = num / nums * 100;

            CustomerConstitute cc = new CustomerConstitute(region,f,region);
            customerConstitutes.add(cc);
        }


        return customerConstitutes;
    }

    @Override
    public List<CustomerConstitute> levelAnalyze() {
        float nums = customerDao.findAll().size();
        String[] levels = {"普通客户","大客户","重点开发客户","合作伙伴","战略合作伙伴"};

        List<CustomerConstitute> list = new ArrayList<>();

        for(String level:levels){
            float num = customerDao.findByLevel(level).size();
            float result = num/nums;
            CustomerConstitute cc = new CustomerConstitute(level, result, level);
            list.add(cc);
        }

        return list;

    }
}
