package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Goods;
import com.liujin.entity.RespBean;
import com.liujin.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/29 18:37
 */
@RestController
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @GetMapping("/goods")
    public Map<String,Object> fetchGoodsInfo(@RequestParam("page") Integer page,
                                             @RequestParam("limit") Integer limit,
                                             Goods goods){
        System.out.println(goods);
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",goodsService.fetchGoodsInfo(goods));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @GetMapping("/checkGoodsName/{goodsName}")
    public Integer checkGoodsName(@PathVariable("goodsName") String goodsName){
        return goodsService.getBaseMapper().selectCount(new QueryWrapper<Goods>().eq("goods_name",goodsName));
    }
    @PostMapping("/goods")
    public RespBean increaseGoods(@RequestBody Goods goods){
        goods.setGoodsCount(0);
        if (goods.insert()){
            return RespBean.ok("添加成功",true);
        }
        return RespBean.error("添加失败",false);
    }
    @GetMapping("/fetchGoodsCategoryId/{goodsCategoryId}")
    public List<Goods> queryByGoodsCategoryId(@PathVariable("goodsCategoryId") Integer goodsCategoryId){
        return goodsService.getBaseMapper().selectList(new QueryWrapper<Goods>().eq("goods_category_id",goodsCategoryId));
    }
}
