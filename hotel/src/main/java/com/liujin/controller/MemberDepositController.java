package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.MemberDeposit;
import com.liujin.entity.RespBean;
import com.liujin.service.IMemberDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/30 12:37
 */
@RestController
public class MemberDepositController {
    @Autowired
    private IMemberDepositService memberDepositService;
    @GetMapping("/memberDeposits")
    public Map<String,Object> fetchMemberDeposit(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit,
                                                 MemberDeposit memberDeposit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",memberDepositService.fetchMemberDeposit(memberDeposit));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/memberDeposit")
    public RespBean increaseMemberDeposit(@RequestBody MemberDeposit memberDeposit){
        if (memberDepositService.increaseMemberDeposit(memberDeposit)){
            return RespBean.ok("充值成功",true);
        }
        return RespBean.error("充值失败",false);
    }
}
