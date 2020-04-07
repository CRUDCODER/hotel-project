package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.GoodsStorage;
import com.liujin.entity.RespBean;
import com.liujin.service.IGoodsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/30 9:29
 */
@RestController
public class GoodsStorageController {
    @Autowired
    private IGoodsStorageService goodsStorageService;
    @GetMapping("/goodsStorage")
    public Map<String,Object> fetchGoodsStorage(@RequestParam("page") Integer page,
                                                @RequestParam("limit") Integer limit,
                                                GoodsStorage goodsStorage){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",goodsStorageService.fetchGoodsStorage(goodsStorage));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/goodsStore")
    public RespBean increaseGoodsStore(@RequestBody GoodsStorage goodsStorage){
        if (goodsStorageService.increaseGoodsStorage(goodsStorage)){
            return RespBean.ok("添加成功",true);
        }
        return RespBean.error("添加失败",false);
    }
}
