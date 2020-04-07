package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.GoodsCategory;
import com.liujin.entity.RespBean;
import com.liujin.service.IGoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/29 14:38
 */
@RestController
public class GoodsCategoryController {
    @Autowired
    private IGoodsCategoryService goodsCategoryService;
    @GetMapping("/goodsCategorys")
    public Map<String,Object> fetchGoodsCategoryInfo(@RequestParam("page") Integer page,
                                                     @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",goodsCategoryService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/goodsCategory")
    public RespBean increaseGoodsCategory(@RequestBody GoodsCategory goodsCategory){
        if (goodsCategory.insert()){
            return RespBean.ok("新增成功",true);
        }
        return RespBean.error("新增失败",false);
    }
    @GetMapping("/checkGoodsCategoryName/{goodsCategoryName}")
    public Integer checkGoodsCategoryName(@PathVariable("goodsCategoryName") String goodsCategoryName){
        return goodsCategoryService.getBaseMapper().selectCount(new QueryWrapper<GoodsCategory>().eq("goods_category_name",goodsCategoryName));
    }
    @GetMapping("/goodsCategoryForSelect")
    public List<GoodsCategory> fetchGoodsCategoryForSelect(){
        return goodsCategoryService.getBaseMapper().selectList(null);
    }
}
