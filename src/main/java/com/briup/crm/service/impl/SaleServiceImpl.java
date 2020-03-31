package com.briup.crm.service.impl;

import com.briup.crm.bean.Chance;
import com.briup.crm.dao.ChanceDao;
import com.briup.crm.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author kj
 * @Date 2020/3/31 13:25
 * @Version 1.0
 */
@Service
public class SaleServiceImpl implements ISaleService {


    @Autowired
    private ChanceDao chanceDao;

    @Override
    public Page<Chance> findAllChance(Integer page) {
        return chanceDao.findAll(PageRequest.of(page,3));
    }

    @Override
    public void deleteChance(Integer id) {
        chanceDao.deleteById(id);
    }

    @Override
    public Chance findChanceById(Integer id) {
        return chanceDao.getOne(id);
    }

    @Override
    public void saveChance(Chance chance) {
        chanceDao.save(chance);
    }
}
