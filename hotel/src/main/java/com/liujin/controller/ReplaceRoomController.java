package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.ReplaceRoom;
import com.liujin.entity.RespBean;
import com.liujin.service.IReplaceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/1 13:30
 */
@RestController
public class ReplaceRoomController {
    @Autowired
    private IReplaceRoomService replaceRoomService;
    @GetMapping("/replaceRooms")
    public Map<String,Object> fetchReplaceRoom(@RequestParam("page") Integer page,
                                               @RequestParam("limit") Integer limit,
                                               ReplaceRoom replaceRoom){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",replaceRoomService.fetchReplaceRoom(replaceRoom));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 客户换房 需要把之前的房间改为未出租  换的房间改为出租
     * @return
     */
    @PostMapping("/replaceRoom")
    public RespBean replaceRoom(@RequestBody ReplaceRoom replaceRoom){
        if (replaceRoomService.replaceRoom(replaceRoom)){
            return RespBean.ok("更换成功",true);
        }
        return RespBean.error("更换失败",false);
    }
}
