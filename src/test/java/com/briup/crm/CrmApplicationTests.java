package com.briup.crm;

import com.briup.crm.bean.Chance;
import com.briup.crm.bean.Customer;
import com.briup.crm.bean.User;
import com.briup.crm.dao.ChanceDao;
import com.briup.crm.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    @Autowired
    private ChanceDao chanceDao;
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void contextLoads() {



        for (int i = 1; i < 4; i++){
            Chance chance = new Chance(i, "湘南", "许三三",
                    "100%", "湘南日报", "华南", "邝杰",
                    10086, new User(1), new User(2), "描述", "待处理");
            chanceDao.save(chance);
        }

    }

    @Test
    public void run(){
        Customer customer = new Customer();
        customer.setName("%王%");
        Page<Customer> all = customerDao.findAll(Example.of(customer), PageRequest.of(0,3));

        System.out.println(all.getContent());

    }

}
