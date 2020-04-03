package com.briup.crm.service;

import com.briup.crm.bean.CustomerConstitute;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/3 10:44
 * @Version 1.0
 */
public interface ICustomerConstituteService {

    //地区分类
    List<CustomerConstitute> regionAnalyze();

    //等级分类
    List<CustomerConstitute> levelAnalyze();
}
