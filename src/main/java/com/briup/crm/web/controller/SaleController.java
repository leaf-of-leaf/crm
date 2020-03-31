package com.briup.crm.web.controller;

import com.briup.crm.bean.Chance;
import com.briup.crm.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author kj
 * @Date 2020/3/31 13:16
 * @Version 1.0
 */
@Controller
public class SaleController {

    @Autowired
    private ISaleService service;

    @GetMapping("/toSale")
    public String toSale(){
        return "redirect:/toSale/1";
    }

    @GetMapping("/toSale/{page}")
    public String toSaleByPage(Map<String, Object> map, HttpSession session, @PathVariable("page") Integer page){

        Page<Chance> allChance = service.findAllChance(page - 1);
        session.setAttribute("chances", allChance);
        return "pages/sales";
    }

    @GetMapping("/deleteChance")
    @ResponseBody
    public String deleteChance(Integer id){
        service.deleteChance(id);
        return "删除成功";
    }

    @PostMapping("/saveChance")
    @ResponseBody
    public String saveChance(Chance chance){
        String info = chance.getId() == null?"保存成功":"修改成功";
        service.saveChance(chance);
        return info;
    }
}
