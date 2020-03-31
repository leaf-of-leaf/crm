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
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Chance> findAllChance(Integer page);

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

}
