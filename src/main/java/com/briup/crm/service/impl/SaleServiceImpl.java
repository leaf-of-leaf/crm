package com.briup.crm.service.impl;

import com.briup.crm.bean.Chance;
import com.briup.crm.dao.ChanceDao;
import com.briup.crm.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Page<Chance> findAllChance(String address, String customer, Integer page) {
        if(page == null){
            if(StringUtils.isEmpty(address) && StringUtils.isEmpty(customer)){
                return chanceDao.findAll(PageRequest.of(0,3));
            }else if(StringUtils.isEmpty(address) && !StringUtils.isEmpty(customer)){
                return findAllChanceByCustomer(customer);
            }else if((!StringUtils.isEmpty(address) && StringUtils.isEmpty(customer))){
                return findAllChanceByAddress(address);
            }else{
                return findAllChanceByAddressAndCustomer(address,customer);
            }
        }else{
            if(StringUtils.isEmpty(address) && StringUtils.isEmpty(customer)){
                return chanceDao.findAll(PageRequest.of(page,3));
            }else if(StringUtils.isEmpty(address) && !StringUtils.isEmpty(customer)){
                return findAllChanceByCustomerAndPage(customer,page);
            }else if((!StringUtils.isEmpty(address) && StringUtils.isEmpty(customer))){
                return findAllChanceByAddressAndPage(address,page);
            }else{
                return findAllChanceByAddressAndCustomerAndPage(address,customer,page);
            }
        }
    }

    @Override
    public Page<Chance> findAllChanceByAddress(String address) {
        if(StringUtils.isEmpty(address)){
            return chanceDao.findAll(PageRequest.of(0,3));
        }else{
            return chanceDao.findChancesByAddress(PageRequest.of(0,3), address);
        }
    }

    @Override
    public Page<Chance> findAllChanceByAddressAndPage(String address, Integer page) {
        if(StringUtils.isEmpty(address)){
            return chanceDao.findAll(PageRequest.of(page,3));
        }else{
            return chanceDao.findChancesByAddress(PageRequest.of(page,3), address);
        }
    }

    @Override
    public Page<Chance> findAllChanceByCustomer(String customer) {
        if(StringUtils.isEmpty(customer)){
            return chanceDao.findAll(PageRequest.of(0,3));
        }else{
            return chanceDao.findChancesByCustomerLike(PageRequest.of(0,3)
                    , "%" + customer + "%");
        }
    }

    @Override
    public Page<Chance> findAllChanceByCustomerAndPage(String customer, Integer page) {
        if(StringUtils.isEmpty(customer)){
            return chanceDao.findAll(PageRequest.of(page,3));
        }else{
            return chanceDao.findChancesByCustomerLike(PageRequest.of(page,3)
                    , "%" + customer + "%");
        }
    }

    @Override
    public Page<Chance> findAllChanceByAddressAndCustomer(String address, String customer) {
        return chanceDao.findChancesByAddressAndCustomerLike(PageRequest.of(0,3),address,"%" + customer + "%");
    }

    @Override
    public Page<Chance> findAllChanceByAddressAndCustomerAndPage(String address, String customer, Integer page) {
        return chanceDao.findChancesByAddressAndCustomerLike(PageRequest.of(page,3),address,"%" + customer + "%");
    }

    //为PlanController提供的通过userId查询对应的销售商机

    @Override
    public Page<Chance> findAllChanceByUser(Integer page, Integer user_id, String address) {
        if(page == null){
            if(StringUtils.isEmpty(address)){
                return findAllChanceByUser(0,user_id);
            }else{
                return findAllChanceByUserAndAddress(0,user_id,address);
            }
        }else{
            if(StringUtils.isEmpty(address)){
                return findAllChanceByUser(page,user_id);
            }else{
                return findAllChanceByUserAndAddress(page,user_id,address);
            }
        }
    }

    @Override
    public Page<Chance> findAllChanceByUser(Integer page, Integer user_id) {
        return chanceDao.findChancesByHandlerId(PageRequest.of(page,3), user_id);
    }

    @Override
    public Page<Chance> findAllChanceByUserAndAddress(Integer page, Integer user_id, String address) {
        return chanceDao.findChancesByHandlerIdAndAddress(PageRequest.of(page,3),user_id,address);
    }
}
