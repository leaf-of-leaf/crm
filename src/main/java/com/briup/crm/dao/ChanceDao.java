package com.briup.crm.dao;

import com.briup.crm.bean.Chance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kj
 * @Date 2020/3/31 13:26
 * @Version 1.0
 */
public interface ChanceDao extends JpaRepository<Chance, Integer> {
    Page<Chance> findChancesByAddress(Pageable pageable, String address);
    Page<Chance> findChancesByCustomerLike(Pageable pageable, String customer);
    Page<Chance> findChancesByAddressAndCustomerLike(
            Pageable pageable,
            String address,
            String customer
    );
    Page<Chance> findChancesByHandlerId(Pageable pageable, Integer user_i);
    Page<Chance> findChancesByHandlerIdAndAddress(
       Pageable pageable,
       Integer user_id,
       String address
    );
}
