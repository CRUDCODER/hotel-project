package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.RespBean;
import com.liujin.entity.Room;
import com.liujin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/29 11:58
 */
@RestController
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @GetMapping("/rooms")
    public Map<String, Object> fetchRoomsInfo(@RequestParam("page") Integer page,
                                              @RequestParam("limit") Integer limit,
                                              Room room) {
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String, Object> map = new HashMap<>(16);
        map.put("data",roomService.fetchRooms(room));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/room")
    public RespBean increaseRoom(@RequestBody Room room){
        room.setRoomFlag(0);
        if (room.insert()){
            return RespBean.ok("新增成功",true);
        }
        return RespBean.error("新增失败",false);
    }
    @GetMapping("/checkRoomNumber/{roomNumber}")
    public Integer checkRoomNumber(@PathVariable("roomNumber") Integer roomNumber){
        return roomService.getBaseMapper().selectCount(new QueryWrapper<Room>().eq("room_number",roomNumber));
    }
    @GetMapping("/fetchRoomByRoomId/{roomId}")
    public Room fetchRoomByRoomId(@PathVariable("roomId") Integer roomId){
        return roomService.getBaseMapper().selectOne(new QueryWrapper<Room>().eq("room_id",roomId));
    }
    @GetMapping("/fetchByRoomCategoryId/{roomCategoryId}")
    public List<Room> queryByRoomCategoryId(@PathVariable("roomCategoryId") Integer roomCategoryId){
        return roomService.getBaseMapper().selectList(new QueryWrapper<Room>().eq("room_category_id",roomCategoryId));
    }
    @GetMapping("/fetchByRoomCategoryIdAndRoomFlag")
    public List<Room> fetchByRoomCategoryIdAndRoomFlag(@RequestParam("roomCategoryId")Integer roomCategoryId,@RequestParam("roomFlag") Integer roomFlag ){
        return roomService.getBaseMapper().selectList(new QueryWrapper<Room>().eq("room_category_id",roomCategoryId).eq("room_flag",roomFlag));
    }
}
