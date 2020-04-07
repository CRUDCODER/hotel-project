package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.RespBean;
import com.liujin.entity.RoomGoods;
import com.liujin.service.IRoomGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/29 19:19
 */
@RestController
public class RoomGoodsController {
    @Autowired
    private IRoomGoodsService roomGoodsService;

    @GetMapping("/roomGoods")
    public Map<String,Object> fetchRoomGoods(@RequestParam("page") Integer page,
                                             @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",roomGoodsService.fetchRoomGoods());
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    @PostMapping("/roomGoods")
    public RespBean increaseRoomGoods(@RequestBody RoomGoods roomGoods){
        if (roomGoodsService.increaseRoomGoods(roomGoods)){
            return RespBean.ok("新增成功",true);
        }
        return RespBean.error("新增失败",false);
    }
    @GetMapping("/GoodsByRoomNumber")
    public RespBean fetchGoodsByRoomNumber(RoomGoods roomGoods){
        return RespBean.ok(roomGoodsService.fetchGoodsByRoomNumber(roomGoods));
    }
}
