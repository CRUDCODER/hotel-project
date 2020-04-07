package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.RespBean;
import com.liujin.entity.RoomReservation;
import com.liujin.service.IRoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/30 13:36
 */
@RestController
public class RoomReservationController {
    @Autowired
    private IRoomReservationService roomReservationService;
    @GetMapping("/roomReservations")
    public Map<String,Object> fetchRoomReservation(@RequestParam("page") Integer page,
                                                   @RequestParam("limit") Integer limit,
                                                   RoomReservation roomReservation){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",roomReservationService.fetchRoomReservation(roomReservation));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/roomReservation")
    public RespBean reservationRoom(@RequestBody RoomReservation roomReservation){
        if (roomReservationService.reservationRoom(roomReservation)){
            return RespBean.ok("预定成功",true);
        }
        return RespBean.error("预定失败",false);
    }
    @GetMapping("/fetchByMemberId/{memberId}")
    public List<RoomReservation> fetchByMemberId(@PathVariable("memberId") Integer memberId){
        RoomReservation roomReservation=new RoomReservation();
        roomReservation.setMemberId(memberId);
        roomReservation.setReservationFlag(0);
        return roomReservationService.fetchRoomReservation(roomReservation);
    }
    @GetMapping("/statisticalReservation")
    public List<RoomReservation> statistical(){
        return roomReservationService.statistical();
    }
}
