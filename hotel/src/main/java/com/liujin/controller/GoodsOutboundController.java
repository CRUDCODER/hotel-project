package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.GoodsOutbound;
import com.liujin.service.IGoodsOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/30 10:21
 */
@RestController
public class GoodsOutboundController {
    @Autowired
    private IGoodsOutboundService goodsOutboundService;

    @GetMapping("/goodsOutbound")
    public Map<String,Object> fetchGoodsOutbound(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit,
                                                 GoodsOutbound goodsOutbound){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",goodsOutboundService.fetchGoodsOutbound(goodsOutbound));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
}
