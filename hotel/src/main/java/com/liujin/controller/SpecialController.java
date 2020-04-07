package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.RespBean;
import com.liujin.entity.Room;
import com.liujin.entity.Special;
import com.liujin.service.ISpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/2 13:02
 */
@RestController
public class SpecialController {
    @Autowired
    private ISpecialService specialService;

    /**
     * 查询今日特价房
     * 查询结果之前 需要判断是否已经过了优惠时间
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/special")
    public Map<String,Object> fetchSpecial(@RequestParam("page") Integer page,
                                           @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",specialService.fetchSpecial());
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    @PostMapping("/special")
    public RespBean increaseSpecialRoom(@RequestBody Special special){
        if (specialService.increaseSpecialRoom(special)){
            return RespBean.ok("添加成功",true);
        }
        return RespBean.error("添加失败",false);
    }

    /**
     * 查询该房间今天是否已经是特价
     * @return
     */
    @GetMapping("/checkIsIn/{roomId}")
    public Integer checkTodayIsInByRoomId(@PathVariable("roomId") Integer roomId){
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Integer count = specialService.getBaseMapper().selectCount(new QueryWrapper<Special>()
                .eq("room_id", roomId).eq("special_date", date));
        return count;
    }
    @GetMapping("/selectTodayIsSpecial")
    public List<Room> selectTodayIsSpecial(){
        return specialService.selectTodayIsSpecial();
    }
}
