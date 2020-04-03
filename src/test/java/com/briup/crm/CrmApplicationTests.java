package com.briup.crm;

import com.briup.crm.bean.Chance;
import com.briup.crm.bean.User;
import com.briup.crm.dao.ChanceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    @Autowired
    private ChanceDao chanceDao;

    @Test
    public void contextLoads() {



        for (int i = 1; i < 4; i++){
            Chance chance = new Chance(i, "湘南", "许三三",
                    "100%", "湘南日报", "华南", "邝杰",
                    10086, new User(1), new User(2), "描述", "待处理");
            chanceDao.save(chance);
        }

    }

}
