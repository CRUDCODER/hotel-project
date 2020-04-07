package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.MemberExchange;
import com.liujin.entity.RespBean;
import com.liujin.service.IMemberExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/1 10:59
 */
@RestController
public class MemberExchangeController {
    @Autowired
    private IMemberExchangeService memberExchangeService;

    /**
     * 查询会员兑换记录
     * @param page
     * @param limit
     * @param memberExchange
     * @return
     */
    @GetMapping("/memberExchanges")
    public Map<String,Object> fetchMemberExchange(@RequestParam("page") Integer page,
                                                  @RequestParam("limit") Integer limit,
                                                  MemberExchange memberExchange){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",memberExchangeService.fetchMemberExchange(memberExchange));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 根据会员编号查询优惠卷
     * 只查询可以使用的优惠卷 即flag为1
     * @param memberId
     * @return
     */
    @GetMapping("/fetchMemberExchangeByMemberId/{memberId}")
    public List<MemberExchange> fetchMemberExchangeByMemberId(@PathVariable("memberId") Integer memberId){
        MemberExchange memberExchange=new MemberExchange();
        memberExchange.setMemberId(memberId);
        memberExchange.setExchangeFlag(1);
        return memberExchangeService.fetchMemberExchange(memberExchange);
    }

    /**
     * 积分兑换优惠卷
     * 新增兑换记录
     * 减少对应会员积分
     * @param memberExchange
     * @return
     */
    @PostMapping("/memberExchange")
    public RespBean exchangeCoupons(@RequestBody MemberExchange memberExchange){
        if (memberExchangeService.exchangeCoupons(memberExchange)){
            return RespBean.ok("兑换成功",true);
        }
        return RespBean.error("兑换失败",false);
    }













}
