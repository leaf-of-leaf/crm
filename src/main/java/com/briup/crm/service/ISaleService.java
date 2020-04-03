package com.briup.crm.service;

import com.briup.crm.bean.Chance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author kj
 * @Date 2020/3/31 13:21
 * @Version 1.0
 * 销售商机管理
 */
public interface ISaleService {

    /**
     * 删除Chance
     * @param id
     */
    void deleteChance(Integer id);

    /**
     * 通过id查找Chance
     * @param id
     * @return
     */
    Chance findChanceById(Integer id);

    /**
     * 保存或者修改Chance
     * @param chance
     */
    void saveChance(Chance chance);

    /**
     *
     * @param address
     * @param page
     * @return
     */
    Page<Chance> findAllChance(String address, String customer, Integer page);
    Page<Chance> findAllChanceByAddress(String address);
    Page<Chance> findAllChanceByCustomer(String customer);
    Page<Chance> findAllChanceByAddressAndPage(String address, Integer page);
    Page<Chance> findAllChanceByCustomerAndPage(String customer, Integer page);
    Page<Chance> findAllChanceByAddressAndCustomer(
            String address,
            String customer
    );
    Page<Chance> findAllChanceByAddressAndCustomerAndPage(
            String address,
            String customer,
            Integer page
    );

    Page<Chance> findAllChanceByUser(Integer page, Integer user_id, String address);
    Page<Chance> findAllChanceByUser(Integer page, Integer user_id);
    Page<Chance> findAllChanceByUserAndAddress(Integer page, Integer user_id, String address);
}
