package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Live;
import com.liujin.entity.RespBean;
import com.liujin.service.ILiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/31 10:29
 */
@RestController
public class LiveController {
    @Autowired
    private ILiveService liveService;
    @GetMapping("/lives")
    public Map<String,Object> fetchLive(@RequestParam("page") Integer page,
                                        @RequestParam("limit") Integer limit,
                                        Live live){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",liveService.fetchLive(live));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    /**
     * 散客入住
     * @return
     */
    @PostMapping("/guestLive")
    public RespBean guestLive(@RequestBody Live live){
        if (liveService.guestLive(live)){
            return RespBean.ok("支付成功",true);
        }
        return RespBean.error("支付失败",false);
    }
    @PostMapping("/memberLive")
    public RespBean memberLive(@RequestBody Live live){
        if (liveService.memberLive(live)){
            return RespBean.ok("支付成功",true);
        }
        return RespBean.error("支付失败",false);
    }


    @GetMapping("/selectLiveToday")
    public List<Live> selectLiveToday(){
        return liveService.selectLiveToday();
    }

    @GetMapping("/selectLiveAll")
    public List<Live> selectLiveAll(){
        return liveService.selectLiveAll();
    }
    @GetMapping("/queryLiveMoneyByLiveId/{liveId}")
    public float queryLiveMoneyByLiveId(@PathVariable("liveId") Integer liveId){
        return liveService.queryLiveMoneyByLiveId(liveId);
    }
 }
