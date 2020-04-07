package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Blacklist;
import com.liujin.entity.RespBean;
import com.liujin.service.IBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/2 13:04
 */
@RestController
public class BlacklistController {
    @Autowired
    private IBlacklistService blacklistService;
    @GetMapping("/blacklists")
    public Map<String,Object> fetchBlackList(@RequestParam("page") Integer page,
                                             @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",blacklistService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/blacklist")
    public RespBean increaseBlacklist(@RequestBody Blacklist blacklist){
        blacklist.setBlacklistDate(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
        if (blacklist.insert())
        {
            return RespBean.ok("添加成功",true);
        }
        return RespBean.error("添加失败",false);
    }
    @GetMapping("/checkCard/{card}")
    public Integer checkCard(@PathVariable("card") String card){
        return blacklistService.getBaseMapper().selectCount(new QueryWrapper<Blacklist>().eq("blacklist_card",card));
    }
}
