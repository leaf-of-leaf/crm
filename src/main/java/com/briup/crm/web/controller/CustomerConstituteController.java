package com.briup.crm.web.controller;

import com.briup.crm.bean.CustomerConstitute;
import com.briup.crm.service.ICustomerConstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kj
 * @Date 2020/4/3 10:42
 * @Version 1.0
 */
@Controller
public class CustomerConstituteController {

    @Autowired
    private ICustomerConstituteService service;

    @RequestMapping("/toCustomerConstitute")
    public String toCustomerConstitute(){
        return "pages/customerConstitute";
    }

    @RequestMapping("/toCustomerContribution")
    public String toCustomerContribution(){
        return "pages/customerContribution";
    }


    @RequestMapping("regionAnalyze")
    @ResponseBody
    public List<CustomerConstitute> toRegionAnalyze(){
        return service.regionAnalyze();
    }

    @RequestMapping("levelAnalyze")
    @ResponseBody
    public List<CustomerConstitute> toLevelAnalyze(){
        return service.levelAnalyze();
    }

}
