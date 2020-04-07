package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Reception;
import com.liujin.entity.RespBean;
import com.liujin.service.IReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/31 10:27
 */
@RestController
public class ReceptionController {
    @Autowired
    private IReceptionService receptionService;
    @GetMapping("/receptions")
    public Map<String,Object> fetchReception(@RequestParam("page")Integer page,
                                             @RequestParam("limit")Integer limit,
                                             Reception reception){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>(16);
        map.put("data",receptionService.fetchReception(reception));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 此api为散客接待及入住接口
     * @param reception
     * @return
     */
    @PostMapping("/guestReception")
    public RespBean guestReception(@RequestBody Reception reception){
        Integer receptionId = receptionService.guestReception(reception);
        if (receptionId!=0){
            return RespBean.ok("ok",receptionId);
        }
        return RespBean.error("error",false);
    }

    /**
     * 此api为会员接待入住接口
     * @param reception
     * @return
     */
    @PostMapping("/memberReception")
    public RespBean memberReception(@RequestBody Reception reception){
        Integer reception1 = receptionService.memberReception(reception);
        if (reception1!=0){
            return RespBean.ok("ok",reception1);
        }
        return RespBean.error("error",false);
    }
}
