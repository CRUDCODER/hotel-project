package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Leave;
import com.liujin.entity.RespBean;
import com.liujin.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/2 9:22
 */
@RestController
public class LeaveController {
    @Autowired
    private ILeaveService leaveService;
    @GetMapping("/leaves")
    public Map<String,Object> fetchLeave(@RequestParam("page") Integer page,
                                         @RequestParam("limit") Integer limit,
                                         Leave leave){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",leaveService.fetchLeave(leave));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 办理离店
     * @return
     */
    @PostMapping("/guestLeave")
    public RespBean guestLeave(@RequestBody Leave leave){
        if (leaveService.guestLeave(leave)){
            return RespBean.ok("离店办理成功",true);
        }
        return RespBean.error("离店办理失败",false);
    }
}
