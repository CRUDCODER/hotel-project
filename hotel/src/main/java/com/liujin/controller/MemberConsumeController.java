package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.MemberConsume;
import com.liujin.service.IMemberConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/1 9:48
 */
@RestController
public class MemberConsumeController {
    @Autowired
    private IMemberConsumeService memberConsumeService;
    @GetMapping("/memberConsumes")
    public Map<String,Object> fetchMemberConsume(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit,
                                                 MemberConsume memberConsume){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",memberConsumeService.fetchMemberConsume(memberConsume));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
}
