package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.service.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/2 11:31
 */
@RestController
public class CheckoutController {
    @Autowired
    private ICheckoutService checkoutService;

    @GetMapping("/checkouts")
    public Map<String,Object> fetchCheckouts(@RequestParam("page") Integer page,
                                           @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",checkoutService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;

    }
}
